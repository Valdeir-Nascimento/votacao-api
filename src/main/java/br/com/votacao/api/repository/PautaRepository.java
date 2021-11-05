package br.com.votacao.api.repository;

import br.com.votacao.api.model.Pauta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PautaRepository extends JpaRepository<Pauta, Long> {

}
