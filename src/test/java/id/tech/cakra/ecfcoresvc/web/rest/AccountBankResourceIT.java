package id.tech.cakra.ecfcoresvc.web.rest;

import id.tech.cakra.ecfcoresvc.EcfcoresvcApp;
import id.tech.cakra.ecfcoresvc.domain.AccountBank;
import id.tech.cakra.ecfcoresvc.repository.AccountBankRepository;
import id.tech.cakra.ecfcoresvc.service.AccountBankService;
import id.tech.cakra.ecfcoresvc.service.dto.AccountBankDTO;
import id.tech.cakra.ecfcoresvc.service.mapper.AccountBankMapper;
import id.tech.cakra.ecfcoresvc.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.ZoneOffset;
import java.time.ZoneId;
import java.util.List;

import static id.tech.cakra.ecfcoresvc.web.rest.TestUtil.sameInstant;
import static id.tech.cakra.ecfcoresvc.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link AccountBankResource} REST controller.
 */
@SpringBootTest(classes = EcfcoresvcApp.class)
public class AccountBankResourceIT {

    private static final String DEFAULT_BANK_ACCOUNT_NO = "AAAAAAAAAA";
    private static final String UPDATED_BANK_ACCOUNT_NO = "BBBBBBBBBB";

    private static final String DEFAULT_BANK_ACCOUNT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_BANK_ACCOUNT_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_BANK_BRANCH = "AAAAAAAAAA";
    private static final String UPDATED_BANK_BRANCH = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS = "A";
    private static final String UPDATED_STATUS = "B";

    private static final Long DEFAULT_CURRENCY_ID = 1L;
    private static final Long UPDATED_CURRENCY_ID = 2L;

    private static final Long DEFAULT_BANK_ID = 1L;
    private static final Long UPDATED_BANK_ID = 2L;

    private static final LocalDate DEFAULT_CREATE_SYSTEM_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATE_SYSTEM_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final ZonedDateTime DEFAULT_CREATE_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CREATE_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final Long DEFAULT_CREATE_USER_ID = 1L;
    private static final Long UPDATED_CREATE_USER_ID = 2L;

    private static final LocalDate DEFAULT_LAST_MODIFICATION_SYSTEM_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_LAST_MODIFICATION_SYSTEM_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final ZonedDateTime DEFAULT_LAST_MODIFICATION_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_LAST_MODIFICATION_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final Long DEFAULT_LAST_MODIFICATION_USER_ID = 1L;
    private static final Long UPDATED_LAST_MODIFICATION_USER_ID = 2L;

    @Autowired
    private AccountBankRepository accountBankRepository;

    @Autowired
    private AccountBankMapper accountBankMapper;

    @Autowired
    private AccountBankService accountBankService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restAccountBankMockMvc;

