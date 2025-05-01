package com.projeyonetim.projects.business.requests;

import lombok.Data;

@Data
public class CreateProjectRequest {
    private String projectName;
    private String projectDate;
}