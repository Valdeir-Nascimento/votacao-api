package br.com.votacao.api.service;

import br.com.votacao.api.model.Pauta;
import br.com.votacao.api.model.Sessao;
import br.com.votacao.api.repository.PautaRepository;
import br.com.votacao.api.repository.SessaoRepository;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static io.restassured.RestAssured.*;

@RunWith(SpringRunner.class)
@TestPropertySource("/application-test.properties")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SessaoServiceTest {

    private static final int ID_SESSAO_INEXISTENTE = 100;

    @LocalServerPort
    private int port;
    @Autowired
    private SessaoRepository sessaoRepository;
    @Autowired
    private PautaRepository pautaRepository;

    private Pauta pauta;
    private Sessao sessao;
    private String jsonCadastrarVotoCorreto;

    @Before
    public void setup() {
        enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.port = port;
        basePath = "/v1/pautas/sessoes";

        prepararDados();
    }

    @Test
    public void deveRetornarStatus200_QuandoConsultarSessao() {
        given()
                .accept(ContentType.JSON)
                .when()
                .get()
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void deveRetornaStatus200_QuandoBuscarVotoPorId() {
        given()
                .pathParam("votoId", 1L)
                .accept(ContentType.JSON)
                .when()
                .get("/{votoId}")
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

    @Test
    public void deveRetornarRespostaEStatusCorretos_QuandoConsultarSessaoExistente() {
        given()
                .pathParam("sessaoId", sessao.getId())
                .accept(ContentType.JSON)
                .when()
                .get("/{sessaoId}")
                .then()
                .statusCode(HttpStatus.OK.value());
    }


    private void prepararDados() {
        pauta = new Pauta();
        sessao = new Sessao();
        pauta.setNome("RECURSO ELEITORAL");
        pautaRepository.save(pauta);

        sessao.setMinutos(30L);
        sessao.setPauta(pauta);
        sessao.setDataInicio(LocalDateTime.now());
        sessaoRepository.save(sessao);
    }


}
