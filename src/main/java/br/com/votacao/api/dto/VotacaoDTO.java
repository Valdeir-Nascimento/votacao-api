package br.com.votacao.api.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class VotacaoDTO {

    private Long totalVotoSim;
    private Long totalVotoNao;
    private Integer totalVotos;
    private Integer totalSessoes;
    private PautaDTO pauta;

}
