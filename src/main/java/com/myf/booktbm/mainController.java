package com.myf.booktbm;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.alibaba.fastjson.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;
import static com.myf.booktbm.getCellValue.getCellFormatValue;
@Controller
public class mainController {
    @GetMapping(value = "/")
    public String mainGetUrl() {

        return "main";
    }
    @ResponseBody
    @GetMapping(value = "/search")
    public void searchBooks(HttpServletResponse response)throws IOException {
        ArrayList<Book> bookArrayList = new ArrayList<>();
        int index = 0;
        try(
                InputStream inputStream = new FileInputStream("D:\\myn\\test1.xlsx");
                XSSFWorkbook wb = new XSSFWorkbook(inputStream);
                )
        {
            XSSFSheet sheet = wb.getSheetAt(0);
            Row rowSt = sheet.getRow(0);
            Cell cellSt = rowSt.getCell(4);
            if (getCellFormatValue(cellSt).toString().equals("5.0")){
                index = 4;
            }else{
                index = 3;
            }

            for(int i=1;i<sheet.getPhysicalNumberOfRows();i++){
                Row row = sheet.getRow(i);

                Book book = new Book();
                book.setBookId(i);
                Cell cell1 = row.getCell(0);
                book.setBookName(getCellFormatValue(cell1).toString());


                Cell cell2 = row.getCell(1);
                book.setAuthor(getCellFormatValue(cell2).toString());

                Cell cell3 = row.getCell(2);
                book.setBookStatus(getCellFormatValue(cell3).toString());

                Cell cell4 = row.getCell(index);
                book.setTheLastChapter(getCellFormatValue(cell4).toString());

                Cell cell7 = row.getCell(6);
                SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
                String dateString = getCellFormatValue(cell7).toString();
                Date date = format.parse(dateString);
                //imageUrl
                Cell cell8 = row.getCell(7);
                book.setImageUrl(getCellFormatValue(cell8).toString());
                //txtUrl
                Cell cell9 = row.getCell(8);
                String content = readTxt.readTxtFile(getCellFormatValue(cell9).toString());
                book.setTxtUrl(content);


                book.setModifiedTime(date);
                bookArrayList.add(book);
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        JSONArray bookJSONArray;
        bookJSONArray = JSON.parseArray(com.alibaba.fastjson.JSON.toJSONString(bookArrayList));
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(bookJSONArray.toJSONString());
    }
    @ResponseBody
    @GetMapping(value = "/useCrawler")
    public void useCrawler(HttpServletResponse response){
        try {
            String[] args = new String[] { "python", "D:\\crawler.py"};
            Process proc = Runtime.getRuntime().exec(args);// 执行py文件
            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream(), "GBK"));
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }

            in.close();
            proc.waitFor();
            searchBooks(response);
        }catch (IOException | InterruptedException e){
            e.printStackTrace();
        }
    }
    @ResponseBody
    @PostMapping(value = "/modifyBookStatus")
    public void sort(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String status = request.getParameter("status");
        int id = Integer.parseInt(request.getParameter("id"));
        modifyData.modifyBookStatus(id,status);
        modifyData.modifyTime(id);
        searchBooks(response);
    }
    @GetMapping(value = "/l")
    public String l(){
//        landformType hill = com.myf.dnd.dataClass.landformType.HILL;
//        landform landform = new landform(hill, 3);
//        ArrayList<landform> map1 = new ArrayList<>();
//        map1.add(landform);
//        System.out.println(map1.get(0));
        return "l";
    }
    @GetMapping(value = "/mcSearch")
    public String mc(){
        return "mcSearch";
    }
    @GetMapping(value = "/starTable")
    public String star(){
        return "starTable";
    }
    @GetMapping(value = "/easyForm")
    public String easyForm(){
        return "easyForm";
    }
    @GetMapping(value = "/etiquetteExam")
    public String etiquetteExam(){
        return "etiquetteExam";
    }
    @GetMapping(value = "/calculator")
    public String calculator(){
        return "calculator";
    }
    @PostMapping(value = "/easyForm")
    public void easyFormPost(HttpServletRequest request){
        String bookNameInput = request.getParameter("bookNameInput");
        String bookAuthorInput = request.getParameter("bookAuthorInput");
        String newFormSelect = request.getParameter("newFormSelect");
        String abstract1 = request.getParameter("abstract");
        String comment = request.getParameter("comment");

        System.out.println(bookAuthorInput);
        System.out.println(bookNameInput);
        System.out.println(newFormSelect);
        System.out.println(abstract1);
        System.out.println(comment);

    }
    @GetMapping(value = "/test")
    public String testG(){
        return "test";
    }
    @ResponseBody
    @PostMapping(value = "/test")
    public String test(MultipartFile myFile){
        //获取上传文件名,包含后缀
        String originalFilename = myFile.getOriginalFilename();
        //获取后缀
        String substring = originalFilename.substring(originalFilename.lastIndexOf("."));
        //保存的文件名
        String dFileName = UUID.randomUUID()+substring;
        //保存路径
        //springboot 默认情况下只能加载 resource文件夹下静态资源文件
        String path = "D:/myn/testImg/";
        //生成保存文件
        File uploadFile = new File(path+dFileName);
        try {
            myFile.transferTo(uploadFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("success");
        return dFileName;
    }
    @GetMapping(value = "/uiDesign")
    public String ui(){
        return "uiDesign";
    }
    @Component
    public class CustomWebConfiguration  implements WebMvcConfigurer{

        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry){
            registry.addResourceHandler("/**").addResourceLocations(
                    "classpath:/META-INF/resources/",
                    "classpath:/resources/",
                    "classpath:/static/",
                    "classpath:/public/",
                    "file:/D:/myn/testImg/"
            );
        }
    }
}