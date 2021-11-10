package br.com.votacao.api.service;

import br.com.votacao.api.model.Pauta;
import br.com.votacao.api.repository.PautaRepository;
import br.com.votacao.api.util.ResourceUtil;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.BeforeClass;

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
public class PautaServiceTest {

    private static final int ID_PAUTA_INEXISTENTE = 100;

    @LocalServerPort
    private int port;

    @Autowired
    private PautaRepository pautaRepository;

    private String jsonPautaCorreto;
    private Pauta pauta;

    @Before
    public void setup() {
        enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.port=port;
        basePath="/v1/pautas";
        jsonPautaCorreto = ResourceUtil.getContentFromResource("/json/correto/pauta.json");

        prepararDados();
    }

    @Test
    public void deveRetornarStatus200_QuandoConsultarPautas() {
        given()
                .accept(ContentType.JSON)
                .when()
                .get()
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void deveRetornarStatus201_QuandoCadastrarPauta() {
        given()
                .body(jsonPautaCorreto)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .post()
                .then()
                .statusCode(HttpStatus.CREATED.value());
    }

    @Test
    public void deveRetornarStatus404_QuandoConsultarPautaInexistente() {
        given()
                .pathParam("pautaId", ID_PAUTA_INEXISTENTE)
                .accept(ContentType.JSON)
                .when()
                .get("/{pautaId}")
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

    @Test
    public void deveRetornarRespostaEStatusCorretos_QuandoConsultarPautaExistente() {
        given()
                .pathParam("pautaId", pauta.getId())
                .accept(ContentType.JSON)
                .when()
                .get("/{pautaId}")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("nome", Matchers.equalTo(pauta.getNome()));
    }


    private void prepararDados() {
        pauta = new Pauta();

        pauta.setNome("RECURSO ELEITORAL");
        pautaRepository.save(pauta);
    }

}