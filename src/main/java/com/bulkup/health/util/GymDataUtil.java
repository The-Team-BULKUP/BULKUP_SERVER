package com.bulkup.health.util;

import com.bulkup.health.dto.GymListByNameDto;
import com.bulkup.health.dto.GymListByPointDto;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Collections;

@Component
public class GymDataUtil {

    public static GymListByPointDto parseGymListWithPoint(double latitude, double longitude, int offset, int limit) {
        // https://www.da-gym.co.kr/api/v2/dagym/list?latitude=36.7731706&longitude=126.9312483&offset=0&limit=45
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://www.da-gym.co.kr/api/v2/dagym/list?latitude=" + latitude + "&longitude=" + longitude + "&offset=" + offset + "&limit=" + limit;

        return restTemplate.getForObject(url, GymListByPointDto.class);
    }

    public static GymListByNameDto parseGymListWithName(String gymName){
        // https://www.da-gym.co.kr/api/v2/dagym/keyword/%ED%97%AC%EC%8A%A4
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://www.da-gym.co.kr/api/v2/dagym/keyword/" + URLEncoder.encode(gymName, StandardCharsets.UTF_8);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Referer", "https://www.da-gym.co.kr/search-result/" + URLEncoder.encode(gymName, StandardCharsets.UTF_8));
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set("Authorization", "0");
        headers.set("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36");
        headers.set("x-dagym-fcm-token", "1021026368.1660492524");
        headers.set("x-dagym-platform", "pc");
        headers.set("x-dagym-version", "2.1.5");
        headers.set("Cookie", "_ga=GA1.3.1021026368.1660492524; _ba_exist=true; ch-veil-id=fdf1393a-424c-4fcc-9b12-a00985b2bec1; _gid=GA1.3.1147054521.1660996319; _ba_rand=82; _ba_initial_refer=https%3A%2F%2Fwww.google.com%2F; _ba_ssid=UQKMag39; _ba_page_ct=2022-08-20T11%3A51%3A59.366Z; _ba_initial_refer=https%3A%2F%2Fwww.google.com%2F; ch-session-27992=eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJzZXMiLCJrZXkiOiIyNzk5Mi02MmY5MWFlZmEzNDczZWVhZDQyMSIsImlhdCI6MTY2MDk5NjMyMSwiZXhwIjoxNjYzNTg4MzIxfQ.dZyAxTB4wX0Zc296tBXAc2tqC05lS1tE0hPRA4IfdlM; _gat=1; _ba_last_2nd_url=https%3A%2F%2Fwww.da-gym.co.kr%2Fsearch-result%2F%25EC%258B%25A0%25EC%25B0%25BD; _ba_page_seq=2; _ba_parent_seq=2; _ba_last_url=https%3A%2F%2Fwww.da-gym.co.kr%2Fsearch-result%2F%25ED%2597%25AC%25EC%258A%25A4; wcs_bt=s_256d423f9196:1660996349");
        headers.set("Sec-Fetch-Site", "same-origin");
        headers.set("Sec-Fetch-Mode", "cors");
        headers.set("Sec-Fetch-Dest", "empty");

        ResponseEntity<GymListByNameDto> result =
            restTemplate.exchange(
                url,
                HttpMethod.GET,
                new HttpEntity(headers),
                GymListByNameDto.class
            );

        return result.getBody();
    }



}
