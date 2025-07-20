package br.com.gems.core.controller;

import br.com.gems.base.BaseController;
import br.com.gems.core.services.BancoService;
import br.com.gems.base.model.dto.BancoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping( "banco" )
@RequiredArgsConstructor
public class BancoController {

    private final BancoService service;

    @GetMapping( "/{codigo}" )
    public ResponseEntity<BancoDTO> findByCodigo( @PathVariable String codigo ) {
        return ResponseEntity.ok( service.findByCodigo( codigo ) );
    }

    @GetMapping
    public ResponseEntity<List<BancoDTO>> findAll() {
        return ResponseEntity.ok( service.findAll() );
    }

}
