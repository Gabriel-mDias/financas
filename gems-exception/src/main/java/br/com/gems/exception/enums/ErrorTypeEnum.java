package br.com.gems.exception.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorTypeEnum {

    ERRO_NAO_ESPERADO( "Erro não esperado" ),
    FALHA( "Falha" ),
    ALERTA( "Alerta" ),
    ;

    private String description;


}
