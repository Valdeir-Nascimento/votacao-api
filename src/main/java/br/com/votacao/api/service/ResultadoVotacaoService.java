package br.com.votacao.api.service;

import br.com.votacao.api.dto.PautaDTO;
import br.com.votacao.api.dto.VotacaoDTO;
import br.com.votacao.api.dto.converter.PautaDTOConverter;
import br.com.votacao.api.exception.PautaNaoEncontradaException;
import br.com.votacao.api.exception.VotoRegistradoException;
import br.com.votacao.api.model.Voto;
import br.com.votacao.api.repository.SessaoRepository;
import br.com.votacao.api.repository.VotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ResultadoVotacaoService {

    @Autowired
    private VotoRepository votoRepository;
    @Autowired
    private SessaoRepository sessaoRepository;
    @Autowired
    private PautaDTOConverter pautaDTOConverter;

    @Transactional
    public Voto salvar(Voto voto) {
        verificarVotoExistente(voto);
        return votoRepository.save(voto);
    }

    public VotacaoDTO obterResultadoVotacao(Long id) {
        VotacaoDTO votacaoDTO = getVotacaoPauta(id);
        return votacaoDTO;
    }

    private VotacaoDTO getVotacaoPauta(Long id) {
        var votosNaPauta = votoRepository.findByPautaId(id);
        if (!votosNaPauta.isPresent() || votosNaPauta.get().isEmpty()) {
            throw new PautaNaoEncontradaException(id);
        }

        PautaDTO pauta = pautaDTOConverter.to(votosNaPauta.get().iterator().next().getPauta());

        var totalVotosSim = votosNaPauta.get()
                .stream()
                .filter(v -> Boolean.TRUE.equals(v.getEscolha()))
                .count();

        var total = votosNaPauta.get().size();

        return VotacaoDTO.builder()
                .pauta(pauta)
                .totalVotoSim(totalVotosSim)
                .totalVotoNao(total - totalVotosSim)
                .totalVotos(total)
                .totalSessoes(sessaoRepository.countByPautaId(id))
                .build();

    }

    private void verificarVotoExistente(Voto voto) {
        var votou = votoRepository.findByCpf(voto.getCpf());
        if (votou.isPresent() && (voto == null || isVoto(voto, votou.get()))) {
            throw new VotoRegistradoException("Usuário já votou!");
        }
    }

    private boolean isVoto(Voto voto, Voto votouNaPauta) {
        return voto.getId() != null && !votouNaPauta.equals(voto);
    }


}
