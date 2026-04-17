package br.com.gems.security.repository;

import br.com.gems.security.model.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRoleSecurityRepository extends JpaRepository<UserRole, UUID> {
}
