package com.sanskar.chat.application.service;

import com.sanskar.chat.application.entity.User;
import com.sanskar.chat.application.model.UserModel;

import java.util.List;

public interface UserInterface {
    public User fetchUserByUsername(String userName);
    public User saveUser(User user);
    public User registerUser(UserModel userModel);
    public List<User> fetchAllUsers();
    public User fetchUserByEmail(String email);
    public User updateProfileByUsername(String userName, User user);
    public User updateProfileByEmail(String email, User user);
    public void saveVerificationTokenForUser(String token, User user);
    public String validateVerificationToken(String token);
}
