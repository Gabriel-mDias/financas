package br.com.gems.security.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table( name = "ROLE", schema = "security" )
public class Role {

    @Id
    @GeneratedValue( strategy = GenerationType.UUID )
    @Column( name = "ID_ROLE" )
    private UUID id;

    @Column( name = "CD_ROLE", nullable = false, unique = true )
    private String code;

    @Column( name = "DS_ROLE" )
    private String description;

}
