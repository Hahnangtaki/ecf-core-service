package id.tech.cakra.ecfcoresvc.service.impl;

import id.tech.cakra.ecfcoresvc.service.AccountBankService;
import id.tech.cakra.ecfcoresvc.domain.AccountBank;
import id.tech.cakra.ecfcoresvc.repository.AccountBankRepository;
import id.tech.cakra.ecfcoresvc.service.dto.AccountBankDTO;
import id.tech.cakra.ecfcoresvc.service.mapper.AccountBankMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Service Implementation for managing {@link AccountBank}.
 */
@Service
@Transactional
public class AccountBankServiceImpl implements AccountBankService {

    private final Logger log = LoggerFactory.getLogger(AccountBankServiceImpl.class);

    private final AccountBankRepository accountBankRepository;

    private final AccountBankMapper accountBankMapper;

    public AccountBankServiceImpl(AccountBankRepository accountBankRepository, AccountBankMapper accountBankMapper) {
        this.accountBankRepository = accountBankRepository;
        this.accountBankMapper = accountBankMapper;
    }

    /**
     * Save a accountBank.
     *
     * @param accountBankDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public AccountBankDTO save(AccountBankDTO accountBankDTO) {
        log.debug("Request to save AccountBank : {}", accountBankDTO);
        AccountBank accountBank = accountBankMapper.toEntity(accountBankDTO);
        accountBank = accountBankRepository.save(accountBank);
        return accountBankMapper.toDto(accountBank);
    }

    /**
     * Get all the accountBanks.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<AccountBankDTO> findAll() {
        log.debug("Request to get all AccountBanks");
        return accountBankRepository.findAll().stream()
            .map(accountBankMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }



    /**
    *  Get all the accountBanks where AccountMember is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true) 
    public List<AccountBankDTO> findAllWhereAccountMemberIsNull() {
        log.debug("Request to get all accountBanks where AccountMember is null");
        return StreamSupport
            .stream(accountBankRepository.findAll().spliterator(), false)
            .filter(accountBank -> accountBank.getAccountMember() == null)
            .map(accountBankMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one accountBank by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<AccountBankDTO> findOne(Long id) {
        log.debug("Request to get AccountBank : {}", id);
        return accountBankRepository.findById(id)
            .map(accountBankMapper::toDto);
    }

    /**
     * Delete the accountBank by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete AccountBank : {}", id);
        accountBankRepository.deleteById(id);
    }
}
