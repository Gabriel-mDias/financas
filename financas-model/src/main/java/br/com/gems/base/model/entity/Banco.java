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
@Table( name = "BANCO" )
public class Banco {

    @Id
    @GeneratedValue( strategy = GenerationType.UUID )
    @Column( name = "ID_BANCO" )
    private UUID id;

    @Column( name = "NM_BANCO", nullable = false )
    private String nome;

    @Column( name = "CD_BANCO", nullable = false )
    private String codigo;

    
}
