package com.bulkup.health.config.spring_security;

import com.bulkup.health.config.jwt.JwtAccessDeniedHandler;
import com.bulkup.health.config.jwt.JwtAuthenticationEntryPoint;
import com.bulkup.health.config.jwt.JwtSecurityConfig;
import com.bulkup.health.config.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity //스프링 시큐리티 필터가 스프링 필터체인에 등록
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final TokenProvider tokenProvider;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) //세션을 사용하지 않고, STATELESS모드로 사용
        .and()
                .cors().configurationSource(corsConfigurationSource())
                .and()

            .formLogin().disable()
            .httpBasic().disable()
            .authorizeRequests()

                // 익명 접속 가능
                .antMatchers("/api/v1/reissue/**").anonymous()
                .antMatchers("/api/vi/signup/**").anonymous()

                // 권한이 있어야함
                .antMatchers("/api/vi/login/**").hasAnyRole()
                .antMatchers("/api/vi/logout/**").hasAnyRole()

                .and()
                .exceptionHandling()// 예외 처리 진입점
                .authenticationEntryPoint(jwtAuthenticationEntryPoint) // 인증 실패 진입점
                .accessDeniedHandler(jwtAccessDeniedHandler) // 인가 실패 진입점

                .and()
                //addFilter메스드가 담긴 Config 클래스 추가
                .apply(new JwtSecurityConfig(tokenProvider));
    }

    // CORS 허용 적용
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.addAllowedOriginPattern("*");
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/api/**", configuration);
        return source;
    }

}
