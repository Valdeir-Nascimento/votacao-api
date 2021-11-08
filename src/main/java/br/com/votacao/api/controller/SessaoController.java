package br.com.votacao.api.controller;

import br.com.votacao.api.dto.SessaoDTO;
import br.com.votacao.api.dto.converter.SessaoDTOConverter;
import br.com.votacao.api.model.Sessao;
import br.com.votacao.api.service.SessaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/sessoes")
public class SessaoController {

    @Autowired
    private SessaoService sessaoService;
    @Autowired
    private SessaoDTOConverter sessaoDTOConverter;

    @GetMapping
    public ResponseEntity<List<SessaoDTO>> findAll() {
        List<Sessao> sessaoList = sessaoService.listar();
        return ResponseEntity.ok().body(sessaoDTOConverter.toList(sessaoList));
    }



}
