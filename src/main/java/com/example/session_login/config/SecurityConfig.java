package com.example.session_login.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf((auth)->auth.disable());          // 해킹 기법 중 CSRF 방어 코드 기능을 끈거
        http.httpBasic((auth)->auth.disable());     // 로그인 방식 중 HTTP BASIC 로그인 기능 끈거

        http.formLogin(auth -> auth                 // 웹 사이트에 일반적으로 사용하는 폼 로그인 방식 설정
                .defaultSuccessUrl("/home"));       // 로그인 성공하면 /home 주소로 이동시키는 코드

        http.authorizeRequests()
                .antMatchers("/user/signup", "/", "/login").permitAll()
                .antMatchers( "/ip-info").hasRole("ADMIN")
                .antMatchers("/home").authenticated()
                .anyRequest().authenticated();

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
