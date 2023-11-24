package com.findlog.base.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.findlog.base.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AuthenticationProviderService implements AuthenticationProvider {

    private final UsuarioRepository repository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        var email = authentication.getName();
        var senha = authentication.getCredentials().toString();
        
        var customerFromDb = repository.findByEmail(email);

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return customerFromDb.stream()
            .findFirst()
            .map(customer -> {
                if (encoder.matches(senha, customer.getSenha())) {
                    List<GrantedAuthority> authorities = new ArrayList<>();
                    authorities.add(new SimpleGrantedAuthority(customer.getRole()));
                    System.out.println("USUARIO AUTENTICADO!!");
                    return new UsernamePasswordAuthenticationToken(email, senha, authorities);
                } else {
                    throw new BadCredentialsException("Invalid Password for user");
                }
            }).orElseThrow(() -> new BadCredentialsException("User Not Registred"));
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
    
}
