package com.gerenciador.tarefas.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.gerenciador.tarefas.permissoes.PermissoesEnum;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="roles")
@Data
@Getter
@Setter
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column
    @Enumerated(EnumType.STRING)
    private PermissoesEnum nome;

    @ManyToMany(mappedBy = "roles")
    @JsonBackReference
    private List<Usuario> usuarios;

}
