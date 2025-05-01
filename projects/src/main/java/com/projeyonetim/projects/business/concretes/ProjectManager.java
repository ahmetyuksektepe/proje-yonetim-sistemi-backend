package com.projeyonetim.projects.business.concretes;

import com.projeyonetim.projects.business.abstracts.ProjectService;
import com.projeyonetim.projects.business.requests.CreateProjectRequest;
import com.projeyonetim.projects.business.requests.UpdateProjectRequest;

import com.projeyonetim.projects.business.responses.GetProjectResponse;
import com.projeyonetim.projects.core.utilities.mappers.ModelMapperService;
import com.projeyonetim.projects.dataAccess.ProjectsRepository;
import com.projeyonetim.projects.entities.Project;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProjectManager implements ProjectService {
    private final ProjectsRepository projectsRepository;
    private final ModelMapperService modelMapperService;


    @Override
    public List<GetProjectResponse> getAllProjects() {
        List<Project> projects = projectsRepository.findAll();
        List<GetProjectResponse> projectsResponses = projects.stream()
                .map(user -> this.modelMapperService.forResponse()
                        .map(user, GetProjectResponse.class))
                .toList();
        return projectsResponses;
    }

    @Override
    public GetProjectResponse getProjectById(int id) {
        Project project = projectsRepository.findById(id).orElseThrow();

        GetProjectResponse response = this.modelMapperService.forResponse()
                .map(project, GetProjectResponse.class);
        return response;
    }

    @Override
    public void addProject(CreateProjectRequest createProjectRequest) {
        Project project = this.modelMapperService.forRequest().map(createProjectRequest, Project.class);
        this.projectsRepository.save(project);

    }

    @Override
    public void updateProject(UpdateProjectRequest updateProjectRequest) {
        Project project = this.modelMapperService.forRequest().map(updateProjectRequest, Project.class);
        this.projectsRepository.save(project);
    }

    @Override
    public void deleteProject(int id) {
        this.projectsRepository.deleteById(id);
    }

}