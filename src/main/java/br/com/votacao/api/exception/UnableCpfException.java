package br.com.votacao.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UnableCpfException extends EntidadeNaoEncontradaException {

    public UnableCpfException(String message) {
        super("Não pode Votar");
    }
}
