package com.tasks.tasks.webApi.controller;


import com.tasks.tasks.business.abstracts.TaskService;
import com.tasks.tasks.business.requests.CreateTaskRequest;
import com.tasks.tasks.business.requests.UpdateTaskRequest;
import com.tasks.tasks.business.responses.GetTaskResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@AllArgsConstructor
public class TaskController {

    private TaskService taskService;

    @GetMapping
    public List<GetTaskResponse> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/{id}")
    public GetTaskResponse getTaskById(@PathVariable int id){
        return taskService.getTaskById(id);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public void addTask(@RequestBody @Valid CreateTaskRequest createTaskRequest){
        taskService.addTask(createTaskRequest);
    }

    @PutMapping
    public void updateTask(@RequestBody @Valid UpdateTaskRequest updateTaskRequest){
        taskService.updateTask(updateTaskRequest);
    }

    /*@PostMapping("/assign")
    @ResponseStatus(code = HttpStatus.OK)
    public void assignTask(@RequestBody @Valid AssignTaskRequest assignTaskRequest){
        taskService.assignTask(assignTaskRequest);
    }*/

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        taskService.deleteTask(id);
    }
}