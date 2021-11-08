package br.com.votacao.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class SessaoDTO {

    private Long id;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDateTime dataInicio;
    private Long minutos;
    private PautaDTO pauta;

}
