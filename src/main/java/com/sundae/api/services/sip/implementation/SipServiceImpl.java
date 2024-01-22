package com.sundae.api.services.sip.implementation;

import com.sundae.api.data.entities.Sip;
import com.sundae.api.data.repos.SipRepository;
import com.sundae.api.mapper.SIPMapper;
import com.sundae.api.models.SIPQuery;
import com.sundae.api.models.request.AddSIPRequest;
import com.sundae.api.models.request.UpdateSIPRequest;
import com.sundae.api.models.response.SIPResponse;
import com.sundae.api.services.sip.SIPService;
import java.util.NoSuchElementException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

/**
 * Implementation of the SIPService interface.
 * Provides methods for saving, deleting, updating, and retrieving SIPs.
 */
@AllArgsConstructor
@Slf4j
@Service
public class SipServiceImpl implements SIPService {

    private final SipRepository sipRepository;

    /**
     * Saves a new SIP based on the provided request.
     *
     * @param addSIPRequest the request containing the SIP data to be saved
     * @return the response containing the saved SIP data
     */
    @Override
    public SIPResponse save(AddSIPRequest addSIPRequest) {
        log.info("Saving new SIP");
        Sip sip = SIPMapper.mapAddSIPRequestToSip(addSIPRequest);
        sip = sipRepository.save(sip);
        log.info("New SIP saved with ID: {}", sip.getId());
        return toModel(sip);
    }

    /**
     * Deletes the SIP with the specified ID.
     *
     * @param id the ID of the SIP to be deleted
     */
    @Override
    public void delete(Integer id) {
        log.info("Deleting SIP with ID: {}", id);
        sipRepository.deleteById(id);
        log.info("SIP deleted with ID: {}", id);
    }

    /**
     * Updates the SIP with the specified ID based on the provided request.
     *
     * @param id               the ID of the SIP to be updated
     * @param updateSIPRequest the request containing the updated SIP data
     * @return the response containing the updated SIP data
     */
    @Override
    public SIPResponse update(Integer id, UpdateSIPRequest updateSIPRequest) {
        log.info("Updating SIP with ID: {}", id);
        Sip bean = requireOne(id);
        BeanUtils.copyProperties(updateSIPRequest, bean);
        bean = sipRepository.save(bean);
        log.info("SIP updated with ID: {}", id);
        return toModel(bean);
    }

    /**
     * Retrieves the SIP with the specified ID.
     *
     * @param id the ID of the SIP to be retrieved
     * @return the response containing the retrieved SIP data
     */
    @Override
    public SIPResponse getById(Integer id) {
        log.info("Retrieving SIP with ID: {}", id);
        Sip original = requireOne(id);
        log.info("SIP retrieved with ID: {}", id);
        return toModel(original);
    }

    /**
     * Queries SIPs based on the specified query parameters.
     * This method is not supported and will throw an UnsupportedOperationException.
     *
     * @param query the query parameters for the SIP query
     * @return the page of SIP responses matching the query parameters
     * @throws UnsupportedOperationException if the method is called
     */
    @Override
    public Page<SIPResponse> query(SIPQuery query) {
        throw new UnsupportedOperationException();
    }

    /**
     * Converts a Sip entity to a SIPResponse model.
     *
     * @param original the original Sip entity
     * @return the converted SIPResponse model
     */
    private SIPResponse toModel(Sip sip) {
        return SIPMapper.mapSipToSIPResponse(sip);
    }

    /**
     * Retrieves the SIP with the specified ID or throws a NoSuchElementException if not found.
     *
     * @param id the ID of the SIP to be retrieved
     * @return the retrieved SIP entity
     * @throws NoSuchElementException if the SIP with the specified ID is not found
     */
    private Sip requireOne(Integer id) {
        return sipRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }
}
