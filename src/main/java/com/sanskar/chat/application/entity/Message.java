package com.sanskar.chat.application.entity;

import com.sanskar.chat.application.util.MessageStatus;
import com.sanskar.chat.application.util.MessageType;
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
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long messageId;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MessageType messageType;

    @Enumerated(EnumType.STRING)
    private MessageStatus messageStatus;

    @Column
    private Boolean edited = false;

    @Column
    private Boolean delivered = false;

    @Column
    private Boolean isRead = false;

    private String fileUrl;


    @ManyToOne
    @JoinColumn(name = "sender_id", nullable = false, insertable = false, updatable = false)
    private User sender;

    @ManyToOne
    @JoinColumn(name = "room_id", nullable = false, insertable = false, updatable = false)
    private ChatRoom chatRoom;


}
