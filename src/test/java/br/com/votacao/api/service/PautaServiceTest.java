package br.com.votacao.api.service;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;

import static io.restassured.RestAssured.*;

@TestPropertySource("/application-test.properties")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PautaServiceTest {

    @LocalServerPort
    private int port;
    @Autowired
    private PautaService pautaService;

    @Before
    public void setup() {
        enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.port=8081;
        basePath="/v1/pautas";
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

    }




}
