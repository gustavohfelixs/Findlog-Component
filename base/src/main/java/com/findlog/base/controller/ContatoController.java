package com.findlog.base.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContatoController {
    
    @GetMapping("/contact")
    public String contact() {
        return "Contato";
    }


}
