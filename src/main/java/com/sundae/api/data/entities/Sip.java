package com.sundae.api.data.entities;

import com.sundae.api.constants.Currency;
import com.sundae.api.constants.SIPType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@AllArgsConstructor
@Table(name = "sip")
@NoArgsConstructor
public class Sip {

    @Id
    @Column(name = "Id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "PlanId")
    private String planId;

    @Column(name = "Name")
    private String name;

    @Column(name = "Type")
    private SIPType type;

    @Column(name = "NRCApprovalDate")
    private Date NRCApprovalDate;

    @Column(name = "BoardApprovalDate")
    private Date boardApprovalDate;

    @Column(name = "ShareHoldersApprovalDate")
    private Date shareHoldersApprovalDate;

    @Column(name = "InstitutionDate")
    private Date institutionDate;

    @Column(name = "SIPEffectiveDate")
    private Date SIPEffectiveDate;

    @Column(name = "SIPEndDate")
    private Date SIPEndDate;

    @Column(name = "Currency")
    private Currency currency;

    @Column(name = "NumberOfOptions")
    private Integer numberOfOptions;

    @Column(name = "OptionsToShareRatio")
    private Integer optionsToShareRatio;

    @Column(name = "TotalShares")
    private Long totalShares;

    @Column(name = "CompanyId")
    private String companyId;

    @Column(name = "Remarks")
    private String remarks;

    @Column(name = "CreatedAt")
    private Date createdAt;

    @Column(name = "ModifiedAt")
    private Date modifiedAt;

    @Column(name = "CreatedBy")
    private String createdBy;

    @Column(name = "ModifiedBy")
    private String modifiedBy;
}
