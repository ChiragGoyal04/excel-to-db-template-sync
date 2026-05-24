package com.chirag.exceltomysql.controller;


import com.chirag.exceltomysql.entity.Orders;
import com.chirag.exceltomysql.helper.ExcelHelper;
import com.chirag.exceltomysql.helper.LogSaver;
import com.chirag.exceltomysql.repository.ExcelRepository;
import com.chirag.exceltomysql.service.ExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/api")
public class ExcelController {

    @Autowired
    private ExcelHelper excelHelper;

    @Autowired
    private ExcelService excelService;

    @Autowired
    private ExcelRepository  excelRepository;

    @Autowired
    private LogSaver logSaver;

    @PostMapping("/excel/upload")
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file) throws IOException {
        if(excelHelper.Excelcheck(file)) {
            try {
                boolean karnaHai = excelHelper.matchColName(file);
                if(karnaHai) {
                    String li =excelService.readExcel(file);
                    logSaver.setLogs("Uploaded ",li);
                    return ResponseEntity.ok().body( "✔ Data Inserted Successfully");
                }
                    return ResponseEntity.badRequest().body("Columns not matched");
            }
            catch (Exception e) {
                logSaver.setLogs("Error in Uploading : ", e.getMessage());
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }
        logSaver.setLogs("Upload Request : ","File name is invalid : "+file.getOriginalFilename());
        return ResponseEntity.badRequest().body("File name is invalid");
    }

    @GetMapping("/excel/read")
    public ResponseEntity<List<Orders>> readExcel() throws IOException {
        List<Orders> l1=excelRepository.findAll();
            return ResponseEntity.ok().body(l1);
    }
}
