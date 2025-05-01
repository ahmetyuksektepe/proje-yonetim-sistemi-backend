package com.projeyonetim.users.webApi.controller;

import com.projeyonetim.users.business.abstracts.UserService;
import com.projeyonetim.users.business.requests.CreateUserRequest;
import com.projeyonetim.users.business.requests.LoginRequest;
import com.projeyonetim.users.business.requests.UpdateUserRequest;
import com.projeyonetim.users.business.responses.AuthResponse;
import com.projeyonetim.users.business.responses.GetAllUserResponse;
import com.projeyonetim.users.business.responses.GetByIdUserResponse;
import jakarta.persistence.PostRemove;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {
    private UserService userService;

    @GetMapping()
    public List<GetAllUserResponse> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public GetByIdUserResponse getUserById(@PathVariable long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/project/{projectId}")
    public List<GetByIdUserResponse> getUsersByProjectId(@PathVariable int projectId) {
        return userService.getUsersByProjectId(projectId);
    }

    @GetMapping("/task/{taskId}")
    public List<GetByIdUserResponse> getUsersByGorevId(@PathVariable int taskId) {
        return userService.getUsersByGorevId(taskId);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid LoginRequest loginRequest) {
        return ResponseEntity.ok(userService.login(loginRequest));
    }


    @PostMapping("/register")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void addUser(@RequestBody @Valid CreateUserRequest createUserRequest) {
        userService.addUser(createUserRequest);
    }

    @PutMapping
    public void updateUser(@RequestBody @Valid UpdateUserRequest updateUserRequest) {
        userService.updateUser(updateUserRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable long id) {
        userService.deleteUser(id);
    }
}