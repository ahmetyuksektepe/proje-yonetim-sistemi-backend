package com.tasks.tasks.business.abstracts;


import com.tasks.tasks.business.requests.CreateTaskRequest;
import com.tasks.tasks.business.requests.UpdateTaskRequest;
import com.tasks.tasks.business.responses.GetTaskResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TaskService {
    List<GetTaskResponse> getAllTasks();
    //List<GetTaskResponse> getTasksByProjectId(Integer projectId);
    GetTaskResponse getTaskById(Integer taskId);

    void addTask(CreateTaskRequest createTaskRequest);
    //void assignTask(AssignTaskRequest assignTaskRequest);
    void updateTask(UpdateTaskRequest updateTaskRequest);
    void deleteTask(int id);
}