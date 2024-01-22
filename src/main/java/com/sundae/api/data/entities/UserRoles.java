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
@Table(name = "userroles")
public class UserRoles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;

    @Column(name = "UserId")
    private String userId;

    @Column(name = "RoleId")
    private Integer roleId;

    @Column(name = "CreatedAt")
    private Date createdAt;

    @Column(name = "ModifiedAt")
    private Date modifiedAt;

    @Column(name = "CreatedBy")
    private String createdBy;

    @Column(name = "ModifiedBy")
    private String modifiedBy;
}
