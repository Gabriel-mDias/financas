package br.com.gems.base.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContaBancariaDTO {

    private UUID id;
    private BancoDTO banco;
    private ClienteDTO cliente;

}
