package br.com.votacao.api.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class PautaDTO {

    private Long id;

    @NotBlank
    private String nome;


}
