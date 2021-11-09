package br.com.votacao.api.service;

import br.com.votacao.api.exception.EntidadeEmUsoException;
import br.com.votacao.api.exception.PautaNaoEncontradaException;
import br.com.votacao.api.exception.SessaoNaoEncontradaException;
import br.com.votacao.api.model.Pauta;
import br.com.votacao.api.model.Sessao;
import br.com.votacao.api.repository.SessaoRepository;
import br.com.votacao.api.util.MessagesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
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
    public List<Sessao> listar() {
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
        try {
            sessaoRepository.deleteById(idSessao);
            sessaoRepository.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new SessaoNaoEncontradaException(idSessao);
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(String.format(MessagesUtil.MSG_ENTIDADE_EM_USO, idSessao));
        }
    }

    public Sessao buscarSessaoPorPauta(Long idSessao, Long idPauta) {
        return sessaoRepository
                .findByIdAndPautaId(idSessao, idPauta)
                .orElseThrow(() -> new SessaoNaoEncontradaException("Sessão e/ou Pauta não encontrada"));
    }

}
