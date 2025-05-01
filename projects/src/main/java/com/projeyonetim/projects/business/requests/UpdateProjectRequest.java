package com.projeyonetim.projects.business.requests;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateProjectRequest {
    @NotNull
    private int id;
    private String projectName;
    private String projectDate;
}