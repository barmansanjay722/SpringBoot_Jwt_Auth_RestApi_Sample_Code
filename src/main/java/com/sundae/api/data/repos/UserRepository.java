package com.sundae.api.data.repos;

import com.sundae.api.data.entities.Users;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, String> {
    Users findByEmail(String email);

    Users findByIdAndStatus(String id, String status, PageRequest pageRequest);
}
