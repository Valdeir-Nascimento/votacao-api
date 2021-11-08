package br.com.votacao.api.exception.handler;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class StandardError {
    private Long timestamp;
    private Integer statusCode;
    private String errorMessage;
}
