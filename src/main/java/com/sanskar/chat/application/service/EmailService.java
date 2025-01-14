package com.sanskar.chat.application.service;

import com.sanskar.chat.application.model.EmailDetails;

public interface EmailService {
    String sendSimpleMail(EmailDetails emailDetails);
}
