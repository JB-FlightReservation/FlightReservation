package com.joongbu.flight_reservation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

//회원가입 페이지에서 비밀번호 암호할때 사용

@Configuration
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    	http 
        .cors().disable()		//cors방지
        .csrf().disable()		//csrf방지
        .formLogin().disable()	//기본 로그인 페이지 없애기
        .headers().frameOptions().disable();
    	 return http.build();
}
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}