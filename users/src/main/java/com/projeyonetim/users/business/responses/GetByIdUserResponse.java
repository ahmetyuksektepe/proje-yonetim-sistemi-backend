package com.projeyonetim.users.business.responses;

import com.projeyonetim.users.enums.Roles;
import com.projeyonetim.users.enums.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetByIdUserResponse {
    private int id;
    private String name;
    private String surname;
    private String mail;
    private String phone;
    private Roles role;
    private UserStatus status;
    private Integer projectId;
    private Integer gorevId;
}