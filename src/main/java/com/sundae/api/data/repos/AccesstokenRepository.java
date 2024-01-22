package com.sundae.api.data.repos;

import com.sundae.api.data.entities.AccessTokens;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccesstokenRepository extends JpaRepository<AccessTokens, String> {

    AccessTokens findByTokenAndIsActive(String token, Boolean isActive);

    Optional<AccessTokens> findByUserId(String userId);

    List<AccessTokens> findByUserIdAndIsActive(String userId, Boolean isActive);
}
