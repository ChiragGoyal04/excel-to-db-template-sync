package com.chirag.exceltomysql.service;

import com.chirag.exceltomysql.entity.Orders;

import com.chirag.exceltomysql.repository.OrderRepository;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class OrderExcelService {

    @Autowired
    private OrderRepository orderRepository;


    //convert content into list(return list of data)
    public void fillOrderExcel(MultipartFile file) throws IOException {


        Workbook workbook = WorkbookFactory.create(file.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);
        Row row = sheet.getRow(0);

        DataFormatter formatter = new DataFormatter();

        int tot_row=sheet.getLastRowNum()+1;
        int tot_col=row.getLastCellNum();

        for(int i=1;i<tot_row;i++) {
            Orders orders=new Orders();
            Row rows = sheet.getRow(i);
            for (int j = 0; j < tot_col; j++) {
                if(j==0){
                Double ans=rows.getCell(j).getNumericCellValue();
                Long res=Math.round(ans);
                orders.setOrder_id(res);
                }
                 if (j == 1) {
                    orders.setCustomer_name(rows.getCell(j).getStringCellValue());
                } else if (j == 2) {
                    orders.setProduct(rows.getCell(j).getStringCellValue());
                } else if (j == 3) {
                    Double ans = rows.getCell(j).getNumericCellValue();
                    Integer res= (int) Math.round(ans);
                    orders.setQuantity(res.toString());
                } else if (j == 4) {
                    Double ans = rows.getCell(j).getNumericCellValue();
                     Integer res= (int) Math.round(ans);
                    orders.setTotal_Amount(res.toString());
                } else if (j == 5) {
                    String value = formatter.formatCellValue(rows.getCell(j));
                    orders.setOrder_date(value);
                }
            }
            orderRepository.save(orders);
        }
    }
}
