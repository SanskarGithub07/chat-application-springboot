package com.sanskar.chat.application.event.listener;

import com.sanskar.chat.application.entity.User;
import com.sanskar.chat.application.event.RegistrationCompleteEvent;
import com.sanskar.chat.application.model.EmailDetails;
import com.sanskar.chat.application.service.EmailServiceImpl;
import com.sanskar.chat.application.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
public class RegistrationCompleteEventListener implements
        ApplicationListener<RegistrationCompleteEvent> {

    @Autowired
    private UserService userService;

    @Autowired
    private EmailServiceImpl emailService;

    @Override
    public void onApplicationEvent(RegistrationCompleteEvent event) {
        //Create the verification Token for the User with Link
        User user = event.getUser();
        String token = UUID.randomUUID().toString();

        userService.saveVerificationTokenForUser(token, user);
        //Send Mail to the User

        String url = event.getApplicationUrl() + "/verifyRegistration?token=" + token;

        //sendVerificationEmail()
//        log.info("Click the link to verify your account: {}", url);

        EmailDetails emailDetails = EmailDetails.builder()
                .recipient(user.getEmail())
                .subject("Account Verification")
                .msgBody("Click the link to verify your account:, {" + url + "}")
                .build();

        emailService.sendSimpleMail(emailDetails);
    }
}
