package id.tech.cakra.ecfcoresvc.web.rest;

import id.tech.cakra.ecfcoresvc.service.AccountAddressService;
import id.tech.cakra.ecfcoresvc.web.rest.errors.BadRequestAlertException;
import id.tech.cakra.ecfcoresvc.service.dto.AccountAddressDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link id.tech.cakra.ecfcoresvc.domain.AccountAddress}.
 */
@RestController
@RequestMapping("/api")
public class AccountAddressResource {

    private final Logger log = LoggerFactory.getLogger(AccountAddressResource.class);

    private static final String ENTITY_NAME = "ecfcoresvcAccountAddress";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AccountAddressService accountAddressService;

    public AccountAddressResource(AccountAddressService accountAddressService) {
        this.accountAddressService = accountAddressService;
    }

    /**
     * {@code POST  /account-addresses} : Create a new accountAddress.
     *
     * @param accountAddressDTO the accountAddressDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new accountAddressDTO, or with status {@code 400 (Bad Request)} if the accountAddress has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/account-addresses")
    public ResponseEntity<AccountAddressDTO> createAccountAddress(@Valid @RequestBody AccountAddressDTO accountAddressDTO) throws URISyntaxException {
        log.debug("REST request to save AccountAddress : {}", accountAddressDTO);
        if (accountAddressDTO.getId() != null) {
            throw new BadRequestAlertException("A new accountAddress cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AccountAddressDTO result = accountAddressService.save(accountAddressDTO);
        return ResponseEntity.created(new URI("/api/account-addresses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /account-addresses} : Updates an existing accountAddress.
     *
     * @param accountAddressDTO the accountAddressDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated accountAddressDTO,
     * or with status {@code 400 (Bad Request)} if the accountAddressDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the accountAddressDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/account-addresses")
    public ResponseEntity<AccountAddressDTO> updateAccountAddress(@Valid @RequestBody AccountAddressDTO accountAddressDTO) throws URISyntaxException {
        log.debug("REST request to update AccountAddress : {}", accountAddressDTO);
        if (accountAddressDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AccountAddressDTO result = accountAddressService.save(accountAddressDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, accountAddressDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /account-addresses} : get all the accountAddresses.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of accountAddresses in body.
     */
    @GetMapping("/account-addresses")
    public List<AccountAddressDTO> getAllAccountAddresses() {
        log.debug("REST request to get all AccountAddresses");
        return accountAddressService.findAll();
    }

    /**
     * {@code GET  /account-addresses/:id} : get the "id" accountAddress.
     *
     * @param id the id of the accountAddressDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the accountAddressDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/account-addresses/{id}")
    public ResponseEntity<AccountAddressDTO> getAccountAddress(@PathVariable Long id) {
        log.debug("REST request to get AccountAddress : {}", id);
        Optional<AccountAddressDTO> accountAddressDTO = accountAddressService.findOne(id);
        return ResponseUtil.wrapOrNotFound(accountAddressDTO);
    }

    /**
     * {@code DELETE  /account-addresses/:id} : delete the "id" accountAddress.
     *
     * @param id the id of the accountAddressDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/account-addresses/{id}")
    public ResponseEntity<Void> deleteAccountAddress(@PathVariable Long id) {
        log.debug("REST request to delete AccountAddress : {}", id);
        accountAddressService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
