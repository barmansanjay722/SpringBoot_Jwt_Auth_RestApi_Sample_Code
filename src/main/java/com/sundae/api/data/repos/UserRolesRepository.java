package com.sundae.api.data.repos;

import com.sundae.api.data.entities.UserRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRolesRepository extends JpaRepository<UserRoles, Integer> {
    UserRoles findByUserId(String userId);
}
