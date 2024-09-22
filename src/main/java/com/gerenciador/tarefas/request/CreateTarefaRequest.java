package com.gerenciador.tarefas.request;

import com.gerenciador.tarefas.statustarefa.StatusTarefaEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateTarefaRequest {

    private String descricao;
    private String titulo;
    private StatusTarefaEnum status;
    private Long criadorId;
    private Long responsavelId;
    private int quantidadeHorasplanejadas;

}
