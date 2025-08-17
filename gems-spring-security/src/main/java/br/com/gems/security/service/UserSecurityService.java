package br.com.gems.security.service;

import br.com.gems.security.model.entity.User;
import br.com.gems.security.repository.UserSecurityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserSecurityService implements UserDetailsService {

    private UserSecurityRepository repository;

    @Override
    public User loadUserByUsername( String username ) throws SecurityException {
        return repository.findByUsername( username )
                .orElseThrow(() -> new SecurityException( "Usuário não encontrado." ) );
    }

}
