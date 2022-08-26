package com.bulkup.health.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class TelegramAlertUtil {
    private static final String baseUrl = "https://api.telegram.org/bot";
    private static String token = "5531815652:AAFjP8n-fi7bjoKjS4YjhfQFkOvVgWf8dV8";
    private static String chatId = "5763275835";


    public static void sendAlert(String message){
        log.info("sendAlert");
        String url = baseUrl + token + "/sendMessage?chat_id=" + chatId + "&text=" + message;
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getForObject(url, String.class);
    }


}
