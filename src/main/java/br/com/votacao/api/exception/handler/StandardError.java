package br.com.votacao.api.exception.handler;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

//Incluir no json somente se for diferente de null
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@Getter
public class StandardError {

    private Integer statusCode;
    private String type;
    private String title;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime timestamp;
    private String errorMessage;
    private String userMessage;

    private List<Field> fields;


}
