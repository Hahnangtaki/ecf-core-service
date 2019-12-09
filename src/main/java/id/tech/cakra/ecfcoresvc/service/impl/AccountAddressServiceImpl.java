package id.tech.cakra.ecfcoresvc.service.impl;

import id.tech.cakra.ecfcoresvc.service.AccountAddressService;
import id.tech.cakra.ecfcoresvc.domain.AccountAddress;
import id.tech.cakra.ecfcoresvc.repository.AccountAddressRepository;
import id.tech.cakra.ecfcoresvc.service.dto.AccountAddressDTO;
import id.tech.cakra.ecfcoresvc.service.mapper.AccountAddressMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link AccountAddress}.
 */
@Service
@Transactional
public class AccountAddressServiceImpl implements AccountAddressService {

    private final Logger log = LoggerFactory.getLogger(AccountAddressServiceImpl.class);

    private final AccountAddressRepository accountAddressRepository;

    private final AccountAddressMapper accountAddressMapper;

    public AccountAddressServiceImpl(AccountAddressRepository accountAddressRepository, AccountAddressMapper accountAddressMapper) {
        this.accountAddressRepository = accountAddressRepository;
        this.accountAddressMapper = accountAddressMapper;
    }

    /**
     * Save a accountAddress.
     *
     * @param accountAddressDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public AccountAddressDTO save(AccountAddressDTO accountAddressDTO) {
        log.debug("Request to save AccountAddress : {}", accountAddressDTO);
        AccountAddress accountAddress = accountAddressMapper.toEntity(accountAddressDTO);
        accountAddress = accountAddressRepository.save(accountAddress);
        return accountAddressMapper.toDto(accountAddress);
    }

    /**
     * Get all the accountAddresses.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<AccountAddressDTO> findAll() {
        log.debug("Request to get all AccountAddresses");
        return accountAddressRepository.findAll().stream()
            .map(accountAddressMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one accountAddress by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<AccountAddressDTO> findOne(Long id) {
        log.debug("Request to get AccountAddress : {}", id);
        return accountAddressRepository.findById(id)
            .map(accountAddressMapper::toDto);
    }

    /**
     * Delete the accountAddress by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete AccountAddress : {}", id);
        accountAddressRepository.deleteById(id);
    }
}
