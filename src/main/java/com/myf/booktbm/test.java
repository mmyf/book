package com.myf.booktbm;

import jxl.CellType;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.*;
import java.io.File;
import java.io.IOException;
import java.util.Date;

public class test {
    public static void main(String[] args){
        try
        {
            File file = new File("D:\\myn\\test12.xls");
            Workbook workbook = Workbook.getWorkbook(file);
            WritableWorkbook writableWorkbook = Workbook.createWorkbook(file,workbook);

            if(workbook != null && writableWorkbook != null){
                WritableSheet sheet = writableWorkbook.getSheet(0);
                for(int i=1;i<sheet.getRows();i++){
                    Date date = new Date();
                    WritableCell cell = sheet.getWritableCell(6, i);
                    if (cell.getType() == CellType.LABEL) {
                        Label label = (Label) cell;
                        label.setString(date.toString());  // 设置数据
                    }
                    System.out.println("success"+i);
                }
            }
            writableWorkbook.close();
        }catch (IOException | BiffException | WriteException e){
            e.printStackTrace();
        }
    }
}
