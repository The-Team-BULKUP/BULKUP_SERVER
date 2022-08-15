package com.bulkup.health.entity;

import com.bulkup.health.entity.account.Trainer;
import com.bulkup.health.entity.account.User;
import com.bulkup.health.entity.party.PartyAlone;
import com.bulkup.health.entity.party.PartyCrew;
import org.junit.jupiter.api.Test;

public class SingleTableStrategyTests {

    @Test
    void AccountEntity_트레이너_싱글테이블전략_테스트() throws Exception {
        //given
        Trainer trainer = new Trainer();
        //when
        String discriminatorValue = trainer.getDiscriminatorValue();
        //then
        assert discriminatorValue.equals("ROLE_TRAINER");
    }

    @Test
    void AccountEntity_일반회원_싱글테이블전략_테스트() throws Exception {
        //given
        User user = new User();
        //when
        String discriminatorValue = user.getDiscriminatorValue();
        //then
        assert discriminatorValue.equals("ROLE_USER");
    }

    @Test
    void PartyEntity_일대일피티_싱글테이블전략_테스트() throws Exception {
        //given
        PartyAlone partyAlone = new PartyAlone();
        //when
        String discriminatorValue = partyAlone.getDiscriminatorValue();
        //then
        assert discriminatorValue.equals("alone");
    }

    @Test
    void PartyEntity_다대다피티_싱글테이블전략_테스트() throws Exception {
        //given
        PartyCrew partyCrew = new PartyCrew();
        //when
        String discriminatorValue = partyCrew.getDiscriminatorValue();
        //then
        assert discriminatorValue.equals("crew");
    }
}
