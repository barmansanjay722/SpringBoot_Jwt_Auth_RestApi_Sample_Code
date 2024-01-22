package com.sundae.api.data.entities;

import jakarta.persistence.*;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "companydepartment")
public class CompanyDepartment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;

    @Column(name = "DepartmentName")
    private String departmentName;

    @Column(name = "CompanyId")
    private String companyId;

    @Column(name = "Status")
    private String status;

    @Column(name = "CreatedAt")
    private Date createdAt;

    @Column(name = "ModifiedAt")
    private Date modifiedAt;

    @Column(name = "CreatedBy")
    private String createdBy;

    @Column(name = "ModifiedBy")
    private String modifiedBy;
}
