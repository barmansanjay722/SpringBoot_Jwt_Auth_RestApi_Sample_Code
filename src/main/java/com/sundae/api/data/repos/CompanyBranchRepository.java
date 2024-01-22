package com.sundae.api.data.repos;

import com.sundae.api.data.entities.CompanyBranches;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyBranchRepository extends JpaRepository<CompanyBranches, Integer> {

    CompanyBranches findByBranchName(String branchName);
}
