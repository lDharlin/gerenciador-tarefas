package com.gerenciador.tarefas.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@Builder
public class ErrorResponse {

    private String status;
    private Map<String,String> errors;
}
