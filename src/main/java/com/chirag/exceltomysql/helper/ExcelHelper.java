package com.chirag.exceltomysql.helper;

import com.chirag.exceltomysql.entity.Orders;
import com.chirag.exceltomysql.entity.Templates;
import com.chirag.exceltomysql.entity.Users;
import com.chirag.exceltomysql.repository.TemplatesRepo;
import com.chirag.exceltomysql.repository.UserRepo;
import jakarta.transaction.Transactional;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;



@Component
public class ExcelHelper {

    @Autowired
    private LogSaver logSaver;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private TemplatesRepo templatesRepo;

    public boolean Excelcheck(MultipartFile file,Users user) {
        if (file == null) {
            logSaver.setLogs("Uploading" , "File is null",user);
            return false;
        }
        String fileName = file.getOriginalFilename();
//        String ext = fileName.substring(0,fileName.lastIndexOf("."));
//        if(!ext.equals("orders_data")) {
//            return false;
//        }
        if (file.isEmpty() || !fileName.endsWith("xls") && !fileName.endsWith("xlsx")) {
            logSaver.setLogs("Uploading" , "File is empty or either not in format",user);
            return false;
        }
        return true;
    }


    public boolean matchOrderColName(MultipartFile file,Users user) throws IOException {
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
                        logSaver.setLogs("Uploading : " + orgName , "Not match colName: " + val,user);
                        return false;
                }
            }
        } catch (IOException e) {
            logSaver.setLogs("Uploading : " +orgName, "Not match colName: " + e.getMessage(),user);
            e.printStackTrace();
            return false;
        }
        return true;
    }


    public boolean matchProductColName(MultipartFile file,Users user) throws IOException {
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
                        logSaver.setLogs("Uploading : "+orgName , "Not match colName: " + val,user);
                        return false;
                }
            }
        } catch (IOException e) {
            logSaver.setLogs("Uploading : "+orgName , "Not match colName: " + e.getMessage(),user);
            e.printStackTrace();
            return false;
        }
        return true;
    }


    @Transactional
    public void fillDataTemplates(MultipartFile file) throws IOException {
        Workbook workbook = WorkbookFactory.create(file.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);
        Row row = sheet.getRow(0);

        int tot_row = sheet.getLastRowNum() + 1;
        int tot_col = row.getLastCellNum();

        for (int i = 1; i < tot_row; i++) {
            Templates tt=new Templates();
            Row rows = sheet.getRow(i);
            for (int j = 0; j < tot_col; j++) {
                if(j==0)
                    continue;
                else if(j==1){
                    String temp_name = rows.getCell(j).getStringCellValue();
                    tt.setOrgName(temp_name);
                }
                else if(j==2){
                String temp_name = rows.getCell(j).getStringCellValue();
                tt.setTemplateName(temp_name);
                }
            }
            templatesRepo.save(tt);
            }
        }
    }
