package com.chirag.exceltomysql.controller;


import com.chirag.exceltomysql.entity.Orders;
import com.chirag.exceltomysql.entity.Products;
import com.chirag.exceltomysql.entity.Users;
import com.chirag.exceltomysql.helper.ExcelHelper;
import com.chirag.exceltomysql.helper.LogSaver;
import com.chirag.exceltomysql.repository.OrderRepository;
import com.chirag.exceltomysql.repository.ProductRepository;
import com.chirag.exceltomysql.repository.UserRepo;
import com.chirag.exceltomysql.service.OrderExcelService;
import com.chirag.exceltomysql.service.ProductExcelService;
import jakarta.servlet.annotation.MultipartConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api")
public class ExcelController {

    @Autowired
    private ExcelHelper excelHelper;

    @Autowired
    private OrderExcelService orderExcelService;

    @Autowired
    private ProductExcelService productExcelService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private LogSaver logSaver;

    @Autowired
    private UserRepo userRepo;

    @PostMapping("/temp/excel")
    public ResponseEntity<String> uploadExcel(@RequestParam("template") String template , @RequestParam("file") MultipartFile file, Principal principal) throws IOException{
        Users user= userRepo.findByUsername(principal.getName()).orElseThrow(null);
        if(excelHelper.Excelcheck(file,user)) {
            try {
                if (template.equals("orders")) {
                    boolean karnaHai = excelHelper.matchOrderColName(file,user);
                    if(karnaHai) {
                        orderExcelService.fillOrderExcel(file);
                        logSaver.setLogs("Request to fill Order Template","Data inserted successfully",user);
                        return ResponseEntity.ok().body("Data inserted successfully");
                    }
                    else{
                        return ResponseEntity.badRequest().body("Columns Not matched");
                    }
                }
                else if (template.equals("products")) {
                    boolean karnaHai = excelHelper.matchProductColName(file,user);
                    if(karnaHai) {
                        productExcelService.fillProductExcel(file);
                        logSaver.setLogs("Request to fill Product Template","Data inserted successfully",user);
                        return ResponseEntity.ok().body("Data inserted successfully");
                    }
                    else{
                        return ResponseEntity.badRequest().body("Columns Not matched");
                    }
                }
            } catch (Exception e) {
                logSaver.setLogs("Error in Uploading : ", e.getMessage(),user);
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }
        return ResponseEntity.badRequest().body("File is invalid");
    }

//    @PostMapping("/excel/upload")
//    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file) throws IOException {
//        if(excelHelper.Excelcheck(file)) {
//            try {
//                boolean karnaHai = excelHelper.matchOrderColName(file);
//                if(karnaHai) {
//                    String li =excelService.readExcel(file);
//                    logSaver.setLogs("Uploaded ",li);
//                    return ResponseEntity.ok().body( "✔ Data Inserted Successfully");
//                }
//                    return ResponseEntity.badRequest().body("Columns not matched");
//            }
//            catch (Exception e) {
//                logSaver.setLogs("Error in Uploading : ", e.getMessage());
//                return ResponseEntity.badRequest().body(e.getMessage());
//            }
//        }
//        logSaver.setLogs("Upload Request : ","File name is invalid : "+file.getOriginalFilename());
//        return ResponseEntity.badRequest().body("File name is invalid");
//    }

    @GetMapping("/excel/read/{name}")
    public ResponseEntity<List<?>> readExcel(@PathVariable String name) throws IOException {
        if(name==null) {
            return ResponseEntity.badRequest().body(new ArrayList<>());
        }
        if(name.equals("orders")) {
            List<Orders> l1= orderRepository.findAll();
            return ResponseEntity.ok().body(l1);
        }
        else if(name.equals("products")) {
            List<Products> l1= productRepository.findAll();
            return ResponseEntity.ok().body(l1);
        }
        return ResponseEntity.badRequest().body(new ArrayList<>());
    }

    @PostMapping("/template")
    public ResponseEntity<String> getTemp(@RequestParam("file") MultipartFile file) throws IOException {
        excelHelper.fillDataTemplates(file);
        return ResponseEntity.ok().body("Data inserted successfully");
    }

//    @PostMapping("/temp-cols")
//    public ResponseEntity<String> getTempCols(@RequestParam("file") MultipartFile file) throws Exception{
//        excelHelper.fillDataTempCols(file);
//        return ResponseEntity.ok().body("Data inserted successfully");
//    }
}
