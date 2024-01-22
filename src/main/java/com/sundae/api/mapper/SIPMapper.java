package com.sundae.api.mapper;

import com.sundae.api.data.entities.Sip;
import com.sundae.api.models.request.AddSIPRequest;
import com.sundae.api.models.response.SIPResponse;

public class SIPMapper {
    private SIPMapper() {}

    public static Sip mapAddSIPRequestToSip(AddSIPRequest addSIPRequest) {
        return Sip.builder()
                .planId(addSIPRequest.getPlanId())
                .name(addSIPRequest.getName())
                .type(addSIPRequest.getType())
                .NRCApprovalDate(addSIPRequest.getNRCApprovalDate())
                .boardApprovalDate(addSIPRequest.getBoardApprovalDate())
                .shareHoldersApprovalDate(addSIPRequest.getShareHoldersApprovalDate())
                .institutionDate(addSIPRequest.getInstitutionDate())
                .SIPEffectiveDate(addSIPRequest.getSIPEffectiveDate())
                .SIPEndDate(addSIPRequest.getSIPEndDate())
                .currency(addSIPRequest.getCurrency())
                .numberOfOptions(addSIPRequest.getNumberOfOptions())
                .optionsToShareRatio(addSIPRequest.getOptionsToShareRatio())
                .totalShares(addSIPRequest.getTotalShares())
                .companyId(addSIPRequest.getCompanyId())
                .remarks(addSIPRequest.getRemarks())
                .build();
    }

    public static AddSIPRequest mapSipToAddSIPRequest(Sip sip) {
        return AddSIPRequest.builder()
                .planId(sip.getPlanId())
                .name(sip.getName())
                .type(sip.getType())
                .NRCApprovalDate(sip.getNRCApprovalDate())
                .boardApprovalDate(sip.getBoardApprovalDate())
                .shareHoldersApprovalDate(sip.getShareHoldersApprovalDate())
                .institutionDate(sip.getInstitutionDate())
                .SIPEffectiveDate(sip.getSIPEffectiveDate())
                .SIPEndDate(sip.getSIPEndDate())
                .currency(sip.getCurrency())
                .numberOfOptions(sip.getNumberOfOptions())
                .optionsToShareRatio(sip.getOptionsToShareRatio())
                .totalShares(sip.getTotalShares())
                .companyId(sip.getCompanyId())
                .remarks(sip.getRemarks())
                .build();
    }

    public static SIPResponse mapSipToSIPResponse(Sip sip) {
        return SIPResponse.builder()
                .id(sip.getId())
                .planId(sip.getPlanId())
                .name(sip.getName())
                .type(sip.getType())
                .NRCApprovalDate(sip.getNRCApprovalDate())
                .boardApprovalDate(sip.getBoardApprovalDate())
                .shareHoldersApprovalDate(sip.getShareHoldersApprovalDate())
                .institutionDate(sip.getInstitutionDate())
                .SIPEffectiveDate(sip.getSIPEffectiveDate())
                .SIPEndDate(sip.getSIPEndDate())
                .currency(sip.getCurrency())
                .numberOfOptions(sip.getNumberOfOptions())
                .optionsToShareRatio(sip.getOptionsToShareRatio())
                .totalShares(sip.getTotalShares())
                .companyId(sip.getCompanyId())
                .remarks(sip.getRemarks())
                .build();
    }
}
