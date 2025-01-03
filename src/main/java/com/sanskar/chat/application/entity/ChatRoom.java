package com.sanskar.chat.application.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatRoom {
    @Id
    @SequenceGenerator(name = "chatroom_sequence", sequenceName = "chatroom_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "chatroom_sequence")
    private Long roomId;
    private String roomName;
    private String roomType;
    private LocalDateTime createdAt;

    @OneToMany(
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "room_id",
            referencedColumnName = "roomId"
    )
    private List<Message> messages;

    @OneToMany(mappedBy = "chatRoom", cascade = CascadeType.ALL)
    private List<UserRoomMapping> userRoomMappings;

}
