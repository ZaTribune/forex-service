package com.zatribune.forex.domain;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GenericResponse <T>{

    private String message;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private T body;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Object reason;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private int code;
}
