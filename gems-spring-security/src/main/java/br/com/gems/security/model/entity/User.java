package br.com.gems.security.model.entity;

import br.com.gems.utils.ObjectUtil;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table( name = "USERS", schema = "security" )
@SQLDelete( sql = "UPDATE security.USERS SET DT_DELETED = NOW() WHERE ID_USER = ?" )
@SQLRestriction( "DT_DELETED IS NULL" )
public class User implements UserDetails {

    @Id
    @GeneratedValue( strategy = GenerationType.UUID )
    @Column( name = "ID_USER" )
    private UUID id;

    @Column( name = "NM_USERNAME", nullable = false )
    private String username;

    @Column( name = "CD_EMAIL", nullable = false, unique = true )
    private String email;

    @Column( name = "CD_PASSWORD", nullable = false )
    private String password;

    @Column( name = "DT_CREATED", nullable = false )
    private LocalDateTime createDate;

    @Column( name = "DT_DELETED" )
    private LocalDateTime deleteDate;

    @ToString.Exclude
    @OneToMany( mappedBy = "user", fetch = FetchType.EAGER )
    private List<UserRole> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getRoles()
                .stream()
                .map( ur -> new SimpleGrantedAuthority( ur.getRole().getCode() ) )
                .collect( Collectors.toList() );
    }

    @Override
    public String getPassword() {
        return this.user.getPassword();
    }

    @Override
    public String getUsername() {
        return this.user.getEmail();
    }

    @Override
    public boolean isEnabled() {
        return ObjectUtil.isNullOrEmpty( this.user.getDeleteDate() );
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

}
