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
@Table(name = "otps")
public class Otps {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;

    @Column(name = "Otp")
    private String otp;

    @Column(name = "ReferenceCode")
    private String referenceCode;

    @Column(name = "UserId")
    private String userId;

    @Column(name = "ExpiresIn")
    private Integer expiresIn;

    @Column(name = "IsVerified")
    private Boolean isVerified;

    @Column(name = "CreatedAt")
    private Date createdAt;

    @Column(name = "ModifiedAt")
    private Date modifiedAt;

    @Column(name = "CreatedBy")
    private String createdBy;

    @Column(name = "ModifiedBy")
    private String modifiedBy;
}
