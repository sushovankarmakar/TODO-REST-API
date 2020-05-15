package com.example.Todo.controller;

import com.example.Todo.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.example.Todo.services.TodoService;

@Controller
//@EnableAutoConfiguration
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TodoController {

    Log log = new Log();
    @Autowired
    TodoService todoService;

    @GetMapping(path = "/todos")
    public ResponseEntity<?> getTodos() {
        log.addLog("GET Request for all todos");
        return todoService.getTodos();
    }

    @GetMapping(path = "/todos/{id}")
    public ResponseEntity<?> getTodo(@PathVariable("id") int todoid){
        log.addLog("GET Request for todo number for" + (todoid+1));
        return todoService.getTodo(todoid);
    }

    @PostMapping("/todos")
    public ResponseEntity<?> addTodo(@RequestBody String json){
        log.addLog("POST Request - Add new Todo");
        log.addLog(json);
        return todoService.addTodo(json);
    }

    @PutMapping(path = "/todos/{id}")
    public ResponseEntity<?> updateTodo(@PathVariable("id") int todoid, @RequestBody String json){
        log.addLog("PUT Request - Update todo number " + (todoid+1));
        log.addLog(json);
        return todoService.updateTodo(todoid, json);
    }

    @DeleteMapping(path = "/todos/{id}")
    public ResponseEntity<?> deleteTodo(@PathVariable("id") int todoid){
        log.addLog("DELETE Request - Delete todo number " + (todoid+1));
        return todoService.deleteTodo(todoid);
    }
}
