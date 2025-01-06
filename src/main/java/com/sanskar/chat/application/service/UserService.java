package com.sanskar.chat.application.service;

import com.sanskar.chat.application.entity.User;
import com.sanskar.chat.application.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserInterface{

    @Autowired
    private UserRepository userRepository;

    @Override
    public User saveUser(User user){
        return userRepository.save(user);
    }

    @Override
    public List<User> fetchAllUsers(){
        return userRepository.findAll();
    }

    @Override
    public User fetchUserByUsername(String userName){
        return userRepository.findByUsername(userName);
    }

    @Override
    public User fetchUserByEmail(String email){
        return userRepository.findByEmail(email);
    }

    @Override
    public User updateProfileByUsername(String userName, User user){
        User userDB = userRepository.findByUsername(userName);

        userDB.setEmail(user.getEmail());
        userDB.setPassword(user.getPassword());
        userDB.setProfilePicture(user.getProfilePicture());
        userDB.setUsername(user.getUsername());

        return userRepository.save(userDB);
    }

    @Override
    public User updateProfileByEmail(String email, User user){
        User userDB = userRepository.findByEmail(email);

        userDB.setEmail(user.getEmail());
        userDB.setPassword(user.getPassword());
        userDB.setProfilePicture(user.getProfilePicture());
        userDB.setUsername(user.getUsername());

        return userRepository.save(userDB);
    }


}
