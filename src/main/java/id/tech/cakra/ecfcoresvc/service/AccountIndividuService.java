package id.tech.cakra.ecfcoresvc.service;

import id.tech.cakra.ecfcoresvc.service.dto.AccountIndividuDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link id.tech.cakra.ecfcoresvc.domain.AccountIndividu}.
 */
public interface AccountIndividuService {

    /**
     * Save a accountIndividu.
     *
     * @param accountIndividuDTO the entity to save.
     * @return the persisted entity.
     */
    AccountIndividuDTO save(AccountIndividuDTO accountIndividuDTO);

    /**
     * Get all the accountIndividus.
     *
     * @return the list of entities.
     */
    List<AccountIndividuDTO> findAll();


    /**
     * Get the "id" accountIndividu.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<AccountIndividuDTO> findOne(Long id);

    /**
     * Delete the "id" accountIndividu.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
