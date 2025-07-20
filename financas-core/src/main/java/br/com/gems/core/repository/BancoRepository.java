package br.com.gems.core.repository;

import br.com.gems.base.model.entity.Banco;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface BancoRepository extends JpaRepository<Banco, UUID> {

    Optional<Banco> findByCodigo(String codigo );

}
