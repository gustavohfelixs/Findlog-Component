package com.findlog.base.controller.web;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.findlog.base.domain.Usuario;
import com.findlog.base.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class LoginController {
    
    private final UsuarioRepository repository;
    private final PasswordEncoder passwordEncoder;

    @RequestMapping("/cadastro")
    public String register(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "cadastro";
        
    }

        @PostMapping("/register")
    public String registerUser(@ModelAttribute Usuario usuario) {
        if (!repository.findByEmail(usuario.getEmail()).isEmpty()) {
            // Tratar o caso em que o e-mail já existe
            return "cadastro";
        }

        usuario.setRole("USER"); // ou "ADMIN" se necessário
        String encodedPassword = passwordEncoder.encode(usuario.getSenha());
        usuario.setSenha(encodedPassword);

        repository.save(usuario);

        return "redirect:/login"; // Redirecionar para a página de login após o registro
    }

    @RequestMapping("/login")
    public String login(@RequestParam(name = "error", required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("loginError", true);
        }
        return "login";
    }

}
