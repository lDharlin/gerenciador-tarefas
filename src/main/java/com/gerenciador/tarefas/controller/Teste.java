package com.gerenciador.tarefas.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Teste {

    @GetMapping("/testeautenticacao")
    public String teste(){
        return "sucesso";
    }

    @GetMapping("/boasvindas")
    public String teste(@RequestParam(name = "name") String nome){
        return "Bem vindo" + nome;
    }
}
