package br.com.votacao.api.repository;

import br.com.votacao.api.model.Voto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VotoRepository extends JpaRepository<Voto, Long> {

    Optional<Voto> findByCpf(String cpf);

    Optional<List<Voto>> findByPautaId(Long id);

    Optional<Voto> findByCpfAndPautaId(String cpf, Long idPauta);

}
