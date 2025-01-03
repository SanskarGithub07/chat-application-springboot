package com.sanskar.chat.application.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class User {
    @Id
    @SequenceGenerator(name = "user_sequence", sequenceName = "user_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
    private Long userId;

    @NotBlank(message = "Please add username")
    @Column(unique = true, nullable = false)
    private String username;

    @Email(message = "Email is not valid", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    @NotEmpty(message = "Email cannot be empty")
    @Column(unique = true, nullable = false)
    private String email;
    private String password;
    private String profilePicture;
    private String status;

    @OneToMany(
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "sender_id",
            referencedColumnName = "userId"
    )
    private List<Message> messages;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserRoomMapping> userRoomMappings;
}
