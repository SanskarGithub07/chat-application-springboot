package com.sanskar.chat.application.repository;

import com.sanskar.chat.application.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    public User findByUsername(String userName);

    public User findByEmail(String email);
}
