package id.tech.cakra.ecfcoresvc.service.impl;

import id.tech.cakra.ecfcoresvc.service.AccountIndividuService;
import id.tech.cakra.ecfcoresvc.domain.AccountIndividu;
import id.tech.cakra.ecfcoresvc.repository.AccountIndividuRepository;
import id.tech.cakra.ecfcoresvc.service.dto.AccountIndividuDTO;
import id.tech.cakra.ecfcoresvc.service.mapper.AccountIndividuMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link AccountIndividu}.
 */
@Service
@Transactional
public class AccountIndividuServiceImpl implements AccountIndividuService {

    private final Logger log = LoggerFactory.getLogger(AccountIndividuServiceImpl.class);

    private final AccountIndividuRepository accountIndividuRepository;

    private final AccountIndividuMapper accountIndividuMapper;

    public AccountIndividuServiceImpl(AccountIndividuRepository accountIndividuRepository, AccountIndividuMapper accountIndividuMapper) {
        this.accountIndividuRepository = accountIndividuRepository;
        this.accountIndividuMapper = accountIndividuMapper;
    }

    /**
     * Save a accountIndividu.
     *
     * @param accountIndividuDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public AccountIndividuDTO save(AccountIndividuDTO accountIndividuDTO) {
        log.debug("Request to save AccountIndividu : {}", accountIndividuDTO);
        AccountIndividu accountIndividu = accountIndividuMapper.toEntity(accountIndividuDTO);
        accountIndividu = accountIndividuRepository.save(accountIndividu);
        return accountIndividuMapper.toDto(accountIndividu);
    }

    /**
     * Get all the accountIndividus.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<AccountIndividuDTO> findAll() {
        log.debug("Request to get all AccountIndividus");
        return accountIndividuRepository.findAll().stream()
            .map(accountIndividuMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one accountIndividu by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<AccountIndividuDTO> findOne(Long id) {
        log.debug("Request to get AccountIndividu : {}", id);
        return accountIndividuRepository.findById(id)
            .map(accountIndividuMapper::toDto);
    }

    /**
     * Delete the accountIndividu by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete AccountIndividu : {}", id);
        accountIndividuRepository.deleteById(id);
    }
}
