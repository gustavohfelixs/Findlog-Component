package com.findlog.base.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.findlog.base.domain.Usuario;
import com.findlog.base.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioRepository repository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/regisster")
    public ResponseEntity<String> findUser(@RequestBody Usuario user) {

        if (!repository.findByEmail(user.getEmail()).isEmpty())
            return ResponseEntity.badRequest().build();

        user.setRole("ADMIN");
        var encondedPassword = passwordEncoder.encode(user.getSenha());

        user.setSenha(encondedPassword);

        repository.save(user);

        return ResponseEntity.ok().build();
    }

}
