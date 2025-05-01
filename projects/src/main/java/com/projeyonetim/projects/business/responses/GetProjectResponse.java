package com.projeyonetim.projects.business.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetProjectResponse {
    private int id;
    private String projectName;
    private String projectDate;
}