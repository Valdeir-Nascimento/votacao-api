package br.com.votacao.api.exception;

public class SessaoNaoEncontradaException extends EntidadeNaoEncontradaException {
    private static final long serialVersionUID = 1L;
    public SessaoNaoEncontradaException(String message) {
        super(message);
    }

    public SessaoNaoEncontradaException(Long idSessao) {
        this(String.format("Não existe cadastro de sessão com Id: %d", idSessao));
    }


}
