package com.gerenciador.tarefas.response;

import com.gerenciador.tarefas.statustarefa.StatusTarefaEnum;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ObterTarefaResponse {
    private Long id;
    private String titulo;
    private String descricao;
    private String criador;
    private String responsavel;
    private int horasEstimadas;
    private StatusTarefaEnum status;
}
