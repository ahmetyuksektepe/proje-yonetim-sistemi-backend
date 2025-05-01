package com.tasks.tasks.business.concretes;


import com.tasks.tasks.business.abstracts.TaskService;
import com.tasks.tasks.business.requests.CreateTaskRequest;
import com.tasks.tasks.business.requests.UpdateTaskRequest;
import com.tasks.tasks.business.responses.GetTaskResponse;
import com.tasks.tasks.core.utilities.mappers.ModelMapperService;
import com.tasks.tasks.dataAccess.TasksRepository;
import com.tasks.tasks.entities.Task;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TaskManager implements TaskService {
    private TasksRepository tasksRepository;
    private ModelMapperService modelMapperService;


    @Override
    public List<GetTaskResponse> getAllTasks() {
        List<Task> tasks = tasksRepository.findAll();
        List<GetTaskResponse> taskResponses = tasks.stream()
                .map(task -> this.modelMapperService.forResponse()
                        .map(task, GetTaskResponse.class))
                .toList();
        return taskResponses;
    }


    /*@Override
    public List<GetTasksResponse> getTasksByProjectId(Integer projectId) {
        return tasksRepository.findByProjectId(projectId)
                .stream()
                .map(task -> this.modelMapperService.forResponse()
                        .map(task, GetTasksResponse.class))
                .toList();
    }*/

    @Override
    public GetTaskResponse getTaskById(Integer taskId) {
        Task task = tasksRepository.findById(taskId).orElseThrow();

        GetTaskResponse response = this.modelMapperService.forResponse()
                .map(task, GetTaskResponse.class);
        return response;
    }

    @Override
    public void addTask(CreateTaskRequest createTaskRequest) {
        Task task = modelMapperService.forRequest().map(createTaskRequest, Task.class);

        tasksRepository.save(task);
    }

    /*@Override
    public void assignTask(AssignTaskRequest assignTaskRequest) {
        Task task = tasksRepository.findById(assignTaskRequest.getTask_id())
                .orElseThrow(() -> new RuntimeException("Görev bulunamadı"));

        task.setAssignedUserIds(assignTaskRequest.getAssignedUserIds());
        tasksRepository.save(task);
    }*/

    @Override
    public void updateTask(UpdateTaskRequest updateTaskRequest) {
        Task existingTask = tasksRepository.findById(updateTaskRequest.getId())
                .orElseThrow(() -> new RuntimeException("Task not found"));

        // Rol kontrolü kaldırıldı — tüm alanlar güncellenebilir
        this.modelMapperService.forRequest().map(updateTaskRequest, existingTask);

        this.tasksRepository.save(existingTask);
    }


    @Override
    public void deleteTask(int id) {
        tasksRepository.deleteById(id);
    }
}