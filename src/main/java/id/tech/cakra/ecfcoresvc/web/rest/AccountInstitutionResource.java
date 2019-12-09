package id.tech.cakra.ecfcoresvc.web.rest;

import id.tech.cakra.ecfcoresvc.service.AccountInstitutionService;
import id.tech.cakra.ecfcoresvc.web.rest.errors.BadRequestAlertException;
import id.tech.cakra.ecfcoresvc.service.dto.AccountInstitutionDTO;

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
 * REST controller for managing {@link id.tech.cakra.ecfcoresvc.domain.AccountInstitution}.
 */
@RestController
@RequestMapping("/api")
public class AccountInstitutionResource {

    private final Logger log = LoggerFactory.getLogger(AccountInstitutionResource.class);

    private static final String ENTITY_NAME = "ecfcoresvcAccountInstitution";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AccountInstitutionService accountInstitutionService;

    public AccountInstitutionResource(AccountInstitutionService accountInstitutionService) {
        this.accountInstitutionService = accountInstitutionService;
    }

    /**
     * {@code POST  /account-institutions} : Create a new accountInstitution.
     *
     * @param accountInstitutionDTO the accountInstitutionDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new accountInstitutionDTO, or with status {@code 400 (Bad Request)} if the accountInstitution has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/account-institutions")
    public ResponseEntity<AccountInstitutionDTO> createAccountInstitution(@Valid @RequestBody AccountInstitutionDTO accountInstitutionDTO) throws URISyntaxException {
        log.debug("REST request to save AccountInstitution : {}", accountInstitutionDTO);
        if (accountInstitutionDTO.getId() != null) {
            throw new BadRequestAlertException("A new accountInstitution cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AccountInstitutionDTO result = accountInstitutionService.save(accountInstitutionDTO);
        return ResponseEntity.created(new URI("/api/account-institutions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /account-institutions} : Updates an existing accountInstitution.
     *
     * @param accountInstitutionDTO the accountInstitutionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated accountInstitutionDTO,
     * or with status {@code 400 (Bad Request)} if the accountInstitutionDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the accountInstitutionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/account-institutions")
    public ResponseEntity<AccountInstitutionDTO> updateAccountInstitution(@Valid @RequestBody AccountInstitutionDTO accountInstitutionDTO) throws URISyntaxException {
        log.debug("REST request to update AccountInstitution : {}", accountInstitutionDTO);
        if (accountInstitutionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AccountInstitutionDTO result = accountInstitutionService.save(accountInstitutionDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, accountInstitutionDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /account-institutions} : get all the accountInstitutions.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of accountInstitutions in body.
     */
    @GetMapping("/account-institutions")
    public List<AccountInstitutionDTO> getAllAccountInstitutions() {
        log.debug("REST request to get all AccountInstitutions");
        return accountInstitutionService.findAll();
    }

    /**
     * {@code GET  /account-institutions/:id} : get the "id" accountInstitution.
     *
     * @param id the id of the accountInstitutionDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the accountInstitutionDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/account-institutions/{id}")
    public ResponseEntity<AccountInstitutionDTO> getAccountInstitution(@PathVariable Long id) {
        log.debug("REST request to get AccountInstitution : {}", id);
        Optional<AccountInstitutionDTO> accountInstitutionDTO = accountInstitutionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(accountInstitutionDTO);
    }

    /**
     * {@code DELETE  /account-institutions/:id} : delete the "id" accountInstitution.
     *
     * @param id the id of the accountInstitutionDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/account-institutions/{id}")
    public ResponseEntity<Void> deleteAccountInstitution(@PathVariable Long id) {
        log.debug("REST request to delete AccountInstitution : {}", id);
        accountInstitutionService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
