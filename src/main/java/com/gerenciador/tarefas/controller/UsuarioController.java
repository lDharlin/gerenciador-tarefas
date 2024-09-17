package com.gerenciador.tarefas.controller;

import com.gerenciador.tarefas.entity.Usuario;
import com.gerenciador.tarefas.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<String> salvarUsuario(@RequestBody Usuario usuario) {
       Usuario usuarioSalvo =  usuarioService.salvarUsuario(usuario);

       return new ResponseEntity<>("Novo usuario salvo: " + usuarioSalvo.getUsername(), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<String> atualizarUsuario(@RequestBody Usuario usuario) {
        Usuario usuarioSalvo =  usuarioService.salvarUsuario(usuario);

        return new ResponseEntity<>("Usuario atualizado: " + usuarioSalvo.getUsername(), HttpStatus.OK);
    }

    @DeleteMapping
    public void excluirUsuario(@RequestBody Usuario usuario) {
        usuarioService.excluirUsuario(usuario);
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> listarUsuariso() {
        return new ResponseEntity<>(usuarioService.obtemUsuarios(), HttpStatus.OK );
    }
}
