package com.sundae.api.models.request;

import com.sundae.api.constants.Currency;
import com.sundae.api.constants.SIPType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.Date;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AddSIPRequest {

    @NotNull(message = "Plan ID cannot be null.")
    @NotBlank(message = "Plan ID cannot be blank.")
    private String planId;

    @NotNull(message = "Name cannot be null.")
    @NotBlank(message = "Name cannot be blank.")
    private String name;

    @NotNull(message = "Type cannot be null.")
    @NotBlank(message = "Type cannot be blank.")
    private SIPType type;

    @NotNull(message = "NRCApprovalDate cannot be null.")
    private Date NRCApprovalDate;

    @NotNull(message = "BoardApprovalDate cannot be null.")
    private Date boardApprovalDate;

    @NotNull(message = "ShareHoldersApprovalDate cannot be null.")
    private Date shareHoldersApprovalDate;

    @NotNull(message = "InstitutionDate cannot be null.")
    private Date institutionDate;

    @NotNull(message = "SIPEffectiveDate cannot be null.")
    private Date SIPEffectiveDate;

    @NotNull(message = "SIPEndDate cannot be null.")
    private Date SIPEndDate;

    @NotNull(message = "Currency cannot be null.")
    private Currency currency;

    @NotNull(message = "Total number of options cannot be null.")
    @Min(value = 1, message = "Total number of options must be greater than 0.")
    private Integer numberOfOptions;

    @Min(value = 1, message = "Options to share ratio must be greater than 0.")
    @NotNull(message = "Options to share ratio cannot be null.")
    private Integer optionsToShareRatio;

    @NotNull(message = "Total shares cannot be null.")
    @Min(value = 1, message = "Total shares must be greater than 0.")
    private Long totalShares;

    @NotNull(message = "Company Id cannot be null.")
    @NotBlank(message = "Company Id cannot be blank.")
    private String companyId;

    @NotNull(message = "Remarks cannot be null.")
    @NotBlank(message = "Remarks cannot be blank.")
    private String remarks;
}
