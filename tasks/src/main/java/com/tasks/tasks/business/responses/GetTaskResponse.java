package com.tasks.tasks.business.responses;


import com.tasks.tasks.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetTaskResponse {
    private int id;
    private String task_name;
    private Status status;
    private String task_description;
    private Integer assignedProjectId;
    private Integer assignedUserId;

}