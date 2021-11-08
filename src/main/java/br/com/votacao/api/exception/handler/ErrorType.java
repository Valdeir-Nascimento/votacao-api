package br.com.votacao.api.exception.handler;

import lombok.Getter;

@Getter
public enum ErrorType {
    RECURSO_NAO_ENCONTRADO("/recurso-nao-encontrado", "Recurso não encontrado"),
    ERRO_NEGOCIO("/erro-negocio", "Violação de regra de negócio"),
    PARAMETRO_INVALIDO("/parametro-invalido", "Parâmetro inválido"),
    DADOS_INVALIDOS("/dados-invalidos", "Dados inválidos"),
    ERRO_DE_SISTEMA("/erro-de-sistema", "Erro de sistema");

    private String title;
    private String uri;

    ErrorType(String path, String uri) {
        this.uri = "https://votacao.com.br" + path;
        this.title = uri;
    }
}
