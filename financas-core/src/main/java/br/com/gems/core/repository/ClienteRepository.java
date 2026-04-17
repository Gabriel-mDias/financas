package br.com.gems.core.repository;

import br.com.gems.base.model.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, UUID> {
    Optional<Cliente> findByPessoaCpf(String cpf);
    Optional<Cliente> findByPessoaEmail(String email);
}
