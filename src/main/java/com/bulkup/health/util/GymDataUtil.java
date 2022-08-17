package com.bulkup.health.util;

import com.bulkup.health.dto.GymListDto;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class GymDataUtil {

    public static GymListDto parseGymList(double latitude, double longitude, int offset, int limit) {
        //https://www.da-gym.co.kr/api/v2/dagym/list?latitude=36.7731706&longitude=126.9312483&offset=0&limit=45
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://www.da-gym.co.kr/api/v2/dagym/list?latitude=" + latitude + "&longitude=" + longitude + "&offset=" + offset + "&limit=" + limit;

        return restTemplate.getForObject(url, GymListDto.class);
    }

}
