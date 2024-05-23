package com.example.asm1java5.security;

import com.example.asm1java5.service.impl.StaffServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;

@Configuration
public class SecurityConfig {
    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(StaffServiceImpl staffService) {
        DaoAuthenticationProvider dap = new DaoAuthenticationProvider();
        dap.setUserDetailsService(staffService);
        dap.setPasswordEncoder(passwordEncoder());
        return dap;
    }


    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new AccessDeniedHandlerImpl();
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(config -> config
                        .requestMatchers(HttpMethod.GET, EndPoints.GET_ADMIN_STAFF).hasAnyRole("ADMIN" , "STAFF")
                        .requestMatchers(HttpMethod.POST, EndPoints.POST_ADMIN_STAFF).hasAnyRole("ADMIN" , "STAFF")
                        .requestMatchers(HttpMethod.POST, "/auth/login-action").permitAll()
                        .anyRequest().permitAll()
                )
                .formLogin(form -> form
                        .loginPage("/auth/login")
                        .loginProcessingUrl("/auth/login-action")
                        .defaultSuccessUrl("/sell-manager/index", true)
                        .failureUrl("/auth/login-errors")
                        .permitAll()
                )
                .logout(logout -> logout
                            .logoutUrl("/auth/logout")
                            .logoutSuccessUrl("/auth/login")
                            .permitAll()
                ).exceptionHandling( configure -> configure.accessDeniedPage("/auth/access-denied"))
                .sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))
                .csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }


}
