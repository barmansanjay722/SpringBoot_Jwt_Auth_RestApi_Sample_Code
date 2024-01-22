package com.sundae.api.services.sip;

import com.sundae.api.models.SIPQuery;
import com.sundae.api.models.request.AddSIPRequest;
import com.sundae.api.models.request.UpdateSIPRequest;
import com.sundae.api.models.response.SIPResponse;
import org.springframework.data.domain.Page;

public interface SIPService {
    void delete(Integer id);

    SIPResponse save(AddSIPRequest addSipRequest);

    SIPResponse update(Integer id, UpdateSIPRequest updateSIPRequest);

    SIPResponse getById(Integer id);

    Page<SIPResponse> query(SIPQuery sipQuery);
}
