package com.gerenciador.tarefas.filter;

import com.gerenciador.tarefas.service.AutenticacaoService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

public class AutenticacaoFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        Authentication authentication = AutenticacaoService.obterAutenticacao((HttpServletRequest) servletRequest);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
