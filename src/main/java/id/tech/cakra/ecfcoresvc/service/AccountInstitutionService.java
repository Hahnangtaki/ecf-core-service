package id.tech.cakra.ecfcoresvc.service;

import id.tech.cakra.ecfcoresvc.service.dto.AccountInstitutionDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link id.tech.cakra.ecfcoresvc.domain.AccountInstitution}.
 */
public interface AccountInstitutionService {

    /**
     * Save a accountInstitution.
     *
     * @param accountInstitutionDTO the entity to save.
     * @return the persisted entity.
     */
    AccountInstitutionDTO save(AccountInstitutionDTO accountInstitutionDTO);

    /**
     * Get all the accountInstitutions.
     *
     * @return the list of entities.
     */
    List<AccountInstitutionDTO> findAll();


    /**
     * Get the "id" accountInstitution.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<AccountInstitutionDTO> findOne(Long id);

    /**
     * Delete the "id" accountInstitution.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
