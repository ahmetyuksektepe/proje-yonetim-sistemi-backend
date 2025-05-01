package com.projeyonetim.users.entities;

import com.projeyonetim.users.enums.Roles;
import com.projeyonetim.users.enums.UserStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String surname;

    @Column(unique = true)
    private String mail;

    private String password;

    private String phone;

    @Enumerated(EnumType.STRING)
    private Roles role;

    @Enumerated(EnumType.STRING)
    private UserStatus status;

    private Integer projectId;
    private Integer gorevId;    // sadece referans
}