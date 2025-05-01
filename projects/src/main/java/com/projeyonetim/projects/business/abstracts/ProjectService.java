package com.projeyonetim.projects.business.abstracts;

import com.projeyonetim.projects.business.requests.CreateProjectRequest;
import com.projeyonetim.projects.business.requests.UpdateProjectRequest;

import com.projeyonetim.projects.business.responses.GetProjectResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProjectService {
    List<GetProjectResponse> getAllProjects();
    GetProjectResponse getProjectById(int id);

    void addProject(CreateProjectRequest createProjectRequest);
    void updateProject(UpdateProjectRequest updateProjectRequest);
    void deleteProject(int id);
}