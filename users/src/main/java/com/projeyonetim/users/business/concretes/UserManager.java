package com.projeyonetim.users.business.concretes;

import com.projeyonetim.users.business.abstracts.UserService;
import com.projeyonetim.users.business.requests.CreateUserRequest;
import com.projeyonetim.users.business.requests.LoginRequest;
import com.projeyonetim.users.business.requests.UpdateUserRequest;
import com.projeyonetim.users.business.responses.AuthResponse;
import com.projeyonetim.users.business.responses.GetAllUserResponse;
import com.projeyonetim.users.business.responses.GetByIdUserResponse;
import com.projeyonetim.users.core.utilities.mappers.ModelMapperService;
import com.projeyonetim.users.dataAccess.UserRepository;
import com.projeyonetim.users.entities.User;
import com.projeyonetim.users.security.JwtTokenProvider;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserManager implements UserService {

    private UserRepository userRepository;
    private ModelMapperService modelMapperService;
    private JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<GetAllUserResponse> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<GetAllUserResponse> userResponses = users.stream()
                .map(user -> this.modelMapperService.forResponse()
                        .map(user, GetAllUserResponse.class))
                .toList();
        return userResponses;
    }

    @Override
    public GetByIdUserResponse getUserById(long id) {
        User user = userRepository.findById(id).orElseThrow();

        GetByIdUserResponse response = this.modelMapperService.forResponse()
                .map(user, GetByIdUserResponse.class);
        return response;
    }

    @Override
    public List<GetByIdUserResponse> getUsersByProjectId(int projectId) {
        return userRepository.findByProjectId(projectId)
                .stream()
                .map(user -> this.modelMapperService.forResponse()
                        .map(user, GetByIdUserResponse.class))
                .toList();
    }

    @Override
    public List<GetByIdUserResponse> getUsersByGorevId(int gorevId) {
        return userRepository.findByGorevId(gorevId)
                .stream()
                .map(user -> this.modelMapperService.forResponse()
                        .map(user, GetByIdUserResponse.class))
                .toList();
    }

    @Override
    public AuthResponse login(LoginRequest loginRequest ) {
        User user = userRepository.findByMail(loginRequest.getMail()).orElseThrow();
        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new RuntimeException("Şifre veya mail yanlış.");
        }

        String token = jwtTokenProvider.generateToken(user);
        return new AuthResponse(token, user.getId(), user.getRole().name());
    }

    //bu hem register
    @Override
    public void addUser(CreateUserRequest createUserRequest) {

        if(userRepository.findByMail(createUserRequest.getMail()).isPresent()) {
            throw new RuntimeException("Bu mail zaten kayıtlı.");
        }
        //rule koy
        User user = this.modelMapperService.forRequest().map(createUserRequest, User.class);
        user.setPassword(passwordEncoder.encode(createUserRequest.getPassword()));
        this.userRepository.save(user);

    }


    @Override
    public void updateUser(UpdateUserRequest updateUserRequest) {
        User user = this.modelMapperService.forRequest().map(updateUserRequest, User.class);
        this.userRepository.save(user);

    }

    @Override
    public void deleteUser(long id) {
        this.userRepository.deleteById(id);

    }
}