package br.com.gems.security.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table( name = "USER_ROLE", schema = "security" )
@SQLDelete( sql = "UPDATE security.USER_ROLE SET DT_DELETED = NOW() WHERE ID_USER_ROLE = ?" )
@SQLRestriction( "DT_DELETED IS NULL" )
public class UserRole {

    @Id
    @GeneratedValue( strategy = GenerationType.UUID )
    @Column( name = "ID_USER_ROLE" )
    private UUID id;

    @ToString.Exclude
    @ManyToOne( fetch = FetchType.LAZY )
    @JoinColumn( name = "ID_USER", nullable = false )
    private User user;

    @ToString.Exclude
    @ManyToOne( fetch = FetchType.EAGER )
    @JoinColumn( name = "ID_ROLE", nullable = false )
    private Role role;

    @Column( name = "DT_CREATED", nullable = false )
    private LocalDateTime createDate;

    @Column( name = "DT_DELETED" )
    private LocalDateTime deleteDate;

}
