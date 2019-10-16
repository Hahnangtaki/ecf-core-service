package id.tech.cakra.ecfcoresvc.web.rest;

import id.tech.cakra.ecfcoresvc.EcfcoresvcApp;
import id.tech.cakra.ecfcoresvc.domain.AccountMember;
import id.tech.cakra.ecfcoresvc.repository.AccountMemberRepository;
import id.tech.cakra.ecfcoresvc.service.AccountMemberService;
import id.tech.cakra.ecfcoresvc.service.dto.AccountMemberDTO;
import id.tech.cakra.ecfcoresvc.service.mapper.AccountMemberMapper;
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
 * Integration tests for the {@link AccountMemberResource} REST controller.
 */
@SpringBootTest(classes = EcfcoresvcApp.class)
public class AccountMemberResourceIT {

    private static final String DEFAULT_ACCOUNT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_ACCOUNT_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_ACCOUNT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ACCOUNT_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ACCOUNT_TYPE = "A";
    private static final String UPDATED_ACCOUNT_TYPE = "B";

    private static final Boolean DEFAULT_ACCOUNT_ANGEL = false;
    private static final Boolean UPDATED_ACCOUNT_ANGEL = true;

    private static final String DEFAULT_KSEI_CLIENT_CODE = "AAAA";
    private static final String UPDATED_KSEI_CLIENT_CODE = "BBBB";

    private static final String DEFAULT_KSEI_SUBREK = "AAAAAAAAAA";
    private static final String UPDATED_KSEI_SUBREK = "BBBBBBBBBB";

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
    private AccountMemberRepository accountMemberRepository;

    @Autowired
    private AccountMemberMapper accountMemberMapper;

    @Autowired
    private AccountMemberService accountMemberService;

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

    private MockMvc restAccountMemberMockMvc;

