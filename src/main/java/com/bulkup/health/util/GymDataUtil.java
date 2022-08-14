package com.bulkup.health.util;

import com.bulkup.health.dto.GymListDto;
import org.springframework.stereotype.Component;

@Component
public class GymDataUtil {

    public static GymListDto parseGymList(double latitude, double longitude, int offset, int limit) {
        //https://www.da-gym.co.kr/api/v2/dagym/list?latitude=36.7731706&longitude=126.9312483&offset=0&limit=45
        //TODO: parse here
        return null;
    }

}
