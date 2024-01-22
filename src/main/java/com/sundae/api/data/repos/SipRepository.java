package com.sundae.api.data.repos;

import com.sundae.api.data.entities.Sip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SipRepository extends JpaRepository<Sip, Integer>, JpaSpecificationExecutor<Sip> {}
