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
@Table(name = "users")
@NoArgsConstructor
public class Users {
    @Id
    @Column(name = "Id")
    private String id;

    @Column(name = "Name")
    private String name;

    @Column(name = "Email")
    private String email;

    @Column(name = "PhoneNumber")
    private String phoneNumber;

    @Column(name = "PasswordHash")
    private String passwordHash;

    @Column(name = "PasswordSalt")
    private String passwordSalt;

    @Column(name = "BranchId")
    private Integer branchId;

    @Column(name = "DepartmentId")
    private Integer departmentId;

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
