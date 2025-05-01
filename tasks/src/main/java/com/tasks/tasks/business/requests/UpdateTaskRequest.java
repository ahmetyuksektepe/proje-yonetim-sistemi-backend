package com.tasks.tasks.business.requests;


import com.tasks.tasks.enums.Status;
import lombok.Data;

@Data
public class UpdateTaskRequest {
    private int id;
    private String task_name;
    private Status status;
    private String task_description;
    //şimdi enum status güncelle
    private Integer assignedUserId;

}