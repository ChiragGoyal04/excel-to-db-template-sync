package com.chirag.exceltomysql.controller;


import com.chirag.exceltomysql.helper.ExcelHelper;
import com.chirag.exceltomysql.service.ExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api")
public class ExcelController {

    @Autowired
    private ExcelHelper excelHelper;

    @Autowired
    private ExcelService excelService;

    @PostMapping("/upload")
    public ResponseEntity<List<List<String>>> upload(@RequestParam("file") MultipartFile file) throws IOException {
        if(excelHelper.Excelcheck(file)) {
            try {
                boolean karnaHai = excelHelper.matchColName(file);
                if(karnaHai) {
                    List<List<String>> li =excelService.readExcel(file);
                    return ResponseEntity.ok().body(li);
                }
                    return ResponseEntity.badRequest().build();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/read")
    public ResponseEntity<List<List<String>>> readExcel(@RequestParam("file") MultipartFile file) throws IOException {
        if(excelHelper.Excelcheck(file)) {
            List<List<String>> l1 = new ArrayList<>();
            return ResponseEntity.ok().body(l1);
        }
        return ResponseEntity.badRequest().build();
    }
}
