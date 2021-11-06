package br.com.votacao.api.service;

import br.com.votacao.api.exception.SessaoNaoEncontradaException;
import br.com.votacao.api.model.Pauta;
import br.com.votacao.api.model.Sessao;
import br.com.votacao.api.repository.SessaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SessaoService {

    @Autowired
    private SessaoRepository sessaoRepository;
    @Autowired
    private PautaService pautaService;

    @Transactional(readOnly = true)
    public List<Sessao> findAll() {
        return sessaoRepository.findAll();
    }

    @Transactional
    public Sessao criarSessao(Long idPauta, Sessao sessao) {
        Pauta pautaAtual = pautaService.buscar(idPauta);
        sessao.setPauta(pautaAtual);
        if (sessao.getMinutos() == null) {
            sessao.setMinutos(1L);
        }
        return sessaoRepository.save(sessao);
    }

    public Sessao buscar(Long idSessao) {
        return sessaoRepository
                .findById(idSessao)
                .orElseThrow(() -> new SessaoNaoEncontradaException(idSessao));
    }

    public void excluir(Long idSessao) {
        sessaoRepository.deleteById(idSessao);
    }

    public Sessao buscarSessaoPorPauta(Long idSessao, Long idPauta) {
        return sessaoRepository
                .findByIdAndPautaId(idSessao, idPauta)
                .orElseThrow(() -> new SessaoNaoEncontradaException("Sessão não encontrada"));
    }

}
