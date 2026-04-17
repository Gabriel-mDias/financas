package br.com.gems.security.service;

import br.com.gems.security.model.dto.AuthenticationDTO;
import br.com.gems.security.model.dto.RegisterDTO;
import br.com.gems.security.model.entity.Role;
import br.com.gems.security.model.entity.User;
import br.com.gems.exception.SecurityException;
import br.com.gems.security.model.entity.UserRole;
import br.com.gems.security.repository.RoleSecurityRepository;
import br.com.gems.security.repository.UserRoleSecurityRepository;
import br.com.gems.security.repository.UserSecurityRepository;
import br.com.gems.utils.ObjectUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    private final UserSecurityRepository userRepository;
    private final RoleSecurityRepository roleRepository;
    private final UserRoleSecurityRepository userRoleRepository;

    public String authenticateUserAndGetToken(AuthenticationDTO dto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        dto.username(),
                        dto.password()
                )
        );

        var user = userRepository.findByUsername( dto.username() ).orElseThrow( () -> new SecurityException("Usuário não encontrado para o username informado.") );
        return tokenService.generateToken( user );
    }

    public void registerUser(RegisterDTO dto) {
        var roles = validateRegisterAndGetRoles(dto);

        var user = User.builder()
                .username( dto.username() )
                .email( dto.email() )
                .password( new BCryptPasswordEncoder().encode( dto.password() ) )
                .createDate(LocalDateTime.now() )
                .build();

        userRepository.save( user );

        var userRoles = roles.stream()
                .map( role -> UserRole.builder()
                                            .role( role )
                                            .user( user )
                                            .createDate( LocalDateTime.now() )
                                            .build() )
                .toList();

        userRoleRepository.saveAll( userRoles );
    }

    private List<Role> validateRegisterAndGetRoles(RegisterDTO dto) {
        if( ObjectUtil.isNullOrEmpty( dto.username() ) ){
            throw new SecurityException( "Username não informado." );
        }

        if( ObjectUtil.isNullOrEmpty( dto.password() ) ) {
            throw new SecurityException("Senha não informada.");
        }

        if( ObjectUtil.isNullOrEmpty( dto.email() ) ) {
            throw new SecurityException("Email não informado.");
        }

        userRepository.findByUsername( dto.username() )
                .ifPresent( user -> {
                    throw new SecurityException( "Username já cadastrado para um outro usuário." );
                });

        userRepository.findByEmail( dto.email() )
                .ifPresent( user -> {
                    throw new SecurityException( "Email já cadastrado para um outro usuário." );
                });

        if( ObjectUtil.isNullOrEmpty( dto.roles() ) ){
            return new ArrayList<>();
        }

        var roles = roleRepository.findByCodeIn( dto.roles() );

        if( ObjectUtil.isNullOrEmpty( roles ) && !ObjectUtil.isNullOrEmpty( dto.roles() ) ){
            throw new SecurityException("Nenhuma role encontrada para os códigos informados." );
        }

        var rolesMap = roles.stream().collect( Collectors.toMap(Role::getCode, r -> r ) );
        dto.roles().forEach(role -> {
            if( !rolesMap.containsKey( role ) ){
                throw new SecurityException("Role " + role + ", que foi informada no cadastro do usuário, não foi encontrada na base de dados." );
            }
        });

        return roles;
    }

}
