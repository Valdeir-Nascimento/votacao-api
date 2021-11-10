package br.com.votacao.api.dto;

import br.com.votacao.api.model.Pauta;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter
@Getter
public class VotoDTO {

    private Long id;

    @NotBlank
    private String cpf;

    @NotNull
    private Boolean escolha;

    private Pauta pauta;
}