    private AccountMember accountMember;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AccountMemberResource accountMemberResource = new AccountMemberResource(accountMemberService);
        this.restAccountMemberMockMvc = MockMvcBuilders.standaloneSetup(accountMemberResource)
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
    public static AccountMember createEntity(EntityManager em) {
        AccountMember accountMember = new AccountMember()
            .accountCode(DEFAULT_ACCOUNT_CODE)
            .accountName(DEFAULT_ACCOUNT_NAME)
            .accountType(DEFAULT_ACCOUNT_TYPE)
            .accountAngel(DEFAULT_ACCOUNT_ANGEL)
            .kseiClientCode(DEFAULT_KSEI_CLIENT_CODE)
            .kseiSubrek(DEFAULT_KSEI_SUBREK)
            .createSystemDate(DEFAULT_CREATE_SYSTEM_DATE)
            .createDate(DEFAULT_CREATE_DATE)
            .createUserId(DEFAULT_CREATE_USER_ID)
            .lastModificationSystemDate(DEFAULT_LAST_MODIFICATION_SYSTEM_DATE)
            .lastModificationDate(DEFAULT_LAST_MODIFICATION_DATE)
            .lastModificationUserId(DEFAULT_LAST_MODIFICATION_USER_ID);
        return accountMember;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AccountMember createUpdatedEntity(EntityManager em) {
        AccountMember accountMember = new AccountMember()
            .accountCode(UPDATED_ACCOUNT_CODE)
            .accountName(UPDATED_ACCOUNT_NAME)
            .accountType(UPDATED_ACCOUNT_TYPE)
            .accountAngel(UPDATED_ACCOUNT_ANGEL)
            .kseiClientCode(UPDATED_KSEI_CLIENT_CODE)
            .kseiSubrek(UPDATED_KSEI_SUBREK)
            .createSystemDate(UPDATED_CREATE_SYSTEM_DATE)
            .createDate(UPDATED_CREATE_DATE)
            .createUserId(UPDATED_CREATE_USER_ID)
            .lastModificationSystemDate(UPDATED_LAST_MODIFICATION_SYSTEM_DATE)
            .lastModificationDate(UPDATED_LAST_MODIFICATION_DATE)
            .lastModificationUserId(UPDATED_LAST_MODIFICATION_USER_ID);
        return accountMember;
    }

    @BeforeEach
    public void initTest() {
        accountMember = createEntity(em);
    }

    @Test
    @Transactional
    public void createAccountMember() throws Exception {
        int databaseSizeBeforeCreate = accountMemberRepository.findAll().size();

        // Create the AccountMember
        AccountMemberDTO accountMemberDTO = accountMemberMapper.toDto(accountMember);
        restAccountMemberMockMvc.perform(post("/api/account-members")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(accountMemberDTO)))
            .andExpect(status().isCreated());

        // Validate the AccountMember in the database
        List<AccountMember> accountMemberList = accountMemberRepository.findAll();
        assertThat(accountMemberList).hasSize(databaseSizeBeforeCreate + 1);
        AccountMember testAccountMember = accountMemberList.get(accountMemberList.size() - 1);
        assertThat(testAccountMember.getAccountCode()).isEqualTo(DEFAULT_ACCOUNT_CODE);
        assertThat(testAccountMember.getAccountName()).isEqualTo(DEFAULT_ACCOUNT_NAME);
        assertThat(testAccountMember.getAccountType()).isEqualTo(DEFAULT_ACCOUNT_TYPE);
        assertThat(testAccountMember.isAccountAngel()).isEqualTo(DEFAULT_ACCOUNT_ANGEL);
        assertThat(testAccountMember.getKseiClientCode()).isEqualTo(DEFAULT_KSEI_CLIENT_CODE);
        assertThat(testAccountMember.getKseiSubrek()).isEqualTo(DEFAULT_KSEI_SUBREK);
        assertThat(testAccountMember.getCreateSystemDate()).isEqualTo(DEFAULT_CREATE_SYSTEM_DATE);
        assertThat(testAccountMember.getCreateDate()).isEqualTo(DEFAULT_CREATE_DATE);
        assertThat(testAccountMember.getCreateUserId()).isEqualTo(DEFAULT_CREATE_USER_ID);
        assertThat(testAccountMember.getLastModificationSystemDate()).isEqualTo(DEFAULT_LAST_MODIFICATION_SYSTEM_DATE);
        assertThat(testAccountMember.getLastModificationDate()).isEqualTo(DEFAULT_LAST_MODIFICATION_DATE);
        assertThat(testAccountMember.getLastModificationUserId()).isEqualTo(DEFAULT_LAST_MODIFICATION_USER_ID);
    }

    @Test
    @Transactional
    public void createAccountMemberWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = accountMemberRepository.findAll().size();

