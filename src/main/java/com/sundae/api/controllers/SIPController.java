package com.sundae.api.controllers;

import com.sundae.api.models.SIPQuery;
import com.sundae.api.models.request.AddSIPRequest;
import com.sundae.api.models.request.UpdateSIPRequest;
import com.sundae.api.models.response.SIPResponse;
import com.sundae.api.services.sip.SIPService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * This class represents the controller for handling SIP (Stock Incentive Plan) related operations.
 */
@Validated
@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("v1/sip")
public class SIPController {

    private final SIPService sipService;

    /**
     * Saves a new SIP record.
     *
     * @param addSipRequest The request object containing the details of the SIP to be added.
     * @return A string representation of the saved SIP record.
     */
    @PostMapping
    public ResponseEntity<SIPResponse> save(@Valid @RequestBody AddSIPRequest addSipRequest) {
        log.info("Saving SIP: {}", addSipRequest);
        SIPResponse sipResponse = sipService.save(addSipRequest);
        return new ResponseEntity<>(sipResponse, HttpStatus.CREATED);
    }

    /**
     * Deletes a SIP record by its ID.
     *
     * @param id The ID of the SIP record to be deleted.
     */
    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") Integer id) {
        log.info("Deleting SIP with ID: {}", id);
        sipService.delete(id);
    }

    /**
     * Updates a SIP record by its ID.
     *
     * @param id               The ID of the SIP record to be updated.
     * @param updateSIPRequest The request object containing the updated details of the SIP.
     */
    @PutMapping("/{id}")
    public void update(
            @Valid @NotNull @PathVariable("id") Integer id, @Valid @RequestBody UpdateSIPRequest updateSIPRequest) {
        log.info("Updating SIP with ID: {}", id);
        log.info("Update request: {}", updateSIPRequest);
        sipService.update(id, updateSIPRequest);
    }

    /**
     * Retrieves a SIP record by its ID.
     *
     * @param id The ID of the SIP record to be retrieved.
     * @return The SIP record matching the given ID.
     */
    @GetMapping("/{id}")
    public SIPResponse getById(@Valid @NotNull @PathVariable("id") Integer id) {
        log.info("Retrieving SIP with ID: {}", id);
        return sipService.getById(id);
    }

    /**
     * Queries SIP records based on the provided query parameters.
     *
     * @param sipQuery The query object containing the parameters for filtering and pagination.
     * @return A page of SIP records matching the query criteria.
     */
    @GetMapping
    public Page<SIPResponse> query(@Valid SIPQuery sipQuery) {
        log.info("Querying SIPs: {}", sipQuery);
        return sipService.query(sipQuery);
    }
}
