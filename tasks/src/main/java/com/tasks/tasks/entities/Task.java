package com.tasks.tasks.entities;


import com.tasks.tasks.enums.Status;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "gorevler")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "task_name")
    private String task_name;
    @Column(name = "task_status")
    @Enumerated(EnumType.STRING)
    private Status status;
    @Column(name = "description")
    private String description;

    @Column(name = "project_id")
    private Integer assignedProjectId;
    @Column(name = "assigned_user_id")
    private Integer assignedUserId;

}