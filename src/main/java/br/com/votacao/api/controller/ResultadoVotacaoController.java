package br.com.votacao.api.controller;

import br.com.votacao.api.dto.VotacaoDTO;
import br.com.votacao.api.service.ResultadoVotacaoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "Resultado Votacao")
@RequestMapping("/v1/pautas")
public class ResultadoVotacaoController {

    @Autowired
    private ResultadoVotacaoService resultadoVotacaoService;

    @ApiOperation(value = "Exibe Resultado da Votação por Pauta")
    @GetMapping("/{id}/resultado")
    public VotacaoDTO buscarResultadoVotacaoPorPauta(@PathVariable Long id) {
        return resultadoVotacaoService.obterResultadoVotacao(id);
    }

}
