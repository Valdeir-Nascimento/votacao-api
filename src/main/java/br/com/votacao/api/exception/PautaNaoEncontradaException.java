package br.com.votacao.api.exception;

public class PautaNaoEncontradaException extends EntidadeNaoEncontradaException {

    private static final long serialVersionUID = 1L;

    public PautaNaoEncontradaException(String message) {
        super(message);
    }

    public PautaNaoEncontradaException(Long idPauta) {
        this(String.format("Não existe cadastro de pauta com Id: %d", idPauta));
    }
}
