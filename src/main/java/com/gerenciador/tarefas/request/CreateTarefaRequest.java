package com.gerenciador.tarefas.request;

import com.gerenciador.tarefas.statustarefa.StatusTarefaEnum;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class CreateTarefaRequest {

    @Length(max = 50, message = "{create.tarefa.request.descricao.limite.cinquenta}")
    private String descricao;

    @NotBlank(message = "{create.tarefa.request.titulo.obrigatorio}")
    private String titulo;

    private StatusTarefaEnum status;
    private Long criadorId;
    private Long responsavelId;
    private int quantidadeHorasplanejadas;

}
