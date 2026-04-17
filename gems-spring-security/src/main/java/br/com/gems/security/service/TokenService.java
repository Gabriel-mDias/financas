package br.com.gems.security.service;

import br.com.gems.exception.SecurityException;
import br.com.gems.security.model.entity.User;
import br.com.gems.security.util.JwtClaimEnum;
import br.com.gems.utils.ObjectUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TokenService {

    @Value("${api.security.jwt.secret:G&MS_SECRET_KEY}")
    private String JWT_SECRET;

    @Value("${api.security.jwt.issuer:G&MS_API}")
    private String ISSUER;

    @Value("${api.security.jwt.expiration.hours:2}")
    private Integer EXPIRATION_HOURS;

    @Value("${api.security.jwt.expiration.minutes:30}")
    private Integer EXPIRATION_MINUTES;

    @Value("${api.security.jwt.expiration.timezone:-03:00}")
    private String TIMEZONE_EXPIRATION_TOKEN;

    public String generateToken(User user) {
        var algorithm = Algorithm.HMAC256( JWT_SECRET );
        return JWT.create().withIssuer( ISSUER )
                .withSubject( user.getUsername() )
                .withClaim( JwtClaimEnum.ID.getClaimCode(), user.getId().toString() )
                .withClaim( JwtClaimEnum.EMAIL.getClaimCode(), user.getEmail() )
                .withClaim( JwtClaimEnum.ROLES.getClaimCode(), user.getRolesCode() )
                .withExpiresAt( getExpirationDateFromNow() )
                .sign( algorithm );
    }

    public boolean validateToken(String token){
        var algorithm = Algorithm.HMAC256( JWT_SECRET );
        var subject = JWT.require( algorithm )
                        .withIssuer( ISSUER )
                        .build()
                        .verify( token )
                        .getSubject();

        return !ObjectUtil.isNullOrEmpty(subject);
    }

    public UUID getUserIdFromToken(String token){
        var algorithm = Algorithm.HMAC256( JWT_SECRET );
        var userId = JWT.require( algorithm )
                        .withIssuer( ISSUER )
                        .build()
                        .verify( token )
                        .getClaim( JwtClaimEnum.ID.getClaimCode() );

        if( ObjectUtil.isNullOrEmpty(userId) ){
            throw new SecurityException("Token inválido!");
        }

        return UUID.fromString( userId.asString() );
    }

    private Instant getExpirationDateFromNow(){
        return LocalDateTime.now()
                .plusHours(EXPIRATION_HOURS)
                .plusMinutes(EXPIRATION_MINUTES)
                .toInstant(ZoneOffset.of(TIMEZONE_EXPIRATION_TOKEN));
    }

}
