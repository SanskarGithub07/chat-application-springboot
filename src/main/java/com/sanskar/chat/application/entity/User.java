package com.sanskar.chat.application.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @SequenceGenerator(name = "user_sequence", sequenceName = "user_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
    private Long userId;
    private String username;
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
