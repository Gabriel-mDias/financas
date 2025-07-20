package br.com.gems.base.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDTO {

    private UUID id;
    private PessoaDTO pessoa;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataExclusao;

}
