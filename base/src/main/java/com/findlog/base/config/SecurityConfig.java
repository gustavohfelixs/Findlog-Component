package com.findlog.base.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.findlog.base.repository.UsuarioRepository;
import com.findlog.base.service.AuthenticationProviderService;

@Configuration
public class SecurityConfig {
 
    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests((requests) -> requests.anyRequest().permitAll());
        httpSecurity.csrf(csrf -> csrf.disable());
        httpSecurity.formLogin().loginPage("/login")
        .defaultSuccessUrl("/logada", true)
        .permitAll().and().logout().permitAll().and();
        httpSecurity.httpBasic();
        
        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider (UsuarioRepository usuarioRepository) {
        return new AuthenticationProviderService(usuarioRepository);
    }
}
