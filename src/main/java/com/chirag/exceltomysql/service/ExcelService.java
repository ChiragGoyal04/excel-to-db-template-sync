package com.chirag.exceltomysql.service;

import com.chirag.exceltomysql.entity.Orders;
import com.chirag.exceltomysql.helper.ExcelHelper;

import com.chirag.exceltomysql.repository.ExcelRepository;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class ExcelService {

    @Autowired
    private ExcelHelper excelHelper;

    @Autowired
    private ExcelRepository excelRepository;


    //convert content into list(return list of data)
    public List<List<String>>  readExcel(MultipartFile file) throws IOException {

        Orders orders=new Orders();

        List<List<String>> lists = new ArrayList<>();

        Workbook workbook = WorkbookFactory.create(file.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);
        Row row = sheet.getRow(0);

        DataFormatter formatter = new DataFormatter();

        int tot_row=sheet.getLastRowNum()+1;
        int tot_col=row.getLastCellNum();

        for(int i=1;i<tot_row;i++) {
            List<String> l1 = new ArrayList<>();
            Row rows = sheet.getRow(i);
            for (int j = 0; j < tot_col; j++) {
                if (j == 0) {
                    Double ans = rows.getCell(j).getNumericCellValue();
                    orders.setId(ans.toString());
                    l1.add(ans.toString());
                } else if (j == 1) {
                    orders.setCustomer_name(rows.getCell(j).getStringCellValue());
                    l1.add(rows.getCell(j).getStringCellValue());
                } else if (j == 2) {
                    orders.setProduct(rows.getCell(j).getStringCellValue());
                    l1.add(rows.getCell(j).getStringCellValue());
                } else if (j == 3) {
                    Double ans = rows.getCell(j).getNumericCellValue();
                    orders.setQuantity(ans.toString());
                    l1.add(ans.toString());
                } else if (j == 4) {
                    Double ans = rows.getCell(j).getNumericCellValue();
                    orders.setTotal_Amount(ans.toString());
                    l1.add(ans.toString());
                } else if (j == 5) {
                    String value = formatter.formatCellValue(rows.getCell(j));
                    orders.setOrder_date(value);
                    l1.add(value);
                }
            }
            lists.add(l1);
            excelRepository.save(orders);
        }
        return lists;
    }
}
