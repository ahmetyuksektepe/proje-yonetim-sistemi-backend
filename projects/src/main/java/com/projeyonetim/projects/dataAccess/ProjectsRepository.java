package com.projeyonetim.projects.dataAccess;

import com.projeyonetim.projects.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectsRepository extends JpaRepository<Project, Integer> {
}
