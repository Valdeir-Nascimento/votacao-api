package br.com.votacao.api.dto.converter;

import br.com.votacao.api.dto.PautaDTO;
import br.com.votacao.api.model.Pauta;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PautaDTOConverter {

    @Autowired
    private ModelMapper mapper;

    public PautaDTO to(Pauta pauta) {
        return mapper.map(pauta, PautaDTO.class);
    }

    public Pauta to(PautaDTO pautaDTO) {
        return mapper.map(pautaDTO, Pauta.class);
    }

    public List<PautaDTO> toList(List<Pauta> pautas) {
        return pautas.stream()
                .map(this::to)
                .collect(Collectors.toList());
    }

}
