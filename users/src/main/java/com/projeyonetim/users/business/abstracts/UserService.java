package com.projeyonetim.users.business.abstracts;

import com.projeyonetim.users.business.requests.CreateUserRequest;
import com.projeyonetim.users.business.requests.LoginRequest;
import com.projeyonetim.users.business.requests.UpdateUserRequest;
import com.projeyonetim.users.business.responses.AuthResponse;
import com.projeyonetim.users.business.responses.GetAllUserResponse;
import com.projeyonetim.users.business.responses.GetByIdUserResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    List<GetAllUserResponse> getAllUsers();
    GetByIdUserResponse getUserById(long id);
    List<GetByIdUserResponse> getUsersByProjectId(int projectId);
    List<GetByIdUserResponse> getUsersByGorevId(int gorevId);
    AuthResponse login(LoginRequest loginRequest);

    void addUser(CreateUserRequest createUserRequest);
    //void loginRequest (LoginRequest loginRequest);
    void updateUser(UpdateUserRequest updateUserRequest);
    void deleteUser(long id);
}