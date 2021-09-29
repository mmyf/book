package com.myf.booktbm;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

//2021.07.30 18:44:15
public class modifyData {
    public static void modifyTime(int id){
        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        Date date = new Date();
        try (
                InputStream inputStream = new FileInputStream("D:\\myn\\test1.xlsx");
                XSSFWorkbook wb = new XSSFWorkbook(inputStream);
                )
        {
            XSSFSheet sheet = wb.getSheetAt(0);
            Row row = sheet.getRow(id);
            Cell newCell = row.createCell(6);
            newCell.setCellValue(format.format(date));
            FileOutputStream outputStream = new FileOutputStream("D:\\myn\\test1.xlsx");
            wb.write(outputStream);
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void modifyBookStatus(int id, String bookStatus){
        try (
                InputStream inputStream = new FileInputStream("D:\\myn\\test1.xlsx");
                XSSFWorkbook wb = new XSSFWorkbook(inputStream);
                )
        {
            XSSFSheet sheet = wb.getSheetAt(0);
            Row row = sheet.getRow(id);
            row.createCell(2).setCellValue(bookStatus);

            FileOutputStream outputStream = new FileOutputStream("D:\\myn\\test1.xlsx");
            wb.write(outputStream);
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
