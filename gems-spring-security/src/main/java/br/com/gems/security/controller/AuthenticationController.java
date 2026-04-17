package br.com.gems.security.controller;

import br.com.gems.security.model.dto.AuthenticationDTO;
import br.com.gems.security.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RequestMapping( "gems/auth" )
@RestController
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping( "login" )
    public ResponseEntity<?> login( @RequestBody AuthenticationDTO dto ){
        var response = Map.of("access_token", service.authenticateUserAndGetToken( dto ));
        return ResponseEntity.ok(response);
    }

}
