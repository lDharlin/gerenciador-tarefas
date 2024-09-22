package com.gerenciador.tarefas.controller;

import com.gerenciador.tarefas.entity.Tarefa;
import com.gerenciador.tarefas.request.CreateTarefaRequest;
import com.gerenciador.tarefas.service.TarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tarefas")
public class TarefasController {

    @Autowired
    private TarefaService tarefaService;

    @PostMapping
    public ResponseEntity<Tarefa> salvarTarefa(@RequestBody CreateTarefaRequest createTarefaRequest) {
        Tarefa tarefaSalva =  tarefaService.salvarTarefa(createTarefaRequest);

        return new ResponseEntity<>(tarefaSalva, HttpStatus.CREATED);
    }

}
