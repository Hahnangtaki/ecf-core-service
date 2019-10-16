package id.tech.cakra.ecfcoresvc.service;

import id.tech.cakra.ecfcoresvc.service.dto.AccountBankDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link id.tech.cakra.ecfcoresvc.domain.AccountBank}.
 */
public interface AccountBankService {

    /**
     * Save a accountBank.
     *
     * @param accountBankDTO the entity to save.
     * @return the persisted entity.
     */
    AccountBankDTO save(AccountBankDTO accountBankDTO);

    /**
     * Get all the accountBanks.
     *
     * @return the list of entities.
     */
    List<AccountBankDTO> findAll();
    /**
     * Get all the AccountBankDTO where AccountMember is {@code null}.
     *
     * @return the list of entities.
     */
    List<AccountBankDTO> findAllWhereAccountMemberIsNull();


    /**
     * Get the "id" accountBank.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<AccountBankDTO> findOne(Long id);

    /**
     * Delete the "id" accountBank.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
