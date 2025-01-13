package com.sanskar.chat.application.service;

import com.sanskar.chat.application.entity.User;
import com.sanskar.chat.application.entity.VerificationToken;
import com.sanskar.chat.application.model.UserModel;
import com.sanskar.chat.application.repository.UserRepository;
import com.sanskar.chat.application.repository.VerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

@Service
public class UserService implements UserInterface{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private VerificationTokenRepository verificationTokenRepository;

    @Override
    public User saveUser(User user){
        return userRepository.save(user);
    }

    @Override
    public User registerUser(UserModel userModel){
        User user = new User();
        user.setEmail(userModel.getEmail());
        user.setUsername(userModel.getUserName());
        user.setPassword(passwordEncoder.encode(userModel.getPassword()));
        user.setStatus("Offline");
        user.setProfilePicture(userModel.getProfilePicture());

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

    @Override
    public void saveVerificationTokenForUser(String token, User user) {
        VerificationToken verificationToken = new VerificationToken(user, token);
        verificationTokenRepository.save(verificationToken);
    }

    @Override
    public String validateVerificationToken(String token) {
        VerificationToken verificationToken = verificationTokenRepository.findByToken(token);
        if(verificationToken == null){
            return "invalid";
        }

        User user = verificationToken.getUser();
        Calendar cal = Calendar.getInstance();

        if((verificationToken.getExpirationTime().getTime() - cal.getTime().getTime()) <= 0){
            verificationTokenRepository.delete(verificationToken);
            return "expired";
        }

        user.setEnabled(true);
        userRepository.save(user);
        return "valid";
    }

    @Override
    public List<User> fetchUserByUsernameContaining(String usernameContains){
        return userRepository.findByUsernameContaining(usernameContains);
    }

    @Override
    public List<User> fetchUserByEmailContaining(String emailContains) {
        return userRepository.findByEmailContaining(emailContains);
    }
}
