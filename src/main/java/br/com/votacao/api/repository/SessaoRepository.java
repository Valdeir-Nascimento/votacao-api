package br.com.votacao.api.repository;

import br.com.votacao.api.model.Sessao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SessaoRepository extends JpaRepository<Sessao, Long> {

    Optional<List<Sessao>> findByPautaId(Long id);

    Optional<Sessao> findByIdAndPautaId(Long idSessao, Long idPauta);

    Integer countByPautaId(Long id);

}
