package id.tech.cakra.ecfcoresvc.service;

import id.tech.cakra.ecfcoresvc.service.dto.AccountAddressDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link id.tech.cakra.ecfcoresvc.domain.AccountAddress}.
 */
public interface AccountAddressService {

    /**
     * Save a accountAddress.
     *
     * @param accountAddressDTO the entity to save.
     * @return the persisted entity.
     */
    AccountAddressDTO save(AccountAddressDTO accountAddressDTO);

    /**
     * Get all the accountAddresses.
     *
     * @return the list of entities.
     */
    List<AccountAddressDTO> findAll();


    /**
     * Get the "id" accountAddress.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<AccountAddressDTO> findOne(Long id);

    /**
     * Delete the "id" accountAddress.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
