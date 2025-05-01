package com.tasks.tasks.business.requests;

import com.tasks.tasks.enums.Status;
import lombok.Data;

@Data
public class CreateTaskRequest {
    private String task_name;
    private Status status;
    private String task_description;
    private Integer assignedProjectId;
    private Integer assignedUserId;

}