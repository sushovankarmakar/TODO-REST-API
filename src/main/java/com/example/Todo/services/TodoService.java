package com.example.Todo.services;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import com.example.Todo.Log;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TodoService {
    private List<String> todoList = new ArrayList<>();
    Log log = new Log();

    public ResponseEntity<?> getTodos() {
        log.addLog("All Todo is retrieved");
        return sendResponse("All todo is retrieved");
    }

    public ResponseEntity<?> getTodo(int todoid){
        if(todoid > todoList.size()){
            log.addLog("Todo id has exceeded");
            return new ResponseEntity<>("Todo id has exceeded", HttpStatus.BAD_REQUEST);
        }

        JSONObject jsonObject = new JSONObject()
                .put("todoname", todoList.get(todoid))
                .put("status", "Todo " +(todoid + 1) + " is retrieved")
                .put("timestamp", Instant.now().toString());
        log.addLog("Todo " +(todoid + 1) + " is retrieved");
        return new ResponseEntity<>(jsonObject.toString(), HttpStatus.OK);
    }

    public ResponseEntity<?> addTodo(String json){
        JSONObject jsonObject = new JSONObject(json);
        String todoname = jsonObject.getString("todoname");
        todoList.add(todoname);
        log.addLog("Todo "+ todoname +" has been added");
        return sendResponse("Todo "+ todoname +" has been added");
    }

    public ResponseEntity<?> updateTodo(int todoid, String json){
        if(todoid > todoList.size()){
            log.addLog("Todo id has exceeded");
            return new ResponseEntity<>("Todo id has exceeded", HttpStatus.BAD_REQUEST);
        }

        JSONObject jsonObject = new JSONObject(json);
        String todoname = jsonObject.getString("todoname");
        todoList.set(todoid, todoname);
        log.addLog("Todo "+ (todoid + 1) + " has been updated");
        return sendResponse("Todo "+ (todoid + 1) + " has been updated");
    }

    public ResponseEntity<?> deleteTodo(int todoid){
        if(todoid > todoList.size()){
            log.addLog("Todo id has exceeded");
            return new ResponseEntity<>("Todo id has exceeded", HttpStatus.BAD_REQUEST);
        }
        todoList.remove(todoid);
        log.addLog("Todo "+ (todoid + 1) + " has been deleted");
        return sendResponse("Todo "+ (todoid + 1) + " has been deleted");
    }

    private ResponseEntity<?> sendResponse(String status) {
        JSONObject jsonObject = new JSONObject()
                .put("todo", todoList)
                .put("status", status)
                .put("timestamp", Instant.now().toString());
        log.addLog(jsonObject.toString());
        return new ResponseEntity<>(jsonObject.toString(), HttpStatus.OK);
    }
}