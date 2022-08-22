package com.bulkup.health.entity.account;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor
@DiscriminatorValue("ROLE_TRAINER")
public class Trainer extends Account {
    @Column(name = "price_per", nullable = false)
    private Integer pricePer;

    @Column(name = "gym_code", nullable = false)
    private String gymCode;

    @Column(name = "gym_lat", nullable = false)
    private Double gymLat;

    @Column(name = "gym_lng", nullable = false)
    private Double gymLng;

    @Column(name = "gym_name", nullable = false)
    private String gymName;

    @Column(name = "gym_photo", nullable = false)
    private String gymPhoto;

    @Column(name = "profile_img", nullable = false)
    private byte[] profileImg;

    @Column(name = "id_card_img", nullable = false)
    private byte[] idCardImg;

    @Column(name = "career_proof_img", nullable = false)
    private byte[] careerProofImg;

}
