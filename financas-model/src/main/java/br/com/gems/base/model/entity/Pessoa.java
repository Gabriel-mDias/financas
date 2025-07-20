package br.com.gems.base.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table( name = "PESSOA" )
public class Pessoa {

    @Id
    @GeneratedValue( strategy = GenerationType.UUID )
    @Column( name = "ID_PESSOA" )
    private UUID id;

    @Column( name = "NM_PESSOA", nullable = false )
    private String nome;

    @Column( name = "CD_CPF", nullable = false )
    private String cpf;

    @Column( name = "NR_TELEFONE", nullable = false )
    private String telefone;

    @Column( name = "DS_EMAIL", nullable = false )
    private String email;

}
