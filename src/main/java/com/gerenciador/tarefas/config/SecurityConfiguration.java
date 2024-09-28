package com.gerenciador.tarefas.config;

import com.gerenciador.tarefas.filter.AutenticacaoFilter;
import com.gerenciador.tarefas.filter.LoginFilter;
import com.gerenciador.tarefas.permissoes.PermissoesEnum;
import com.gerenciador.tarefas.service.UsuarioAutenticadoService;
import jakarta.servlet.DispatcherType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration{

    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;

    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws  Exception {

        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests( auth -> {
                    auth.requestMatchers("/login").permitAll()
                            .dispatcherTypeMatchers(DispatcherType.FORWARD, DispatcherType.ERROR).permitAll()
                            .requestMatchers(HttpMethod.GET, "/boasvindas").permitAll()
                            .requestMatchers(HttpMethod.GET, "/testeautenticacao").hasAuthority(PermissoesEnum.ADMINISTRADOR.toString())
                            .requestMatchers(HttpMethod.GET, "/usuarios").hasAuthority(PermissoesEnum.USUARIO.toString())
                            .requestMatchers(HttpMethod.POST, "/usuarios").hasAuthority(PermissoesEnum.ADMINISTRADOR.toString())
                            .requestMatchers(HttpMethod.POST, "/gerenciador-tarefas").hasAuthority(PermissoesEnum.ADMINISTRADOR.toString())
                            .requestMatchers(HttpMethod.POST,"/tarefas").hasAuthority(PermissoesEnum.ADMINISTRADOR.toString())
                            .requestMatchers(HttpMethod.DELETE,"/tarefas").hasAuthority(PermissoesEnum.ADMINISTRADOR.toString())
                            .requestMatchers(HttpMethod.PUT,"/tarefas").hasAuthority(PermissoesEnum.ADMINISTRADOR.toString())
                            .requestMatchers(HttpMethod.GET,"/tarefas").hasAuthority(PermissoesEnum.USUARIO.toString())
                            .anyRequest()
                            .authenticated();
                });

        http.addFilterBefore(new LoginFilter("/login", authenticationConfiguration.getAuthenticationManager()), UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(new AutenticacaoFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}