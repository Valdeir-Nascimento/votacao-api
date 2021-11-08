package br.com.votacao.api.exception;

public class VotoRegistradoException extends EntidadeNaoEncontradaException {

    public VotoRegistradoException(String message) {
        super(message);
    }

    public VotoRegistradoException(Long idVoto) {
        this(String.format("Voto já foi registrado Id: ", idVoto));
    }
}
