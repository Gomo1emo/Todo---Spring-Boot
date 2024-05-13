package com.ToDo.Todo.service;

import com.ToDo.Todo.entity.Todo;
import com.ToDo.Todo.repository.TodoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service //Annotation used to specify a service
public class TodoServiceImpl implements TodoService {


    @Autowired //Because we want to use methods from Db we need to connect
    private TodoRepo todoRepo;

    @Override
    public List<Todo> getAllTodo() {
        return todoRepo.findAll();
    }

    @Override
    public void saveTodo(Todo todo) {
        this.todoRepo.save(todo); //save() is a method provided by JpaRepository
    }

    @Override
    public Todo getTodoById(Long id) { //Return type todo is expected
        Optional<Todo> optional = todoRepo.findById(id);
        Todo todo;
        if (optional.isPresent()) {
            todo = optional.get();
        } else {
            throw new RuntimeException("Todo for the" + id + "is not found");
        }
        return todo;
    }

    @Override
    public void updateTodo(Long id, Todo todo) {
        Todo todoFromDb = todoRepo.findById(id).get();
        todoFromDb.setTaskName(todo.getTaskName());
        todoFromDb.setDescription(todo.getDescription());
        todoRepo.save(todoFromDb);
    }

    @Override
    public void deleteTodo(Long id) {
        this.todoRepo.deleteById(id);
    }
}