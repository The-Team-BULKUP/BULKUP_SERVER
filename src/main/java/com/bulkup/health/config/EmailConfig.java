//package com.bulkup.health.config;
//
//import io.lettuce.core.dynamic.annotation.Value;
//import org.slf4j.LoggerFactory;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.JavaMailSenderImpl;
//
//import java.io.IOException;
//import java.util.Properties;
//import java.util.logging.Logger;
//
//@Configuration
//@PropertySource("classpath:mail/email.properties")
//public class EmailConfig {
//
//
//    public EmailConfig() throws IOException {
//
//    }
//
//
//
//    @Bean
//    public JavaMailSender javaMailSender(){
//        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
//        Properties properties = new Properties();
//        properties.put("mail.transport.protocol", "SMTP");
//        properties.put("mail.smtp.auth", auth);
//        properties.put("mail.smtp.starttls.enable", starttls);
//        properties.put("mail.smtp.debug", debug);
//
//        mailSender.setHost(host);
//        mailSender.setUsername("jiheon.unidev@gmail.com");
//        mailSender.setPassword(password);
//        mailSender.setPort(port);
//        mailSender.setJavaMailProperties(properties);
//        mailSender.setDefaultEncoding(encoding);
//
//        return mailSender;
//    }
//}
