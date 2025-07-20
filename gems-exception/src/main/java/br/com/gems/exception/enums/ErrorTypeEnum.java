package br.com.gems.exception.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorTypeEnum {

    ERROR( "Error" ),
    FALHA( "Falha" ),
    ALERTA( "Alerta" ),
    ;

    private String description;


}
