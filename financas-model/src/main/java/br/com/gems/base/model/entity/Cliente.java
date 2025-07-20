package br.com.gems.base.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table( name = "CLIENTE" )
public class Cliente {

    @Id
    @GeneratedValue( strategy = GenerationType.UUID )
    @Column( name = "ID_CLIENTE" )
    private UUID id;

    @OneToOne( fetch = FetchType.LAZY )
    @JoinColumn( name = "ID_PESSOA", nullable = false )
    private Pessoa pessoa;

    /**
     * Data de criação do registro do cliente
     */
    @Column( name = "DT_CRIACAO", nullable = false )
    private LocalDateTime dataCriacao;

    /**
     * Data de exclusão do registro do cliente
     */
    @Column( name = "DT_EXCLUSAO" )
    private LocalDateTime dataExclusao;

}
