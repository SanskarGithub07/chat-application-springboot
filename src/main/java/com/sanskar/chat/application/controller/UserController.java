package com.sanskar.chat.application.controller;

import com.sanskar.chat.application.entity.User;
import com.sanskar.chat.application.event.RegistrationCompleteEvent;
import com.sanskar.chat.application.model.UserModel;
import com.sanskar.chat.application.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ApplicationEventPublisher publisher;

    @PostMapping("/register")
    public String registerUser(@RequestBody UserModel userModel, final HttpServletRequest request){
        if(!userModel.getPassword().equals(userModel.getMatchingPassword())){
            return "Please enter matching passwords. Registration aborted.";
        }
        User user = userService.registerUser(userModel);
        publisher.publishEvent(new RegistrationCompleteEvent(user, applicationUrl(request)));
        return "Registration Successful.";
    }

    @GetMapping("/verifyRegistration")
    public String verifyRegistration(@RequestParam("token") String token){
        String result = userService.validateVerificationToken(token);
        if(result.equalsIgnoreCase("valid")){
            return "User Verified Successfully.";
        }
        return "Bad User";
    }

    private String applicationUrl(HttpServletRequest request) {
        return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }

    @GetMapping("/users")
    public List<User> fetchAllUsers(){
        return userService.fetchAllUsers();
    }
    @PostMapping("/users")
    public User saveUser(@Valid @RequestBody User user){
        return userService.saveUser(user);
    }

    @GetMapping("/users/search-user/name/{name}")
    public User searchForUserUsingUsername(@PathVariable("name") String userName){
        return userService.fetchUserByUsername(userName);
    }

    @GetMapping("users/search-user/email/{email}")
    public User searchForUserUsingEmail(@PathVariable("email") String email){
        return userService.fetchUserByEmail(email);
    }

    @PutMapping("/users/update-profile/name/{name}")
    public User updateProfileByUsername(@PathVariable("name") String userName, @RequestBody User user){
        return userService.updateProfileByUsername(userName, user);
    }

    @PutMapping("/users/update-profile/email/{email}")
    public User updateProfileByEmail(@PathVariable("email") String email, @RequestBody User user){
           return userService.updateProfileByEmail(email, user);
    }
}
