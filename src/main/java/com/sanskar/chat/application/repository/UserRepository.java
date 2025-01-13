package com.sanskar.chat.application.repository;

import com.sanskar.chat.application.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    public User findByUsername(String userName);
    public User findByEmail(String email);
    public List<User> findByUsernameContaining(String usernameContains);
    public List<User> findByEmailContaining(String emailContains);
}
