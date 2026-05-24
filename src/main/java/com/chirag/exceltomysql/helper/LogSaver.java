package com.chirag.exceltomysql.helper;

import com.chirag.exceltomysql.entity.Logs;
import com.chirag.exceltomysql.repository.LogRespository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

@Component
public class LogSaver {

    @Autowired
    private LogRespository  logRespository;

    public void setLogs(String action,String message) {
        Logs logs = new Logs();
        logs.setAction(action);
        logs.setMessage(message);
        logRespository.save(logs);
    }
}
