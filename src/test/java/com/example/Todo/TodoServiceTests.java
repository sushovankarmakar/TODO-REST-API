package com.example.Todo;

import com.example.Todo.services.TodoService;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

public class TodoServiceTests {

    TodoService todoService = new TodoService();

    @Test
    public void testAddTodos(){

        todoService.addTodo("{ \"todoname\" : \"dance\" }");
        ResponseEntity<?> responseEntity = todoService.getTodos();
        Assert.assertTrue(responseEntity.getBody().toString().contains("dance"));
    }

    @Test
    public void testGetTodos(){
        todoService.addTodo("{ \"todoname\" : \"eat\" }");
        ResponseEntity<?> responseEntity = todoService.getTodos();
        Assert.assertTrue(responseEntity.getBody().toString().contains("eat"));
    }

    @Test
    public void testGetTodo(){
        todoService.addTodo("{\"todoname\" : \"code\"}");
        todoService.addTodo("{\"todoname\" : \"sleep\"}");
        todoService.addTodo("{\"todoname\" : \"repeat\"}");
        ResponseEntity<?> responseEntity = todoService.getTodo(2);
        Assert.assertTrue(responseEntity.getBody().toString().contains("repeat"));

        ResponseEntity<?> responseEntity1 = todoService.getTodo(10);
        Assert.assertEquals(400, responseEntity1.getStatusCodeValue());
        Assert.assertTrue(responseEntity1.getBody().toString().contains("Todo id has exceeded"));
    }

    @Test
    public void testUpdateTodo(){
        todoService.addTodo("{\"todoname\" : \"code\"}");
        ResponseEntity<?> responseEntity = todoService.updateTodo(0, "{\"todoname\" : \"play\"}");
        Assert.assertTrue(responseEntity.getBody().toString().contains("play"));

        ResponseEntity<?> responseEntity1 = todoService.updateTodo(10, "{\"todoname\" : \"play\"}");
        Assert.assertEquals(400, responseEntity1.getStatusCodeValue());
        Assert.assertTrue(responseEntity1.getBody().toString().contains("Todo id has exceeded"));
    }

    @Test
    public void testDeleteTodo(){
        todoService.addTodo("{\"todoname\" : \"code\"}");
        ResponseEntity<?> responseEntity = todoService.deleteTodo(0);
        Assert.assertTrue(responseEntity.getBody().toString().contains("Todo 1 has been deleted"));

        ResponseEntity<?> responseEntity1 = todoService.deleteTodo(10);
        Assert.assertEquals(400, responseEntity1.getStatusCodeValue());
        Assert.assertTrue(responseEntity1.getBody().toString().contains("Todo id has exceeded"));
    }
}
