package com.findlog.base.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class ContatoController {
    
    @GetMapping("/contatos")
    public String contact() {
        return "contatos";
    }


}
