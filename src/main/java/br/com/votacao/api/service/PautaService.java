package br.com.votacao.api.service;

import br.com.votacao.api.exception.PautaNaoEncontradaException;
import br.com.votacao.api.model.Pauta;
import br.com.votacao.api.repository.PautaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PautaService {

    @Autowired
    private PautaRepository pautaRepository;

    public List<Pauta> listar() {
        return pautaRepository.findAll();
    }

    @Transactional
    public Pauta salvar(Pauta pauta) {
        return pautaRepository.save(pauta);
    }

    public Pauta buscar(Long idPauta) {
        return pautaRepository
                .findById(idPauta)
                .orElseThrow(() -> new PautaNaoEncontradaException(idPauta));
    }

    public void excluir(Long idPauta) {
        pautaRepository.deleteById(idPauta);
    }

}
