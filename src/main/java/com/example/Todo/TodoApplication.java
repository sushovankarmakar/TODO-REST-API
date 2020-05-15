package com.example.Todo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collections;

@SpringBootApplication
public class TodoApplication {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(TodoApplication.class);
        app.setDefaultProperties(Collections.singletonMap("server.port", "8090"));
        app.run(args);
        Log log = new Log();
        log.addLog("Spring Boot application has started");
    }
}
