package id.tech.cakra.ecfcoresvc.service;

import id.tech.cakra.ecfcoresvc.service.dto.AccountMemberDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link id.tech.cakra.ecfcoresvc.domain.AccountMember}.
 */
public interface AccountMemberService {

    /**
     * Save a accountMember.
     *
     * @param accountMemberDTO the entity to save.
     * @return the persisted entity.
     */
    AccountMemberDTO save(AccountMemberDTO accountMemberDTO);

    /**
     * Get all the accountMembers.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<AccountMemberDTO> findAll(Pageable pageable);


    /**
     * Get the "id" accountMember.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<AccountMemberDTO> findOne(Long id);

    /**
     * Delete the "id" accountMember.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
