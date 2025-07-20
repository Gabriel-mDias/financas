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
@Table( name = "CONTA_BANCARIA" )
public class ContaBancaria {

    @Id
    @GeneratedValue( strategy = GenerationType.UUID )
    @Column( name = "ID_CONTA_BANCARIA" )
    private UUID id;

    @ToString.Exclude
    @ManyToOne( fetch = FetchType.LAZY )
    @JoinColumn( name = "ID_BANCO", nullable = false )
    private Banco banco;

    @ToString.Exclude
    @ManyToOne( fetch = FetchType.LAZY )
    @JoinColumn( name = "ID_CLIENTE", nullable = false )
    private Cliente cliente;

    @Column( name = "DT_ABERTURA", nullable = false )
    private LocalDateTime dataAbertura;

    /**
     * Propriedade relacionada ao encerramento de uma conta bancária junto ao banco.
     */
    @Column( name = "DT_ENCERRAMENTO" )
    private LocalDateTime dataEncerramento;

}
