package com.sundae.api.data.repos;

import com.sundae.api.data.entities.Otps;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OtpRepository extends JpaRepository<Otps, Integer> {
    Optional<Otps> findByUserId(String userId);
}
