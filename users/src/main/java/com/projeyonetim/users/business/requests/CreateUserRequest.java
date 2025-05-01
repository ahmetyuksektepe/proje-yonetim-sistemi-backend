package com.projeyonetim.users.business.requests;

import com.projeyonetim.users.enums.Roles;
import com.projeyonetim.users.enums.UserStatus;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class CreateUserRequest {

    @NotBlank(message = "İsim boş olamaz")
    private String name;

    @NotBlank(message = "Soyisim boş olamaz")
    private String surname;

    @NotBlank(message = "Telefon boş olamaz")
    private String phone;

    @Email(message = "Geçerli bir mail giriniz")
    @NotBlank(message = "Mail boş olamaz")
    private String mail;

    @NotBlank(message = "Şifre boş olamaz")
    private String password;

    //@NotNull(message = "Rol seçilmelidir")
    private Roles role;

    //@NotNull(message = "Kullanıcı durumu belirtilmelidir")
    private UserStatus status;

    private Integer projectId;  // opsiyonel olabilir
    private Integer gorevId;
}