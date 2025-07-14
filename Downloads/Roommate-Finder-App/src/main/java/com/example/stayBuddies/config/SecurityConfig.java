package com.example.stayBuddies.config;

import com.example.stayBuddies.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    @Autowired
    private final UserService userService;
    @Autowired
    private final PasswordEncoder passwordEncoder; // inject password Encoder

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService);
        auth.setPasswordEncoder(passwordEncoder);

        return auth;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorize -> authorize.requestMatchers
                                (   "/admins/**",
                                        "/admin/**",
                                        "/dashboard/**",
//                                "/matches",
//                                "/matches/**",
                                        "/quiz/**",
                                        "/students/**",
                                        "/new",
                                        "/save",
                                        "/edit/**",
                                        "/delete/**",
                                        "/view/**",
                                        "/signup/**",
                                        "/signup-success",
                                        "/css/**",
                                        "/js/**",
                                        "/img/**",
                                        "/webjars/**",
                                        "/favicon.ico",
                                        "/login",
                                        "/static/assets/**",
                                        "/doctors/**",
                                        "/edit/**",
                                        "/delete/**",
                                        "/profile/**"
                                )
                        .permitAll()
                        .anyRequest().authenticated()).formLogin(form -> form.loginPage("/login")
                        .loginProcessingUrl("/authenticateTheUser")
                        //.defaultSuccessUrl("/home", true)
                        .permitAll())
                .logout(LogoutConfigurer::permitAll)
                .exceptionHandling(exception -> exception.accessDeniedPage("/access-denied"));
        return http.build();
    }
}