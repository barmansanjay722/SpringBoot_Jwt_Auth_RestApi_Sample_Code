package com.sundae.api.data.repos;

import com.sundae.api.data.entities.Roles;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesRespository extends JpaRepository<Roles, Integer> {
    Optional<Roles> findById(Integer id);

    Roles findByRoleName(String roleName);
}
