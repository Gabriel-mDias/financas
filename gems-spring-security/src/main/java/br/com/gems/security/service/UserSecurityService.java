package br.com.gems.security.service;

import br.com.gems.security.model.entity.User;
import br.com.gems.security.repository.UserSecurityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import br.com.gems.exception.SecurityException;


@Service
@RequiredArgsConstructor
public class UserSecurityService implements UserDetailsService {

    private final UserSecurityRepository repository;

    @Override
    public User loadUserByUsername( String username ) throws SecurityException {
        return repository.findByUsername( username )
                .orElseThrow(() -> new SecurityException( "Usuário não encontrado." ) );
    }

}
