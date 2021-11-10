package br.com.votacao.api.controller;

import br.com.votacao.api.dto.VotoDTO;
import br.com.votacao.api.dto.converter.VotoDTOConverter;
import br.com.votacao.api.event.RecursoCriadoEvent;
import br.com.votacao.api.model.Voto;
import br.com.votacao.api.service.VotoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/v1/pautas")
@Api(value = "Voto")
public class VotoController {

    @Autowired
    private VotoService votoService;
    @Autowired
    private VotoDTOConverter votoDTOConverter;
    @Autowired
    private ApplicationEventPublisher publisher;

    @ApiOperation(value = "Listar Votos")
    @GetMapping("/sessoes/votos")
    public ResponseEntity<List<VotoDTO>> listar() {
        List<Voto> votos = votoService.listar();
        return ResponseEntity.ok().body(votoDTOConverter.toList(votos));
    }

    @ApiOperation(value = "Buscar Voto por ID")
    @GetMapping("/sessoes/votos/{idVoto}")
    public ResponseEntity<VotoDTO> findById(@PathVariable Long idVoto) {
        Voto voto = votoService.buscar(idVoto);
        return ResponseEntity.ok().body(votoDTOConverter.to(voto));
    }

    @ApiOperation(value = "Buscar Voto por Sessão")
    @GetMapping("/{idPauta}/sessoes/votos")
    public ResponseEntity<List<VotoDTO>> buscarVotosPorPauta(@PathVariable Long idPauta) {
        List<Voto> votos = votoService.buscarVotosPorPauta(idPauta);
        return ResponseEntity.ok().body(votoDTOConverter.toList(votos));
    }

    @ApiOperation(value = "Salvar Voto")
    @PostMapping("/{idPauta}/sessoes/{idSessao}/votos")
    public ResponseEntity<VotoDTO> criarVoto(
            @PathVariable Long idPauta,
            @PathVariable Long idSessao,
            @Valid @RequestBody VotoDTO votoDTO, HttpServletResponse response) {
        Voto voto = votoDTOConverter.to(votoDTO);
        voto = votoService.salvarVoto(idPauta, idSessao, voto);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, voto.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(votoDTOConverter.to(voto));
    }

    @ApiOperation(value = "Remover Voto por ID")
    @DeleteMapping("/sessoes/votos/{idVoto}")
    public void excluir(@PathVariable Long idVoto) {
        votoService.excluir(idVoto);
    }

}
