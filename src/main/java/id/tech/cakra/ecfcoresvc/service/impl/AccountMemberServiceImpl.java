package id.tech.cakra.ecfcoresvc.service.impl;

import id.tech.cakra.ecfcoresvc.service.AccountMemberService;
import id.tech.cakra.ecfcoresvc.domain.AccountMember;
import id.tech.cakra.ecfcoresvc.repository.AccountMemberRepository;
import id.tech.cakra.ecfcoresvc.service.dto.AccountMemberDTO;
import id.tech.cakra.ecfcoresvc.service.mapper.AccountMemberMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link AccountMember}.
 */
@Service
@Transactional
public class AccountMemberServiceImpl implements AccountMemberService {

    private final Logger log = LoggerFactory.getLogger(AccountMemberServiceImpl.class);

    private final AccountMemberRepository accountMemberRepository;

    private final AccountMemberMapper accountMemberMapper;

    public AccountMemberServiceImpl(AccountMemberRepository accountMemberRepository, AccountMemberMapper accountMemberMapper) {
        this.accountMemberRepository = accountMemberRepository;
        this.accountMemberMapper = accountMemberMapper;
    }

    /**
     * Save a accountMember.
     *
     * @param accountMemberDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public AccountMemberDTO save(AccountMemberDTO accountMemberDTO) {
        log.debug("Request to save AccountMember : {}", accountMemberDTO);
        AccountMember accountMember = accountMemberMapper.toEntity(accountMemberDTO);
        accountMember = accountMemberRepository.save(accountMember);
        return accountMemberMapper.toDto(accountMember);
    }

    /**
     * Get all the accountMembers.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<AccountMemberDTO> findAll(Pageable pageable) {
        log.debug("Request to get all AccountMembers");
        return accountMemberRepository.findAll(pageable)
            .map(accountMemberMapper::toDto);
    }


    /**
     * Get one accountMember by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<AccountMemberDTO> findOne(Long id) {
        log.debug("Request to get AccountMember : {}", id);
        return accountMemberRepository.findById(id)
            .map(accountMemberMapper::toDto);
    }

    /**
     * Delete the accountMember by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete AccountMember : {}", id);
        accountMemberRepository.deleteById(id);
    }
}
