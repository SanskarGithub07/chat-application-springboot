package com.sanskar.chat.application.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRoomMapping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mappingId;
    private LocalDateTime joinedAt;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id", referencedColumnName = "userId", nullable = false)
    private User user;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "room_id", referencedColumnName = "roomId", nullable = false)
    private ChatRoom chatRoom;
}
