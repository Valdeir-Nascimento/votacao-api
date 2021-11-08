package br.com.votacao.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class SessaoDTO {

    private Long id;
    private LocalDateTime dataInicio;
    private Long minutos;
    private PautaDTO pauta;

}
