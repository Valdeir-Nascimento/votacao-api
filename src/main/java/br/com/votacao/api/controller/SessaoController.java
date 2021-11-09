package br.com.votacao.api.controller;

import br.com.votacao.api.dto.SessaoDTO;
import br.com.votacao.api.dto.converter.SessaoDTOConverter;
import br.com.votacao.api.event.RecursoCriadoEvent;
import br.com.votacao.api.model.Sessao;
import br.com.votacao.api.service.SessaoService;
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
@Api(value = "Sessão")
public class SessaoController {

    @Autowired
    private SessaoService sessaoService;
    @Autowired
    private SessaoDTOConverter sessaoDTOConverter;
    @Autowired
    private ApplicationEventPublisher publisher;

    @ApiOperation(value = "Listar Sessões")
    @GetMapping("/sessoes")
    public ResponseEntity<List<SessaoDTO>> findAll() {
        List<Sessao> sessaoList = sessaoService.listar();
        return ResponseEntity.ok().body(sessaoDTOConverter.toList(sessaoList));
    }

    @ApiOperation(value = "Buscar Sessão por ID")
    @GetMapping("/sessoes/{idSessao}")
    public ResponseEntity<SessaoDTO> findById(@PathVariable Long idSessao) {
        Sessao sessao = sessaoService.buscar(idSessao);
        return ResponseEntity.ok().body(sessaoDTOConverter.to(sessao));
    }

    @ApiOperation(value = "Criar Nova Sessão")
    @PostMapping("/{idPauta}/sessoes")
    public ResponseEntity<SessaoDTO> criarSessao(@PathVariable Long idPauta, @RequestBody @Valid SessaoDTO sessaoDTO, HttpServletResponse response) {
        Sessao sessao = sessaoDTOConverter.to(sessaoDTO);
        sessao = sessaoService.criarSessao(idPauta, sessao);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, sessao.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(sessaoDTOConverter.to(sessao));
    }

    @ApiOperation(value = "Buscar Sessão por Pauta")
    @GetMapping("/{idPauta}/sessoes/{idSessao}")
    public ResponseEntity<SessaoDTO> buscarSessaoPorPauta(@PathVariable Long idPauta, @PathVariable Long idSessao) {
        Sessao sessao = sessaoService.buscarSessaoPorPauta(idSessao, idPauta);
        return ResponseEntity.ok().body(sessaoDTOConverter.to(sessao));
    }

    @ApiOperation(value = "Remover Sessão por ID")
    @DeleteMapping("/sessoes/{idSessao}")
    @ResponseStatus(HttpStatus.OK)
    public void excluir(@PathVariable Long idSessao) {
        sessaoService.excluir(idSessao);
    }
}
