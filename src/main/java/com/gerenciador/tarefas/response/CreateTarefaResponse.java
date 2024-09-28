package com.gerenciador.tarefas.response;

import com.gerenciador.tarefas.entity.Usuario;
import com.gerenciador.tarefas.statustarefa.StatusTarefaEnum;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
