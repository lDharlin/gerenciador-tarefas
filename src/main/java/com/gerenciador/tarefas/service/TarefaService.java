package com.gerenciador.tarefas.service;

import com.gerenciador.tarefas.entity.Tarefa;
import com.gerenciador.tarefas.repository.ITarefaRepository;
import com.gerenciador.tarefas.request.CreateTarefaRequest;
import com.gerenciador.tarefas.statustarefa.StatusTarefaEnum;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class TarefaService {

    @Autowired
    private ITarefaRepository gerenciadorTarefasRepository;

    @Autowired
    private UsuarioService usuarioService;

    public Tarefa salvarTarefa(CreateTarefaRequest request) {

        Tarefa tarefa = Tarefa.builder()
                .quantidadeHorasEstimadas(request.getQuantidadeHorasplanejadas())
                .status(StatusTarefaEnum.CRIADA)
                .titulo(request.getTitulo())
                .descricao(request.getDescricao())
                .criador(usuarioService.obterUsuarioId(request.getCriadorId()).get())
                .build();

        return this.gerenciadorTarefasRepository.save(tarefa);
    }
}