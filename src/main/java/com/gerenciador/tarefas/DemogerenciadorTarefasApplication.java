package com.gerenciador.tarefas;

import com.gerenciador.tarefas.entity.Role;
import com.gerenciador.tarefas.entity.Usuario;
import com.gerenciador.tarefas.permissoes.PermissoesEnum;
import com.gerenciador.tarefas.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class DemogerenciadorTarefasApplication implements CommandLineRunner {

	@Autowired
	private UsuarioService usuarioService;

	public static void main(String[] args) {
		SpringApplication.run(DemogerenciadorTarefasApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Usuario usuario = new Usuario();
		usuario.setUsername("luan");
		usuario.setPassword("123456");

		List<Role> roles = new ArrayList<>();

		Role roleAdm = new Role();
		roleAdm.setNome(PermissoesEnum.ADMINISTRADOR);

		Role roleUser = new Role();
		roleUser.setNome(PermissoesEnum.USUARIO);

		roles.add(roleAdm);
		roles.add(roleUser);

		usuario.setRoles(roles);

		usuarioService.salvarUsuario(usuario);
	}
}
