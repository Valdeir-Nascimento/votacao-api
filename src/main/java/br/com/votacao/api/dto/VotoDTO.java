package br.com.votacao.api.dto;

import br.com.votacao.api.model.Pauta;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class VotoDTO {
    private Long id;
    private String cpf;
    private Boolean escolha;
    private Pauta pauta;
}
