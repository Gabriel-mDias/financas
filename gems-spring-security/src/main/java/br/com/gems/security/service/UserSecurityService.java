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
    public User loadUserByUsername( String email ) throws UsernameNotFoundException {
        return repository.findByEmail( email )
                .orElseThrow(() -> new RuntimeException( "Usuário não encontrado." ) );
    }

}
