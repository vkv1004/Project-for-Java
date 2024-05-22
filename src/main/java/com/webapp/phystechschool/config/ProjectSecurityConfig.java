package com.webapp.phystechschool.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectSecurityConfig {
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((auth) -> auth
                        .mvcMatchers("/dashboard", "/displayProfile", "/updateProfile").authenticated()
                        .mvcMatchers("/", "/index", "/login", "/register", "/contact").permitAll()
                        .mvcMatchers("/displayMessages", "/admin/**").hasRole("ADMIN")
                        .mvcMatchers("/student/**").hasRole("STUDENT")
                )
                .headers().frameOptions().disable().and()
                .csrf().ignoringAntMatchers("/saveMsg", "/data-api/**", "/phystech/actuator/**").and()
                .httpBasic(Customizer.withDefaults())
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/dashboard").failureUrl("/login?error=true").permitAll())
                .logout(logout -> logout
                        .logoutSuccessUrl("/login?logout=true")
                        .invalidateHttpSession(true)
                        .permitAll());
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
