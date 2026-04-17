package br.com.gems.core.controller;

import br.com.gems.core.service.BancoService;
import br.com.gems.base.model.dto.BancoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping( "banco" )
@RequiredArgsConstructor
public class BancoController {

    private final BancoService service;

    @GetMapping( "/{codigo}" )
    @PreAuthorize("hasRole('BANCO')")
    public ResponseEntity<BancoDTO> findByCodigo( @PathVariable String codigo ) {
        return ResponseEntity.ok( service.findByCodigo( codigo ) );
    }

    @GetMapping
    @PreAuthorize("hasRole('BANCO')")
    public ResponseEntity<List<BancoDTO>> findAll() {
        return ResponseEntity.ok( service.findAll() );
    }

}
