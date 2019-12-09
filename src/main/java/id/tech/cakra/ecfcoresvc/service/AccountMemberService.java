package id.tech.cakra.ecfcoresvc.service;

import id.tech.cakra.ecfcoresvc.service.dto.AccountMemberDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
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
     * Get all the AccountMemberDTO where AccountInstitution is {@code null}.
     *
     * @return the list of entities.
     */
    List<AccountMemberDTO> findAllWhereAccountInstitutionIsNull();
    /**
     * Get all the AccountMemberDTO where AccountIndividu is {@code null}.
     *
     * @return the list of entities.
     */
    List<AccountMemberDTO> findAllWhereAccountIndividuIsNull();
    /**
     * Get all the AccountMemberDTO where AccountBank is {@code null}.
     *
     * @return the list of entities.
     */
    List<AccountMemberDTO> findAllWhereAccountBankIsNull();


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
