package com.projeyonetim.projects.webApi.controller;

import com.projeyonetim.projects.business.abstracts.ProjectService;
import com.projeyonetim.projects.business.requests.CreateProjectRequest;
import com.projeyonetim.projects.business.requests.UpdateProjectRequest;
import com.projeyonetim.projects.business.responses.GetProjectResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
@AllArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @GetMapping
    public List<GetProjectResponse> getAllProjects(){
        return projectService.getAllProjects();
    }

    @GetMapping("/{id}")
    public GetProjectResponse getProjectById(@PathVariable int id){
        return projectService.getProjectById(id);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public void addProject(@RequestBody @Valid CreateProjectRequest createProjectRequest){
        projectService.addProject(createProjectRequest);
    }

    @PutMapping()
    public void updateProject (@RequestBody @Valid UpdateProjectRequest updateProjectRequest){
        projectService.updateProject(updateProjectRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteProject(@PathVariable int id) {
        projectService.deleteProject(id);
    }
}