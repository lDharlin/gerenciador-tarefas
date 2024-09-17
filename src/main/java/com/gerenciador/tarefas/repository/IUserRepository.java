package com.gerenciador.tarefas.repository;

import com.gerenciador.tarefas.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<Usuario, Long> {

    public Usuario findByUsername(String username);
}
