package br.com.gems.base.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PessoaDTO {

    private UUID id;
    private String nome;
    private String cpf;
    private String telefone;
    private String email;

}
