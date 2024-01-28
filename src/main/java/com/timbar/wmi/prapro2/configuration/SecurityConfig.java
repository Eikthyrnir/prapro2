package com.timbar.wmi.prapro2.configuration;

import com.timbar.wmi.prapro2.configuration.jwt.JwtConfigurer;
import com.timbar.wmi.prapro2.configuration.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtTokenProvider jwtTokenProvider;

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .httpBasic()
                    .disable()
                .csrf()
                    .disable()
                .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeHttpRequests()
                    .requestMatchers(HttpMethod.POST,
                        "/api/auth/registration", "/api/auth/login")
                        .permitAll()
                    //"/" or "/api/"?
                    .requestMatchers("/", "/**")
                        .permitAll()
                .and()
                .apply(new JwtConfigurer(jwtTokenProvider))
                .and()
                .cors();

        return http.build();
    }

}

//                .cors()
//                    .configurationSource(request -> {
//                        var cors = new CorsConfiguration();
//                        cors.setAllowedOrigins(List.of("http://localhost:3000", "*", "/**"));
//                        cors.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "PATCH"));
//                        cors.setAllowedHeaders(List.of("*"));
//                        return cors;
//                    })
//                .and()