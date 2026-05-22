package com.chirag.exceltomysql.helper;

import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Component
public class ExcelHelper {

    public boolean Excelcheck(MultipartFile file) {
        if (file == null) {
            return false;
        }
        String fileName = file.getOriginalFilename();
        if (file.isEmpty() || !fileName.endsWith("xls") && !fileName.endsWith("xlsx")) {
            return false;
        }
        return true;
    }

    public boolean matchColName(MultipartFile file) throws IOException {
        try {
            Workbook workbook = WorkbookFactory.create(file.getInputStream());
            Sheet sheet = workbook.getSheetAt(0);
            Row row = sheet.getRow(0);

            int colNums = row.getFirstCellNum();
            int x = 0;
            while (x < colNums) {
                Cell cell = row.getCell(x);
                String val = cell.getStringCellValue();

                switch (val) {
                    case "Order_ID":
                        x++;
                        continue;
                    case "Customer_name":
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
                        return false;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
}
