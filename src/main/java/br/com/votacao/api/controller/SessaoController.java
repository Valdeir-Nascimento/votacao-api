package br.com.votacao.api.controller;

import br.com.votacao.api.dto.SessaoDTO;
import br.com.votacao.api.dto.converter.SessaoDTOConverter;
import br.com.votacao.api.event.RecursoCriadoEvent;
import br.com.votacao.api.model.Sessao;
import br.com.votacao.api.service.SessaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping(value = "/v1/pautas/")
public class SessaoController {

    @Autowired
    private SessaoService sessaoService;
    @Autowired
    private SessaoDTOConverter sessaoDTOConverter;
    @Autowired
    private ApplicationEventPublisher publisher;

    @GetMapping("/sessoes")
    public ResponseEntity<List<SessaoDTO>> findAll() {
        List<Sessao> sessaoList = sessaoService.listar();
        return ResponseEntity.ok().body(sessaoDTOConverter.toList(sessaoList));
    }

    @GetMapping("/{idSessao}")
    public ResponseEntity<SessaoDTO> findById(@PathVariable Long idSessao) {
        Sessao sessao = sessaoService.buscar(idSessao);
        return ResponseEntity.ok().body(sessaoDTOConverter.to(sessao));
    }

    @PostMapping("/{idPauta}/sessoes")
    public ResponseEntity<SessaoDTO> criarSessao(@PathVariable Long idPauta, @RequestBody SessaoDTO sessaoDTO, HttpServletResponse response) {
        Sessao sessao = sessaoDTOConverter.to(sessaoDTO);
        sessao = sessaoService.criarSessao(idPauta, sessao);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, sessao.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(sessaoDTOConverter.to(sessao));
    }

    @GetMapping("/{idPauta}/sessoes/{idSessao}")
    public ResponseEntity<SessaoDTO> buscarSessaoPorPauta(@PathVariable Long idPauta, @PathVariable Long idSessao) {
        Sessao sessao = sessaoService.buscarSessaoPorPauta(idSessao, idPauta);
        return ResponseEntity.ok().body(sessaoDTOConverter.to(sessao));
    }

    @DeleteMapping("/sessoes/{idSessao}")
    @ResponseStatus(HttpStatus.OK)
    public void excluir(@PathVariable Long idSessao) {
        sessaoService.excluir(idSessao);
    }



}
