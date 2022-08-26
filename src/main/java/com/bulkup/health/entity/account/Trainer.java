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

    @Column(name = "price_per")
    private Integer pricePer;

    @Column(name = "gym_code")
    private String gymCode;

    @Column(name = "gym_lat")
    private Double gymLat;

    @Column(name = "gym_lng")
    private Double gymLng;

    @Column(name = "gym_name")
    private String gymName;

    @Column(name = "gym_photo")
    private String gymPhoto;

    @Column(name = "profile_img")
    private byte[] profileImg;

    @Column(name = "id_card_img")
    private byte[] idCardImg;

    @Column(name = "career_proof_img")
    private byte[] careerProofImg;

}
