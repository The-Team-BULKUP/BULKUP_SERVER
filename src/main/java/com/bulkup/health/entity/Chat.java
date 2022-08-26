package com.bulkup.health.entity;

import com.bulkup.health.entity.account.Account;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

//todo: 아직 미완
@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Chat", schema = "bulkup")
public class Chat {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "account_id")
    @ManyToOne
    private Account sender;

    @JoinColumn(name = "chat_room_id")
    @ManyToOne
    private ChatRoom chatRoom;

    @Column(length = 100)
    private String message;
}
