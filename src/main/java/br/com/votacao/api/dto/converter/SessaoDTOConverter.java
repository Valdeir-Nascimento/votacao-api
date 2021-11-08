package br.com.votacao.api.dto.converter;

import br.com.votacao.api.dto.SessaoDTO;
import br.com.votacao.api.model.Sessao;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SessaoDTOConverter {

    @Autowired
    private ModelMapper mapper;

    public SessaoDTO to(Sessao sessao) {
        return mapper.map(sessao, SessaoDTO.class);
    }

    public Sessao to(SessaoDTO sessaoDTO) {
        return mapper.map(sessaoDTO, Sessao.class);
    }

    public List<SessaoDTO> toList(List<Sessao> sessoes) {
        return sessoes.stream()
                .map(this::to)
                .collect(Collectors.toList());
    }

}
