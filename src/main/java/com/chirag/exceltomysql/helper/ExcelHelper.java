package com.chirag.exceltomysql.helper;

import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;



@Component
public class ExcelHelper {

    @Autowired
    private LogSaver logSaver;

    public boolean Excelcheck(MultipartFile file) {
        if (file == null) {
            logSaver.setLogs("Uploading" , "File is null");
            return false;
        }
        String fileName = file.getOriginalFilename();
//        String ext = fileName.substring(0,fileName.lastIndexOf("."));
//        if(!ext.equals("orders_data")) {
//            return false;
//        }
        if (file.isEmpty() || !fileName.endsWith("xls") && !fileName.endsWith("xlsx")) {
            logSaver.setLogs("Uploading" , "File is empty or either not in format");
            return false;
        }
        return true;
    }


    public boolean matchOrderColName(MultipartFile file) throws IOException {
        String fileName=file.getOriginalFilename();
        String orgName=fileName.substring(0, fileName.lastIndexOf("."));

        try {
            Workbook workbook = WorkbookFactory.create(file.getInputStream());
            Sheet sheet = workbook.getSheetAt(0);
            Row row = sheet.getRow(0);

            int colNums = row.getLastCellNum();
            int x = 0;
            while (x < colNums) {
                Cell cell = row.getCell(x);
                String val = cell.getStringCellValue();

                switch (val) {
                    case "Order_ID":
                        x++;
                        continue;
                    case "Customer_Name":
                        x++;
                        continue;
                    case "Product":
                        x++;
                        continue;
                    case "Quantity":
                        x++;
                        continue;
                    case "Total_Amount":
                        x++;
                        continue;
                    case "Order_Date":
                        x++;
                        continue;
                    default:
                        logSaver.setLogs("Uploading : " + orgName , "Not match colName: " + val);
                        return false;
                }
            }
        } catch (IOException e) {
            logSaver.setLogs("Uploading : " +orgName, "Not match colName: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
        return true;
    }


    public boolean matchProductColName(MultipartFile file) throws IOException {
        String fileName=file.getOriginalFilename();
        String orgName=fileName.substring(0, fileName.lastIndexOf("."));

        try {
            Workbook workbook = WorkbookFactory.create(file.getInputStream());
            Sheet sheet = workbook.getSheetAt(0);
            Row row = sheet.getRow(0);

            int colNums = row.getLastCellNum();
            int x = 0;
            while (x < colNums) {
                Cell cell = row.getCell(x);
                String val = cell.getStringCellValue();

                switch (val) {
                    case "Product_ID":
                        x++;
                        continue;
                    case "Product_Name":
                        x++;
                        continue;
                    case "Category":
                        x++;
                        continue;
                    case "Price":
                        x++;
                        continue;
                    case "Stock":
                        x++;
                        continue;
                    case "Supplier":
                        x++;
                        continue;
                    default:
                        logSaver.setLogs("Uploading : "+orgName , "Not match colName: " + val);
                        return false;
                }
            }
        } catch (IOException e) {
            logSaver.setLogs("Uploading : "+orgName , "Not match colName: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
