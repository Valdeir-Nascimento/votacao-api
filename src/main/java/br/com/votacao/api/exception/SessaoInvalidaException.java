package br.com.votacao.api.exception;

public class SessaoInvalidaException extends EntidadeNaoEncontradaException {
    private static final long serialVersionUID = 1L;
    public SessaoInvalidaException(String message) {
        super(message);
    }
}
