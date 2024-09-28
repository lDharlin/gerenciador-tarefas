package com.gerenciador.tarefas.controller;

import com.gerenciador.tarefas.entity.Tarefa;
import com.gerenciador.tarefas.request.CreateTarefaRequest;
import com.gerenciador.tarefas.request.UpdateTarefaRequest;
import com.gerenciador.tarefas.response.CreateTarefaResponse;
import com.gerenciador.tarefas.response.ObterTarefaResponse;
import com.gerenciador.tarefas.response.ObterTarefasPaginadasResponse;
import com.gerenciador.tarefas.response.UpdateTarefaResponse;
import com.gerenciador.tarefas.service.TarefaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tarefas")
public class TarefasController {

    @Autowired
    private TarefaService tarefaService;

    @PostMapping
    public ResponseEntity<CreateTarefaResponse> salvarTarefa(@RequestBody CreateTarefaRequest createTarefaRequest) {
        Tarefa tarefaSalva =  tarefaService.salvarTarefa(createTarefaRequest);

        CreateTarefaResponse response = CreateTarefaResponse.builder()
                .id(tarefaSalva.getId())
                .titulo(tarefaSalva.getTitulo())
                .descricao(tarefaSalva.getDescricao())
                .criador(tarefaSalva.getCriador().getUsername())
                .build();

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<UpdateTarefaResponse> atualizarTarefa(@PathVariable Long id,@Valid @RequestBody UpdateTarefaRequest request) {

        Tarefa tarefaAtualizada = tarefaService.atualizarTarefa(id, request);

        UpdateTarefaResponse response = UpdateTarefaResponse
                .builder()
                .id(tarefaAtualizada.getId())
                .titulo(tarefaAtualizada.getTitulo())
                .descricao(tarefaAtualizada.getDescricao())
                .criador(tarefaAtualizada.getCriador().getUsername())
                .quantidadeHorasEstimadas(tarefaAtualizada.getQuantidadeHorasEstimadas())
                .status(tarefaAtualizada.getStatus().toString())
                .responsavel(tarefaAtualizada.getResponsavel().getUsername())
                .quantidadeHorasRealizada(tarefaAtualizada.getQuantidadeHorasRealizada() != null ? tarefaAtualizada.getQuantidadeHorasRealizada() : null)
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}")
    public void excluirTarefa(@PathVariable Long id) {
        tarefaService.excluirTarefa(id);
    }

    @GetMapping
    public ResponseEntity<ObterTarefasPaginadasResponse> getTarefas(
            @RequestParam (required = false) String titulo,
            @RequestParam (defaultValue = "0") int page,
            @RequestParam  (defaultValue = "3") int pageSize ) {

        Page<Tarefa> tarefasPaginadas = titulo != null ? this.tarefaService.obtemTarefasPorTitulo(titulo, PageRequest.of(page, pageSize))
                : this.tarefaService.obtemTodasTarefas(PageRequest.of(page, pageSize));



        List<ObterTarefaResponse> tarefas = tarefasPaginadas.getContent()
                .stream()
                .map(tarefa -> {
                    return ObterTarefaResponse.builder()
                            .id(tarefa.getId())
                            .titulo(tarefa.getTitulo())
                            .descricao(tarefa.getDescricao())
                            .criador(tarefa.getCriador().getUsername())
                            .responsavel(tarefa.getResponsavel() != null ? tarefa.getResponsavel().getUsername() : "NAO_ATRIBUIDO")
                            .horasEstimadas(tarefa.getQuantidadeHorasEstimadas())
                            .status(tarefa.getStatus())
                    .build();
        })
                .toList();

        ObterTarefasPaginadasResponse response = ObterTarefasPaginadasResponse.builder()
                .paginaAtual(tarefasPaginadas.getNumber())
                .totalPaginas(tarefasPaginadas.getTotalPages())
                .totalResultados(tarefasPaginadas.getTotalElements())
                .tarefas(tarefas)
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);

    }

}
