package com.bulkup.health.entity.party;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor
@DiscriminatorValue("alone")
public class PartyAlone extends Party{

}
