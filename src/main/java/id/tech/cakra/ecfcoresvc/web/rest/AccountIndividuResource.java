package id.tech.cakra.ecfcoresvc.web.rest;

import id.tech.cakra.ecfcoresvc.service.AccountIndividuService;
import id.tech.cakra.ecfcoresvc.web.rest.errors.BadRequestAlertException;
import id.tech.cakra.ecfcoresvc.service.dto.AccountIndividuDTO;

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
 * REST controller for managing {@link id.tech.cakra.ecfcoresvc.domain.AccountIndividu}.
 */
@RestController
@RequestMapping("/api")
public class AccountIndividuResource {

    private final Logger log = LoggerFactory.getLogger(AccountIndividuResource.class);

    private static final String ENTITY_NAME = "ecfcoresvcAccountIndividu";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AccountIndividuService accountIndividuService;

    public AccountIndividuResource(AccountIndividuService accountIndividuService) {
        this.accountIndividuService = accountIndividuService;
    }

    /**
     * {@code POST  /account-individus} : Create a new accountIndividu.
     *
     * @param accountIndividuDTO the accountIndividuDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new accountIndividuDTO, or with status {@code 400 (Bad Request)} if the accountIndividu has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/account-individus")
    public ResponseEntity<AccountIndividuDTO> createAccountIndividu(@Valid @RequestBody AccountIndividuDTO accountIndividuDTO) throws URISyntaxException {
        log.debug("REST request to save AccountIndividu : {}", accountIndividuDTO);
        if (accountIndividuDTO.getId() != null) {
            throw new BadRequestAlertException("A new accountIndividu cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AccountIndividuDTO result = accountIndividuService.save(accountIndividuDTO);
        return ResponseEntity.created(new URI("/api/account-individus/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /account-individus} : Updates an existing accountIndividu.
     *
     * @param accountIndividuDTO the accountIndividuDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated accountIndividuDTO,
     * or with status {@code 400 (Bad Request)} if the accountIndividuDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the accountIndividuDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/account-individus")
    public ResponseEntity<AccountIndividuDTO> updateAccountIndividu(@Valid @RequestBody AccountIndividuDTO accountIndividuDTO) throws URISyntaxException {
        log.debug("REST request to update AccountIndividu : {}", accountIndividuDTO);
        if (accountIndividuDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AccountIndividuDTO result = accountIndividuService.save(accountIndividuDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, accountIndividuDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /account-individus} : get all the accountIndividus.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of accountIndividus in body.
     */
    @GetMapping("/account-individus")
    public List<AccountIndividuDTO> getAllAccountIndividus() {
        log.debug("REST request to get all AccountIndividus");
        return accountIndividuService.findAll();
    }

    /**
     * {@code GET  /account-individus/:id} : get the "id" accountIndividu.
     *
     * @param id the id of the accountIndividuDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the accountIndividuDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/account-individus/{id}")
    public ResponseEntity<AccountIndividuDTO> getAccountIndividu(@PathVariable Long id) {
        log.debug("REST request to get AccountIndividu : {}", id);
        Optional<AccountIndividuDTO> accountIndividuDTO = accountIndividuService.findOne(id);
        return ResponseUtil.wrapOrNotFound(accountIndividuDTO);
    }

    /**
     * {@code DELETE  /account-individus/:id} : delete the "id" accountIndividu.
     *
     * @param id the id of the accountIndividuDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/account-individus/{id}")
    public ResponseEntity<Void> deleteAccountIndividu(@PathVariable Long id) {
        log.debug("REST request to delete AccountIndividu : {}", id);
        accountIndividuService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
