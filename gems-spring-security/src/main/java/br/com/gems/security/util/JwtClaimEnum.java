package br.com.gems.security.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum JwtClaimEnum {

    ID( "id" ),
    USERNAME( "username" ),
    EMAIL( "email" ),
    ROLES( "roles" ),
    ;


    private final String claimCode;

}
