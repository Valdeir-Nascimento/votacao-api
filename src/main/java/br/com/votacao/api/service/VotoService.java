package br.com.votacao.api.service;

import br.com.votacao.api.exception.SessaoInvalidaException;
import br.com.votacao.api.exception.VotoNaoEncontradoException;
import br.com.votacao.api.model.Sessao;
import br.com.votacao.api.model.Voto;
import br.com.votacao.api.repository.VotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VotoService {

    @Autowired
    private VotoRepository votoRepository;
    @Autowired
    private SessaoService sessaoService;

    public List<Voto> listar() {
        return votoRepository.findAll();
    }

    public List<Voto> buscarVotosPorPauta(Long id) {
        var votos = votoRepository.findByPautaId(id);
        if (!votos.isPresent()) {
            throw new VotoNaoEncontradoException(id);
        }
        return votos.get();
    }

    public Voto salvarVoto(Long idPauta, Long idSessao, Voto voto) {
        Sessao sessaoAtual = sessaoService.buscarSessaoPorPauta(idSessao, idPauta);
        if (idPauta.equals(sessaoAtual.getPauta().getId())) {
            throw new SessaoInvalidaException("Sessão Inválida");
        }
        voto.setPauta(sessaoAtual.getPauta());
        //TODO: Finalizar metodo
        return null;

    }

    public void excluir(Long idVoto) {
        votoRepository.deleteById(idVoto);
    }

}
