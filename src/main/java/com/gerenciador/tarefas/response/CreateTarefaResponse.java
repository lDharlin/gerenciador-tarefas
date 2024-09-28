package com.gerenciador.tarefas.response;

import com.gerenciador.tarefas.statustarefa.StatusTarefaEnum;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@Builder
public class CreateTarefaResponse {

    private Long id;
    private String titulo;
    private String descricao;
    private StatusTarefaEnum status;
    private String responsavel;
    private String criador;
    private int quantidadeHorasEstimadas;
    private Integer quantidadeHorasRealizada;
    private LocalTime dataCadasto;
    private LocalTime dataAtualizacao;
}
