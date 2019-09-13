package com.example.Todo;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Log {
    public void addLog(String loggingInfo){
        Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
        FileHandler fileHandler;
        try{
            fileHandler = new FileHandler("C:/Users/skarmakar/Downloads/TODO-REST-API-dev1/logs/TodoLog.log");
            logger.addHandler(fileHandler);
            SimpleFormatter formatter = new SimpleFormatter();
            fileHandler.setFormatter(formatter);
            logger.info(loggingInfo);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
