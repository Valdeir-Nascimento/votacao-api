package br.com.votacao.api.exception;

public class VotoNaoEncontradoException extends EntidadeNaoEncontradaException {

    private static final long serialVersionUID = 1L;

    public VotoNaoEncontradoException(String message) {
        super(message);
    }

    public VotoNaoEncontradoException(Long idVoto) {
        this(String.format("Não existe cadastro de voto com Id: %d", idVoto));
    }
}
