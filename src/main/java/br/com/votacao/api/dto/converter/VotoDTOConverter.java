package br.com.votacao.api.dto.converter;

import br.com.votacao.api.dto.VotoDTO;
import br.com.votacao.api.model.Voto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class VotoDTOConverter {

    @Autowired
    private ModelMapper mapper;

    public VotoDTO to(Voto voto) {
        return mapper.map(voto, VotoDTO.class);
    }

    public Voto to(VotoDTO votoDTO) {
        return mapper.map(votoDTO, Voto.class);
    }

    public List<VotoDTO> toList(List<Voto> votos) {
        return votos.stream()
                .map(this::to)
                .collect(Collectors.toList());
    }
}
