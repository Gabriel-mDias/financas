package br.com.gems.security.repository;

import br.com.gems.security.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RoleSecurityRepository extends JpaRepository<Role, UUID> {
}
