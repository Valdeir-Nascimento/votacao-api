package br.com.votacao.api.exception;

public class SessaoFinalizadaException extends EntidadeNaoEncontradaException {
    public SessaoFinalizadaException(String message) {
        super(message);
    }


    public SessaoFinalizadaException(Long idSessao) {
        this(String.format("Sessão com %d foi finalizada", idSessao));
    }
}
