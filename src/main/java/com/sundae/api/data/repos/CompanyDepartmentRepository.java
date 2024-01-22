package com.sundae.api.data.repos;

import com.sundae.api.data.entities.CompanyDepartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyDepartmentRepository extends JpaRepository<CompanyDepartment, Integer> {

    CompanyDepartment findByDepartmentName(String departmentName);
}
