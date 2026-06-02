package com.chirag.exceltomysql.service;

import com.chirag.exceltomysql.repository.ProductRepository;
import com.chirag.exceltomysql.entity.Products;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProductExcelService {

    @Autowired
    private ProductRepository productRepository;


    public void fillProductExcel(MultipartFile file) throws IOException {

        Workbook workbook = WorkbookFactory.create(file.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);
        Row row = sheet.getRow(0);

        int tot_row=sheet.getLastRowNum()+1;
        int tot_col=row.getLastCellNum();

        for(int i=1;i<tot_row;i++) {
            Products product = new Products();
            Row row1 = sheet.getRow(i);
            for (int j = 0; j < tot_col; j++)
            {
                if (j == 0) {
                    Double ans = row1.getCell(j).getNumericCellValue();
                    Long val = Math.round(ans);
                    Double temp=(double)val;
                    product.setProduct_ID(temp);
                } else if (j == 1) {
                    String ans = row1.getCell(j).getStringCellValue();
                    product.setProduct_Name(ans);
                } else if (j == 2) {
                    String ans = row1.getCell(j).getStringCellValue();
                    product.setCategory(ans);
                } else if (j == 3) {
                    Double ans =row1.getCell(j).getNumericCellValue();
                    Integer res= (int)Math.round(ans);
                    product.setPrice(String.valueOf(res));
                } else if (j == 4) {
                    Double ans =row1.getCell(j).getNumericCellValue();
                    Integer res= (int)Math.round(ans);
                    product.setStock(String.valueOf(res));
                } else if (j == 5) {
                    String ans = row1.getCell(j).getStringCellValue();
                    product.setSupplier(ans);
                }
            }
            productRepository.save(product);
        }
    }
}
