package id.tech.cakra.ecfcoresvc.service;

import id.tech.cakra.ecfcoresvc.service.dto.AccountAuthorizeDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link id.tech.cakra.ecfcoresvc.domain.AccountAuthorize}.
 */
public interface AccountAuthorizeService {

    /**
     * Save a accountAuthorize.
     *
     * @param accountAuthorizeDTO the entity to save.
     * @return the persisted entity.
     */
    AccountAuthorizeDTO save(AccountAuthorizeDTO accountAuthorizeDTO);

    /**
     * Get all the accountAuthorizes.
     *
     * @return the list of entities.
     */
    List<AccountAuthorizeDTO> findAll();


    /**
     * Get the "id" accountAuthorize.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<AccountAuthorizeDTO> findOne(Long id);

    /**
     * Delete the "id" accountAuthorize.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
