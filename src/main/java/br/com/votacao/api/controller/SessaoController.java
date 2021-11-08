package br.com.votacao.api.controller;

import br.com.votacao.api.dto.SessaoDTO;
import br.com.votacao.api.dto.converter.SessaoDTOConverter;
import br.com.votacao.api.model.Sessao;
import br.com.votacao.api.service.SessaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/pautas/")
public class SessaoController {

    @Autowired
    private SessaoService sessaoService;
    @Autowired
    private SessaoDTOConverter sessaoDTOConverter;

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

    @DeleteMapping("/sessoes/{idSessao}")
    @ResponseStatus(HttpStatus.OK)
    public void excluir(@PathVariable Long idSessao) {
        sessaoService.excluir(idSessao);
    }

}
