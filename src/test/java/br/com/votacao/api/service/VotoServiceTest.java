package br.com.votacao.api.service;

import br.com.votacao.api.model.Pauta;
import br.com.votacao.api.model.Voto;
import br.com.votacao.api.repository.PautaRepository;
import br.com.votacao.api.repository.VotoRepository;
import br.com.votacao.api.util.ResourceUtil;
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

import static io.restassured.RestAssured.*;

@RunWith(SpringRunner.class)
@TestPropertySource("/application-test.properties")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class VotoServiceTest {

    private static final int ID_VOTO_INEXISTENTE = 100;

    @LocalServerPort
    private int port;
    @Autowired
    private VotoRepository votoRepository;
    @Autowired
    private PautaRepository pautaRepository;

    private Pauta pauta;
    private Voto voto;
    private String jsonCadastrarVotoCorreto;

    @Before
    public void setup() {
        enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.port = port;
        basePath = "/v1/pautas/sessoes/votos";

    }

    @Test
    public void deveRetornarStatus200_QuandoConsultarVotos() {
        given()
                .accept(ContentType.JSON)
                .when()
                .get()
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void deveRetornarStatus404_QuandoConsultarVotoInexistente() {
        given()
                .pathParam("votoId", ID_VOTO_INEXISTENTE)
                .accept(ContentType.JSON)
                .when()
                .get("/{votoId}")
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

    @Test
    public void deveRetornaStatus200_QuandoExcluirVotoPorId() {
        given()
                .pathParam("votoId", 1L)
                .accept(ContentType.JSON)
                .when()
                .delete("/{votoId}")
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

}
