package com.sanskar.chat.application.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserModel {
    private String userName;
    private String email;
    private String password;
    private String matchingPassword;
    private String profilePicture;
}
