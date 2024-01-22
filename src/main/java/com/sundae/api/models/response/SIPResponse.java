package com.sundae.api.models.response;

import com.sundae.api.constants.Currency;
import com.sundae.api.constants.SIPType;
import java.util.Date;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SIPResponse implements java.io.Serializable {
    private Integer id;
    private String planId;

    private String name;

    private SIPType type;
    private Date NRCApprovalDate;

    private Date boardApprovalDate;

    private Date shareHoldersApprovalDate;

    private Date institutionDate;

    private Date SIPEffectiveDate;

    private Date SIPEndDate;

    private Currency currency;

    private Integer numberOfOptions;

    private Integer optionsToShareRatio;

    private Long totalShares;

    private String companyId;

    private String remarks;
}
