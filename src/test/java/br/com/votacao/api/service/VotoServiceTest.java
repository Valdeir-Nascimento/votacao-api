package br.com.votacao.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

public class VotoServiceTest {

    @Autowired
    private VotoService votoService;
    @Autowired
    private ResultadoVotacaoService resultadoVotacaoService;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private SessaoService sessaoService;



}
