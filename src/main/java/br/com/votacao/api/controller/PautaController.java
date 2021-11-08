package br.com.votacao.api.controller;

import br.com.votacao.api.dto.PautaDTO;
import br.com.votacao.api.dto.converter.PautaDTOConverter;
import br.com.votacao.api.event.RecursoCriadoEvent;
import br.com.votacao.api.model.Pauta;
import br.com.votacao.api.service.PautaService;
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
public class PautaController {

    @Autowired
    private PautaService pautaService;
    @Autowired
    private PautaDTOConverter pautaDTOConverter;
    @Autowired
    private ApplicationEventPublisher publisher;

    @GetMapping
    public ResponseEntity<List<PautaDTO>> findAll() {
        List<Pauta> pautas = pautaService.listar();
        return ResponseEntity.ok().body(pautaDTOConverter.toList(pautas));
    }

    @GetMapping("/{idPauta}")
    public ResponseEntity<PautaDTO> findById(@PathVariable Long idPauta) {
        Pauta pauta = pautaService.buscar(idPauta);
        return ResponseEntity.ok().body(pautaDTOConverter.to(pauta));
    }

    @PostMapping
    public ResponseEntity<PautaDTO> create(@Valid @RequestBody PautaDTO pautaDTO, HttpServletResponse response) {
        Pauta pauta = pautaDTOConverter.to(pautaDTO);
        pauta = pautaService.salvar(pauta);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, pauta.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(pautaDTOConverter.to(pauta));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{idPauta}")
    public void excluir(@PathVariable Long idPauta) {
        pautaService.excluir(idPauta);
    }


}
