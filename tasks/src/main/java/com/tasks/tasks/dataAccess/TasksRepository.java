package com.tasks.tasks.dataAccess;


import com.tasks.tasks.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TasksRepository extends JpaRepository<Task, Integer> {
}