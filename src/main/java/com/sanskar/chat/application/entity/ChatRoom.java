package com.sanskar.chat.application.entity;

import com.sanskar.chat.application.util.RoomType;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roomId;

    @Column(nullable = false)
    private String roomName;

    @Enumerated(EnumType.STRING)
    private RoomType roomType;

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
