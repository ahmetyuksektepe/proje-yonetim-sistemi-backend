package com.projeyonetim.users.dataAccess;

import com.projeyonetim.users.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByProjectId(Integer projectId);
    List<User> findByGorevId(Integer gorevId);
    Optional<User> findByMail(String email);
}