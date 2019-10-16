package id.tech.cakra.ecfcoresvc.web.rest;

import id.tech.cakra.ecfcoresvc.service.AccountAuthorizeService;
import id.tech.cakra.ecfcoresvc.web.rest.errors.BadRequestAlertException;
import id.tech.cakra.ecfcoresvc.service.dto.AccountAuthorizeDTO;

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
 * REST controller for managing {@link id.tech.cakra.ecfcoresvc.domain.AccountAuthorize}.
 */
@RestController
@RequestMapping("/api")
public class AccountAuthorizeResource {

    private final Logger log = LoggerFactory.getLogger(AccountAuthorizeResource.class);

    private static final String ENTITY_NAME = "ecfcoresvcAccountAuthorize";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AccountAuthorizeService accountAuthorizeService;

    public AccountAuthorizeResource(AccountAuthorizeService accountAuthorizeService) {
        this.accountAuthorizeService = accountAuthorizeService;
    }

    /**
     * {@code POST  /account-authorizes} : Create a new accountAuthorize.
     *
     * @param accountAuthorizeDTO the accountAuthorizeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new accountAuthorizeDTO, or with status {@code 400 (Bad Request)} if the accountAuthorize has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/account-authorizes")
    public ResponseEntity<AccountAuthorizeDTO> createAccountAuthorize(@Valid @RequestBody AccountAuthorizeDTO accountAuthorizeDTO) throws URISyntaxException {
        log.debug("REST request to save AccountAuthorize : {}", accountAuthorizeDTO);
        if (accountAuthorizeDTO.getId() != null) {
            throw new BadRequestAlertException("A new accountAuthorize cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AccountAuthorizeDTO result = accountAuthorizeService.save(accountAuthorizeDTO);
        return ResponseEntity.created(new URI("/api/account-authorizes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /account-authorizes} : Updates an existing accountAuthorize.
     *
     * @param accountAuthorizeDTO the accountAuthorizeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated accountAuthorizeDTO,
     * or with status {@code 400 (Bad Request)} if the accountAuthorizeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the accountAuthorizeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/account-authorizes")
    public ResponseEntity<AccountAuthorizeDTO> updateAccountAuthorize(@Valid @RequestBody AccountAuthorizeDTO accountAuthorizeDTO) throws URISyntaxException {
        log.debug("REST request to update AccountAuthorize : {}", accountAuthorizeDTO);
        if (accountAuthorizeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AccountAuthorizeDTO result = accountAuthorizeService.save(accountAuthorizeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, accountAuthorizeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /account-authorizes} : get all the accountAuthorizes.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of accountAuthorizes in body.
     */
    @GetMapping("/account-authorizes")
    public List<AccountAuthorizeDTO> getAllAccountAuthorizes() {
        log.debug("REST request to get all AccountAuthorizes");
        return accountAuthorizeService.findAll();
    }

    /**
     * {@code GET  /account-authorizes/:id} : get the "id" accountAuthorize.
     *
     * @param id the id of the accountAuthorizeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the accountAuthorizeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/account-authorizes/{id}")
    public ResponseEntity<AccountAuthorizeDTO> getAccountAuthorize(@PathVariable Long id) {
        log.debug("REST request to get AccountAuthorize : {}", id);
        Optional<AccountAuthorizeDTO> accountAuthorizeDTO = accountAuthorizeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(accountAuthorizeDTO);
    }

    /**
     * {@code DELETE  /account-authorizes/:id} : delete the "id" accountAuthorize.
     *
     * @param id the id of the accountAuthorizeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/account-authorizes/{id}")
    public ResponseEntity<Void> deleteAccountAuthorize(@PathVariable Long id) {
        log.debug("REST request to delete AccountAuthorize : {}", id);
        accountAuthorizeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
