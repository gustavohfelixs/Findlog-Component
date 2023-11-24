package com.findlog.base.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ServicoController {
    
    @GetMapping("/servico")
    public String getServicos() {
        return "servicos";
    }

}
