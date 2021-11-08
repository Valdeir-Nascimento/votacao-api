package br.com.votacao.api.controller;

import br.com.votacao.api.dto.VotoDTO;
import br.com.votacao.api.dto.converter.VotoDTOConverter;
import br.com.votacao.api.model.Voto;
import br.com.votacao.api.service.VotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/pautas")
public class VotoController {

    @Autowired
    private VotoService votoService;
    @Autowired
    private VotoDTOConverter votoDTOConverter;

    @GetMapping("/sessoes/votos")
    public ResponseEntity<List<VotoDTO>> listar() {
        List<Voto> votos = votoService.listar();
        return ResponseEntity.ok().body(votoDTOConverter.toList(votos));
    }

    @GetMapping("/sessoes/votos/{idVoto}")
    public ResponseEntity<VotoDTO> findById(@PathVariable Long idVoto) {
        Voto voto = votoService.buscar(idVoto);
        return ResponseEntity.ok().body(votoDTOConverter.to(voto));
    }


}