        // Create the AccountMember with an existing ID
        accountMember.setId(1L);
        AccountMemberDTO accountMemberDTO = accountMemberMapper.toDto(accountMember);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAccountMemberMockMvc.perform(post("/api/account-members")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(accountMemberDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AccountMember in the database
        List<AccountMember> accountMemberList = accountMemberRepository.findAll();
        assertThat(accountMemberList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllAccountMembers() throws Exception {
        // Initialize the database
        accountMemberRepository.saveAndFlush(accountMember);

        // Get all the accountMemberList
        restAccountMemberMockMvc.perform(get("/api/account-members?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(accountMember.getId().intValue())))
            .andExpect(jsonPath("$.[*].accountCode").value(hasItem(DEFAULT_ACCOUNT_CODE)))
            .andExpect(jsonPath("$.[*].accountName").value(hasItem(DEFAULT_ACCOUNT_NAME)))
            .andExpect(jsonPath("$.[*].accountType").value(hasItem(DEFAULT_ACCOUNT_TYPE)))
            .andExpect(jsonPath("$.[*].accountAngel").value(hasItem(DEFAULT_ACCOUNT_ANGEL.booleanValue())))
            .andExpect(jsonPath("$.[*].kseiClientCode").value(hasItem(DEFAULT_KSEI_CLIENT_CODE)))
            .andExpect(jsonPath("$.[*].kseiSubrek").value(hasItem(DEFAULT_KSEI_SUBREK)))
            .andExpect(jsonPath("$.[*].createSystemDate").value(hasItem(DEFAULT_CREATE_SYSTEM_DATE.toString())))
            .andExpect(jsonPath("$.[*].createDate").value(hasItem(sameInstant(DEFAULT_CREATE_DATE))))
            .andExpect(jsonPath("$.[*].createUserId").value(hasItem(DEFAULT_CREATE_USER_ID.intValue())))
            .andExpect(jsonPath("$.[*].lastModificationSystemDate").value(hasItem(DEFAULT_LAST_MODIFICATION_SYSTEM_DATE.toString())))
            .andExpect(jsonPath("$.[*].lastModificationDate").value(hasItem(sameInstant(DEFAULT_LAST_MODIFICATION_DATE))))
            .andExpect(jsonPath("$.[*].lastModificationUserId").value(hasItem(DEFAULT_LAST_MODIFICATION_USER_ID.intValue())));
    }
    
    @Test
    @Transactional
    public void getAccountMember() throws Exception {
        // Initialize the database
        accountMemberRepository.saveAndFlush(accountMember);

        // Get the accountMember
        restAccountMemberMockMvc.perform(get("/api/account-members/{id}", accountMember.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(accountMember.getId().intValue()))
            .andExpect(jsonPath("$.accountCode").value(DEFAULT_ACCOUNT_CODE))
            .andExpect(jsonPath("$.accountName").value(DEFAULT_ACCOUNT_NAME))
            .andExpect(jsonPath("$.accountType").value(DEFAULT_ACCOUNT_TYPE))
            .andExpect(jsonPath("$.accountAngel").value(DEFAULT_ACCOUNT_ANGEL.booleanValue()))
            .andExpect(jsonPath("$.kseiClientCode").value(DEFAULT_KSEI_CLIENT_CODE))
            .andExpect(jsonPath("$.kseiSubrek").value(DEFAULT_KSEI_SUBREK))
            .andExpect(jsonPath("$.createSystemDate").value(DEFAULT_CREATE_SYSTEM_DATE.toString()))
            .andExpect(jsonPath("$.createDate").value(sameInstant(DEFAULT_CREATE_DATE)))
            .andExpect(jsonPath("$.createUserId").value(DEFAULT_CREATE_USER_ID.intValue()))
            .andExpect(jsonPath("$.lastModificationSystemDate").value(DEFAULT_LAST_MODIFICATION_SYSTEM_DATE.toString()))
            .andExpect(jsonPath("$.lastModificationDate").value(sameInstant(DEFAULT_LAST_MODIFICATION_DATE)))
            .andExpect(jsonPath("$.lastModificationUserId").value(DEFAULT_LAST_MODIFICATION_USER_ID.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingAccountMember() throws Exception {
        // Get the accountMember
        restAccountMemberMockMvc.perform(get("/api/account-members/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAccountMember() throws Exception {
        // Initialize the database
        accountMemberRepository.saveAndFlush(accountMember);

        int databaseSizeBeforeUpdate = accountMemberRepository.findAll().size();

        // Update the accountMember
        AccountMember updatedAccountMember = accountMemberRepository.findById(accountMember.getId()).get();
        // Disconnect from session so that the updates on updatedAccountMember are not directly saved in db
        em.detach(updatedAccountMember);
        updatedAccountMember
            .accountCode(UPDATED_ACCOUNT_CODE)
            .accountName(UPDATED_ACCOUNT_NAME)
            .accountType(UPDATED_ACCOUNT_TYPE)
            .accountAngel(UPDATED_ACCOUNT_ANGEL)
            .kseiClientCode(UPDATED_KSEI_CLIENT_CODE)
            .kseiSubrek(UPDATED_KSEI_SUBREK)
            .createSystemDate(UPDATED_CREATE_SYSTEM_DATE)
            .createDate(UPDATED_CREATE_DATE)
            .createUserId(UPDATED_CREATE_USER_ID)
            .lastModificationSystemDate(UPDATED_LAST_MODIFICATION_SYSTEM_DATE)
            .lastModificationDate(UPDATED_LAST_MODIFICATION_DATE)
            .lastModificationUserId(UPDATED_LAST_MODIFICATION_USER_ID);
        AccountMemberDTO accountMemberDTO = accountMemberMapper.toDto(updatedAccountMember);

        restAccountMemberMockMvc.perform(put("/api/account-members")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(accountMemberDTO)))
            .andExpect(status().isOk());

        // Validate the AccountMember in the database
        List<AccountMember> accountMemberList = accountMemberRepository.findAll();
        assertThat(accountMemberList).hasSize(databaseSizeBeforeUpdate);
        AccountMember testAccountMember = accountMemberList.get(accountMemberList.size() - 1);
        assertThat(testAccountMember.getAccountCode()).isEqualTo(UPDATED_ACCOUNT_CODE);
        assertThat(testAccountMember.getAccountName()).isEqualTo(UPDATED_ACCOUNT_NAME);
        assertThat(testAccountMember.getAccountType()).isEqualTo(UPDATED_ACCOUNT_TYPE);
        assertThat(testAccountMember.isAccountAngel()).isEqualTo(UPDATED_ACCOUNT_ANGEL);
        assertThat(testAccountMember.getKseiClientCode()).isEqualTo(UPDATED_KSEI_CLIENT_CODE);
        assertThat(testAccountMember.getKseiSubrek()).isEqualTo(UPDATED_KSEI_SUBREK);
        assertThat(testAccountMember.getCreateSystemDate()).isEqualTo(UPDATED_CREATE_SYSTEM_DATE);
        assertThat(testAccountMember.getCreateDate()).isEqualTo(UPDATED_CREATE_DATE);
        assertThat(testAccountMember.getCreateUserId()).isEqualTo(UPDATED_CREATE_USER_ID);
        assertThat(testAccountMember.getLastModificationSystemDate()).isEqualTo(UPDATED_LAST_MODIFICATION_SYSTEM_DATE);
        assertThat(testAccountMember.getLastModificationDate()).isEqualTo(UPDATED_LAST_MODIFICATION_DATE);
        assertThat(testAccountMember.getLastModificationUserId()).isEqualTo(UPDATED_LAST_MODIFICATION_USER_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingAccountMember() throws Exception {
        int databaseSizeBeforeUpdate = accountMemberRepository.findAll().size();

        // Create the AccountMember
        AccountMemberDTO accountMemberDTO = accountMemberMapper.toDto(accountMember);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAccountMemberMockMvc.perform(put("/api/account-members")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(accountMemberDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AccountMember in the database
        List<AccountMember> accountMemberList = accountMemberRepository.findAll();
        assertThat(accountMemberList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAccountMember() throws Exception {
        // Initialize the database
        accountMemberRepository.saveAndFlush(accountMember);

        int databaseSizeBeforeDelete = accountMemberRepository.findAll().size();

        // Delete the accountMember
        restAccountMemberMockMvc.perform(delete("/api/account-members/{id}", accountMember.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AccountMember> accountMemberList = accountMemberRepository.findAll();
        assertThat(accountMemberList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AccountMember.class);
        AccountMember accountMember1 = new AccountMember();
        accountMember1.setId(1L);
        AccountMember accountMember2 = new AccountMember();
        accountMember2.setId(accountMember1.getId());
        assertThat(accountMember1).isEqualTo(accountMember2);
        accountMember2.setId(2L);
        assertThat(accountMember1).isNotEqualTo(accountMember2);
        accountMember1.setId(null);
        assertThat(accountMember1).isNotEqualTo(accountMember2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AccountMemberDTO.class);
        AccountMemberDTO accountMemberDTO1 = new AccountMemberDTO();
        accountMemberDTO1.setId(1L);
        AccountMemberDTO accountMemberDTO2 = new AccountMemberDTO();
        assertThat(accountMemberDTO1).isNotEqualTo(accountMemberDTO2);
        accountMemberDTO2.setId(accountMemberDTO1.getId());
        assertThat(accountMemberDTO1).isEqualTo(accountMemberDTO2);
        accountMemberDTO2.setId(2L);
        assertThat(accountMemberDTO1).isNotEqualTo(accountMemberDTO2);
        accountMemberDTO1.setId(null);
        assertThat(accountMemberDTO1).isNotEqualTo(accountMemberDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(accountMemberMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(accountMemberMapper.fromId(null)).isNull();
    }
}
