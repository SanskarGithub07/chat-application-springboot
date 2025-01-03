package com.sanskar.chat.application.service;

import com.sanskar.chat.application.entity.User;

public interface UserInterface {
    public User fetchUserByUsername(String userName);
    public User saveUser(User user);
    public User fetchUserByEmail(String email);
    public User updateProfile(String userName, User user);
}
