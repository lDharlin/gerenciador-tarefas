package com.gerenciador.tarefas.service;

import com.gerenciador.tarefas.entity.Tarefa;
import com.gerenciador.tarefas.repository.ITarefaRepository;
import com.gerenciador.tarefas.request.CreateTarefaRequest;
import com.gerenciador.tarefas.request.UpdateTarefaRequest;
import com.gerenciador.tarefas.statustarefa.StatusTarefaEnum;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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


    public Tarefa atualizarTarefa(Long id, UpdateTarefaRequest request) {

        Tarefa tarefa = this.gerenciadorTarefasRepository.findById(id).get();

       tarefa.setStatus(request.getStatus());
       tarefa.setDescricao(request.getDescricao());
       tarefa.setTitulo(request.getTitulo());
       tarefa.setQuantidadeHorasEstimadas(request.getQuantidadeHorasplanejadas());
       tarefa.setQuantidadeHorasRealizada(request.getQuantidadeHorasRealizadas());
       tarefa.setResponsavel(usuarioService.obterUsuarioId(request.getResponsavelId()).get());

       this.gerenciadorTarefasRepository.save(tarefa);
       return tarefa;
    }

    public void excluirTarefa(Long id){
        this.gerenciadorTarefasRepository.deleteById(id);
    }

    public Page<Tarefa> obtemTarefasPorTitulo(String titulo, Pageable page){
        return this.gerenciadorTarefasRepository.findByTituloContaining(titulo, page);
    }

    public Page<Tarefa> obtemTodasTarefas(Pageable page){
        return this.gerenciadorTarefasRepository.findAll(page);
    }

}