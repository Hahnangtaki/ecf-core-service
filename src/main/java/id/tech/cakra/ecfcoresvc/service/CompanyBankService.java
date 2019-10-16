package id.tech.cakra.ecfcoresvc.service;

import id.tech.cakra.ecfcoresvc.service.dto.CompanyBankDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link id.tech.cakra.ecfcoresvc.domain.CompanyBank}.
 */
public interface CompanyBankService {

    /**
     * Save a companyBank.
     *
     * @param companyBankDTO the entity to save.
     * @return the persisted entity.
     */
    CompanyBankDTO save(CompanyBankDTO companyBankDTO);

    /**
     * Get all the companyBanks.
     *
     * @return the list of entities.
     */
    List<CompanyBankDTO> findAll();


    /**
     * Get the "id" companyBank.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CompanyBankDTO> findOne(Long id);

    /**
     * Delete the "id" companyBank.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