    private AccountBank accountBank;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AccountBankResource accountBankResource = new AccountBankResource(accountBankService);
        this.restAccountBankMockMvc = MockMvcBuilders.standaloneSetup(accountBankResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AccountBank createEntity(EntityManager em) {
        AccountBank accountBank = new AccountBank()
            .bankAccountNo(DEFAULT_BANK_ACCOUNT_NO)
            .bankAccountName(DEFAULT_BANK_ACCOUNT_NAME)
            .bankBranch(DEFAULT_BANK_BRANCH)
            .status(DEFAULT_STATUS)
            .currencyId(DEFAULT_CURRENCY_ID)
            .bankId(DEFAULT_BANK_ID)
            .createSystemDate(DEFAULT_CREATE_SYSTEM_DATE)
            .createDate(DEFAULT_CREATE_DATE)
            .createUserId(DEFAULT_CREATE_USER_ID)
            .lastModificationSystemDate(DEFAULT_LAST_MODIFICATION_SYSTEM_DATE)
            .lastModificationDate(DEFAULT_LAST_MODIFICATION_DATE)
            .lastModificationUserId(DEFAULT_LAST_MODIFICATION_USER_ID);
        return accountBank;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AccountBank createUpdatedEntity(EntityManager em) {
        AccountBank accountBank = new AccountBank()
            .bankAccountNo(UPDATED_BANK_ACCOUNT_NO)
            .bankAccountName(UPDATED_BANK_ACCOUNT_NAME)
            .bankBranch(UPDATED_BANK_BRANCH)
            .status(UPDATED_STATUS)
            .currencyId(UPDATED_CURRENCY_ID)
            .bankId(UPDATED_BANK_ID)
            .createSystemDate(UPDATED_CREATE_SYSTEM_DATE)
            .createDate(UPDATED_CREATE_DATE)
            .createUserId(UPDATED_CREATE_USER_ID)
            .lastModificationSystemDate(UPDATED_LAST_MODIFICATION_SYSTEM_DATE)
            .lastModificationDate(UPDATED_LAST_MODIFICATION_DATE)
            .lastModificationUserId(UPDATED_LAST_MODIFICATION_USER_ID);
        return accountBank;
    }

    @BeforeEach
    public void initTest() {
        accountBank = createEntity(em);
    }

    @Test
    @Transactional
    public void createAccountBank() throws Exception {
        int databaseSizeBeforeCreate = accountBankRepository.findAll().size();

        // Create the AccountBank
        AccountBankDTO accountBankDTO = accountBankMapper.toDto(accountBank);
        restAccountBankMockMvc.perform(post("/api/account-banks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(accountBankDTO)))
            .andExpect(status().isCreated());

        // Validate the AccountBank in the database
        List<AccountBank> accountBankList = accountBankRepository.findAll();
        assertThat(accountBankList).hasSize(databaseSizeBeforeCreate + 1);
        AccountBank testAccountBank = accountBankList.get(accountBankList.size() - 1);
        assertThat(testAccountBank.getBankAccountNo()).isEqualTo(DEFAULT_BANK_ACCOUNT_NO);
        assertThat(testAccountBank.getBankAccountName()).isEqualTo(DEFAULT_BANK_ACCOUNT_NAME);
        assertThat(testAccountBank.getBankBranch()).isEqualTo(DEFAULT_BANK_BRANCH);
        assertThat(testAccountBank.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testAccountBank.getCurrencyId()).isEqualTo(DEFAULT_CURRENCY_ID);
        assertThat(testAccountBank.getBankId()).isEqualTo(DEFAULT_BANK_ID);
        assertThat(testAccountBank.getCreateSystemDate()).isEqualTo(DEFAULT_CREATE_SYSTEM_DATE);
        assertThat(testAccountBank.getCreateDate()).isEqualTo(DEFAULT_CREATE_DATE);
        assertThat(testAccountBank.getCreateUserId()).isEqualTo(DEFAULT_CREATE_USER_ID);
        assertThat(testAccountBank.getLastModificationSystemDate()).isEqualTo(DEFAULT_LAST_MODIFICATION_SYSTEM_DATE);
        assertThat(testAccountBank.getLastModificationDate()).isEqualTo(DEFAULT_LAST_MODIFICATION_DATE);
        assertThat(testAccountBank.getLastModificationUserId()).isEqualTo(DEFAULT_LAST_MODIFICATION_USER_ID);
    }

    @Test
    @Transactional
    public void createAccountBankWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = accountBankRepository.findAll().size();

        // Create the AccountBank with an existing ID
        accountBank.setId(1L);
        AccountBankDTO accountBankDTO = accountBankMapper.toDto(accountBank);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAccountBankMockMvc.perform(post("/api/account-banks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(accountBankDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AccountBank in the database
        List<AccountBank> accountBankList = accountBankRepository.findAll();
        assertThat(accountBankList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllAccountBanks() throws Exception {
        // Initialize the database
        accountBankRepository.saveAndFlush(accountBank);

        // Get all the accountBankList
        restAccountBankMockMvc.perform(get("/api/account-banks?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(accountBank.getId().intValue())))
            .andExpect(jsonPath("$.[*].bankAccountNo").value(hasItem(DEFAULT_BANK_ACCOUNT_NO)))
            .andExpect(jsonPath("$.[*].bankAccountName").value(hasItem(DEFAULT_BANK_ACCOUNT_NAME)))
            .andExpect(jsonPath("$.[*].bankBranch").value(hasItem(DEFAULT_BANK_BRANCH)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].currencyId").value(hasItem(DEFAULT_CURRENCY_ID.intValue())))
            .andExpect(jsonPath("$.[*].bankId").value(hasItem(DEFAULT_BANK_ID.intValue())))
            .andExpect(jsonPath("$.[*].createSystemDate").value(hasItem(DEFAULT_CREATE_SYSTEM_DATE.toString())))
            .andExpect(jsonPath("$.[*].createDate").value(hasItem(sameInstant(DEFAULT_CREATE_DATE))))
            .andExpect(jsonPath("$.[*].createUserId").value(hasItem(DEFAULT_CREATE_USER_ID.intValue())))
            .andExpect(jsonPath("$.[*].lastModificationSystemDate").value(hasItem(DEFAULT_LAST_MODIFICATION_SYSTEM_DATE.toString())))
            .andExpect(jsonPath("$.[*].lastModificationDate").value(hasItem(sameInstant(DEFAULT_LAST_MODIFICATION_DATE))))
            .andExpect(jsonPath("$.[*].lastModificationUserId").value(hasItem(DEFAULT_LAST_MODIFICATION_USER_ID.intValue())));
    }
    
    @Test
    @Transactional
    public void getAccountBank() throws Exception {
        // Initialize the database
        accountBankRepository.saveAndFlush(accountBank);

        // Get the accountBank
        restAccountBankMockMvc.perform(get("/api/account-banks/{id}", accountBank.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(accountBank.getId().intValue()))
            .andExpect(jsonPath("$.bankAccountNo").value(DEFAULT_BANK_ACCOUNT_NO))
            .andExpect(jsonPath("$.bankAccountName").value(DEFAULT_BANK_ACCOUNT_NAME))
            .andExpect(jsonPath("$.bankBranch").value(DEFAULT_BANK_BRANCH))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.currencyId").value(DEFAULT_CURRENCY_ID.intValue()))
            .andExpect(jsonPath("$.bankId").value(DEFAULT_BANK_ID.intValue()))
            .andExpect(jsonPath("$.createSystemDate").value(DEFAULT_CREATE_SYSTEM_DATE.toString()))
            .andExpect(jsonPath("$.createDate").value(sameInstant(DEFAULT_CREATE_DATE)))
            .andExpect(jsonPath("$.createUserId").value(DEFAULT_CREATE_USER_ID.intValue()))
            .andExpect(jsonPath("$.lastModificationSystemDate").value(DEFAULT_LAST_MODIFICATION_SYSTEM_DATE.toString()))
            .andExpect(jsonPath("$.lastModificationDate").value(sameInstant(DEFAULT_LAST_MODIFICATION_DATE)))
            .andExpect(jsonPath("$.lastModificationUserId").value(DEFAULT_LAST_MODIFICATION_USER_ID.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingAccountBank() throws Exception {
        // Get the accountBank
        restAccountBankMockMvc.perform(get("/api/account-banks/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAccountBank() throws Exception {
        // Initialize the database
        accountBankRepository.saveAndFlush(accountBank);

        int databaseSizeBeforeUpdate = accountBankRepository.findAll().size();

        // Update the accountBank
        AccountBank updatedAccountBank = accountBankRepository.findById(accountBank.getId()).get();
        // Disconnect from session so that the updates on updatedAccountBank are not directly saved in db
        em.detach(updatedAccountBank);
        updatedAccountBank
            .bankAccountNo(UPDATED_BANK_ACCOUNT_NO)
            .bankAccountName(UPDATED_BANK_ACCOUNT_NAME)
            .bankBranch(UPDATED_BANK_BRANCH)
            .status(UPDATED_STATUS)
            .currencyId(UPDATED_CURRENCY_ID)
            .bankId(UPDATED_BANK_ID)
            .createSystemDate(UPDATED_CREATE_SYSTEM_DATE)
            .createDate(UPDATED_CREATE_DATE)
            .createUserId(UPDATED_CREATE_USER_ID)
            .lastModificationSystemDate(UPDATED_LAST_MODIFICATION_SYSTEM_DATE)
            .lastModificationDate(UPDATED_LAST_MODIFICATION_DATE)
            .lastModificationUserId(UPDATED_LAST_MODIFICATION_USER_ID);
        AccountBankDTO accountBankDTO = accountBankMapper.toDto(updatedAccountBank);

        restAccountBankMockMvc.perform(put("/api/account-banks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(accountBankDTO)))
            .andExpect(status().isOk());

        // Validate the AccountBank in the database
        List<AccountBank> accountBankList = accountBankRepository.findAll();
        assertThat(accountBankList).hasSize(databaseSizeBeforeUpdate);
        AccountBank testAccountBank = accountBankList.get(accountBankList.size() - 1);
        assertThat(testAccountBank.getBankAccountNo()).isEqualTo(UPDATED_BANK_ACCOUNT_NO);
        assertThat(testAccountBank.getBankAccountName()).isEqualTo(UPDATED_BANK_ACCOUNT_NAME);
        assertThat(testAccountBank.getBankBranch()).isEqualTo(UPDATED_BANK_BRANCH);
        assertThat(testAccountBank.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testAccountBank.getCurrencyId()).isEqualTo(UPDATED_CURRENCY_ID);
        assertThat(testAccountBank.getBankId()).isEqualTo(UPDATED_BANK_ID);
        assertThat(testAccountBank.getCreateSystemDate()).isEqualTo(UPDATED_CREATE_SYSTEM_DATE);
        assertThat(testAccountBank.getCreateDate()).isEqualTo(UPDATED_CREATE_DATE);
        assertThat(testAccountBank.getCreateUserId()).isEqualTo(UPDATED_CREATE_USER_ID);
        assertThat(testAccountBank.getLastModificationSystemDate()).isEqualTo(UPDATED_LAST_MODIFICATION_SYSTEM_DATE);
        assertThat(testAccountBank.getLastModificationDate()).isEqualTo(UPDATED_LAST_MODIFICATION_DATE);
        assertThat(testAccountBank.getLastModificationUserId()).isEqualTo(UPDATED_LAST_MODIFICATION_USER_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingAccountBank() throws Exception {
        int databaseSizeBeforeUpdate = accountBankRepository.findAll().size();

        // Create the AccountBank
        AccountBankDTO accountBankDTO = accountBankMapper.toDto(accountBank);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAccountBankMockMvc.perform(put("/api/account-banks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(accountBankDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AccountBank in the database
        List<AccountBank> accountBankList = accountBankRepository.findAll();
        assertThat(accountBankList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAccountBank() throws Exception {
        // Initialize the database
        accountBankRepository.saveAndFlush(accountBank);

        int databaseSizeBeforeDelete = accountBankRepository.findAll().size();

        // Delete the accountBank
        restAccountBankMockMvc.perform(delete("/api/account-banks/{id}", accountBank.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AccountBank> accountBankList = accountBankRepository.findAll();
        assertThat(accountBankList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AccountBank.class);
        AccountBank accountBank1 = new AccountBank();
        accountBank1.setId(1L);
        AccountBank accountBank2 = new AccountBank();
        accountBank2.setId(accountBank1.getId());
        assertThat(accountBank1).isEqualTo(accountBank2);
        accountBank2.setId(2L);
        assertThat(accountBank1).isNotEqualTo(accountBank2);
        accountBank1.setId(null);
        assertThat(accountBank1).isNotEqualTo(accountBank2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AccountBankDTO.class);
        AccountBankDTO accountBankDTO1 = new AccountBankDTO();
        accountBankDTO1.setId(1L);
        AccountBankDTO accountBankDTO2 = new AccountBankDTO();
        assertThat(accountBankDTO1).isNotEqualTo(accountBankDTO2);
        accountBankDTO2.setId(accountBankDTO1.getId());
        assertThat(accountBankDTO1).isEqualTo(accountBankDTO2);
        accountBankDTO2.setId(2L);
        assertThat(accountBankDTO1).isNotEqualTo(accountBankDTO2);
        accountBankDTO1.setId(null);
        assertThat(accountBankDTO1).isNotEqualTo(accountBankDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(accountBankMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(accountBankMapper.fromId(null)).isNull();
    }
}
