package com.bulkup.health.entity;

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

    @Column(name = "name")
    private Integer name;

    @Column(name = "crew_id")
    private Long crewId;

    @Column(name = "type_message", length = 6)
    private String typeMessage;

    @Column(name = "text_message", length = 100)
    private String textMessage;
}
