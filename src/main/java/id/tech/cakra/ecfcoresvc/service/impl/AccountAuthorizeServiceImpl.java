package id.tech.cakra.ecfcoresvc.service.impl;

import id.tech.cakra.ecfcoresvc.service.AccountAuthorizeService;
import id.tech.cakra.ecfcoresvc.domain.AccountAuthorize;
import id.tech.cakra.ecfcoresvc.repository.AccountAuthorizeRepository;
import id.tech.cakra.ecfcoresvc.service.dto.AccountAuthorizeDTO;
import id.tech.cakra.ecfcoresvc.service.mapper.AccountAuthorizeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link AccountAuthorize}.
 */
@Service
@Transactional
public class AccountAuthorizeServiceImpl implements AccountAuthorizeService {

    private final Logger log = LoggerFactory.getLogger(AccountAuthorizeServiceImpl.class);

    private final AccountAuthorizeRepository accountAuthorizeRepository;

    private final AccountAuthorizeMapper accountAuthorizeMapper;

    public AccountAuthorizeServiceImpl(AccountAuthorizeRepository accountAuthorizeRepository, AccountAuthorizeMapper accountAuthorizeMapper) {
        this.accountAuthorizeRepository = accountAuthorizeRepository;
        this.accountAuthorizeMapper = accountAuthorizeMapper;
    }

    /**
     * Save a accountAuthorize.
     *
     * @param accountAuthorizeDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public AccountAuthorizeDTO save(AccountAuthorizeDTO accountAuthorizeDTO) {
        log.debug("Request to save AccountAuthorize : {}", accountAuthorizeDTO);
        AccountAuthorize accountAuthorize = accountAuthorizeMapper.toEntity(accountAuthorizeDTO);
        accountAuthorize = accountAuthorizeRepository.save(accountAuthorize);
        return accountAuthorizeMapper.toDto(accountAuthorize);
    }

    /**
     * Get all the accountAuthorizes.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<AccountAuthorizeDTO> findAll() {
        log.debug("Request to get all AccountAuthorizes");
        return accountAuthorizeRepository.findAll().stream()
            .map(accountAuthorizeMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one accountAuthorize by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<AccountAuthorizeDTO> findOne(Long id) {
        log.debug("Request to get AccountAuthorize : {}", id);
        return accountAuthorizeRepository.findById(id)
            .map(accountAuthorizeMapper::toDto);
    }

    /**
     * Delete the accountAuthorize by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete AccountAuthorize : {}", id);
        accountAuthorizeRepository.deleteById(id);
    }
}
