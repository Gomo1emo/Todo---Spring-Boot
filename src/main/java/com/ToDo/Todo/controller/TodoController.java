package com.ToDo.Todo.controller;

import com.ToDo.Todo.entity.Todo;
import com.ToDo.Todo.service.TodoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class TodoController {

    @Autowired
    private TodoServiceImpl todoService; //In the controller we do not use the information

    @GetMapping
    public List<Todo> findAll() {
        return todoService.getAllTodo();
    }

    @PostMapping
    public void save(@RequestBody Todo todo) { //Request body is the information we want to save
        todoService.saveTodo(todo);
    }

    @GetMapping("/{id}")
    public Todo findOne(@PathVariable Long id) {
        return todoService.getTodoById(id);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody Todo todo) {
        this.todoService.updateTodo(id, todo);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) { //Path Variable annotation is used to specify id for the needed information
        this.todoService.deleteTodo(id);
    }
}
