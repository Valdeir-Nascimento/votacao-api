package br.com.votacao.api.exception.handler;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Field {
    private String name;
    private String userMessage;
}