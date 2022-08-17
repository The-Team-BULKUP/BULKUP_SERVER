package com.bulkup.health.util;

import com.bulkup.health.dto.GymListDto;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GymDataUtilTest {

    @Test
    void 헬스장_파싱테스트_성공() throws Exception {
        //given
        double latitude = 36.7731706;
        double longitude = 126.9312483;
        int offset = 0;
        int limit = 45;

        //when
        GymListDto gymListDto = GymDataUtil.parseGymList(latitude, longitude, offset, limit);

        //then
        assertNotNull(gymListDto);
        assertEquals(gymListDto.getCode(), 0);
        assertTrue(gymListDto.getResult().getCenterList().size() > 0);
    }

}