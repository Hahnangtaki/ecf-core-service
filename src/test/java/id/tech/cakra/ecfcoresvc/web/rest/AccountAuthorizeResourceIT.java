package id.tech.cakra.ecfcoresvc.web.rest;

import id.tech.cakra.ecfcoresvc.EcfcoresvcApp;
import id.tech.cakra.ecfcoresvc.domain.AccountAuthorize;
import id.tech.cakra.ecfcoresvc.repository.AccountAuthorizeRepository;
import id.tech.cakra.ecfcoresvc.service.AccountAuthorizeService;
import id.tech.cakra.ecfcoresvc.service.dto.AccountAuthorizeDTO;
import id.tech.cakra.ecfcoresvc.service.mapper.AccountAuthorizeMapper;
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
 * Integration tests for the {@link AccountAuthorizeResource} REST controller.
 */
@SpringBootTest(classes = EcfcoresvcApp.class)
public class AccountAuthorizeResourceIT {

    private static final String DEFAULT_FIRST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FIRST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_MIDDLE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_MIDDLE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_LAST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_POSITION = "AAAAAAAAAA";
    private static final String UPDATED_POSITION = "BBBBBBBBBB";

    private static final String DEFAULT_KTP = "AAAAAAAAAA";
    private static final String UPDATED_KTP = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_KTP_EXP_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_KTP_EXP_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_NPWP = "AAAAAAAAAA";
    private static final String UPDATED_NPWP = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_NPWP_REG_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_NPWP_REG_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_PASSPORT = "AAAAAAAAAA";
    private static final String UPDATED_PASSPORT = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_PASSPORT_EXP_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_PASSPORT_EXP_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_KITAS = "AAAAAAAAAA";
    private static final String UPDATED_KITAS = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_KITAS_EXP_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_KITAS_EXP_DATE = LocalDate.now(ZoneId.systemDefault());

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
    private AccountAuthorizeRepository accountAuthorizeRepository;

    @Autowired
    private AccountAuthorizeMapper accountAuthorizeMapper;

    @Autowired
    private AccountAuthorizeService accountAuthorizeService;

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

    private MockMvc restAccountAuthorizeMockMvc;

    private AccountAuthorize accountAuthorize;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AccountAuthorizeResource accountAuthorizeResource = new AccountAuthorizeResource(accountAuthorizeService);
        this.restAccountAuthorizeMockMvc = MockMvcBuilders.standaloneSetup(accountAuthorizeResource)
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
    public static AccountAuthorize createEntity(EntityManager em) {
        AccountAuthorize accountAuthorize = new AccountAuthorize()
            .firstName(DEFAULT_FIRST_NAME)
            .middleName(DEFAULT_MIDDLE_NAME)
            .lastName(DEFAULT_LAST_NAME)
            .position(DEFAULT_POSITION)
            .ktp(DEFAULT_KTP)
            .ktpExpDate(DEFAULT_KTP_EXP_DATE)
            .npwp(DEFAULT_NPWP)
            .npwpRegDate(DEFAULT_NPWP_REG_DATE)
            .passport(DEFAULT_PASSPORT)
            .passportExpDate(DEFAULT_PASSPORT_EXP_DATE)
            .kitas(DEFAULT_KITAS)
            .kitasExpDate(DEFAULT_KITAS_EXP_DATE)
            .createSystemDate(DEFAULT_CREATE_SYSTEM_DATE)
            .createDate(DEFAULT_CREATE_DATE)
            .createUserId(DEFAULT_CREATE_USER_ID)
            .lastModificationSystemDate(DEFAULT_LAST_MODIFICATION_SYSTEM_DATE)
            .lastModificationDate(DEFAULT_LAST_MODIFICATION_DATE)
            .lastModificationUserId(DEFAULT_LAST_MODIFICATION_USER_ID);
        return accountAuthorize;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AccountAuthorize createUpdatedEntity(EntityManager em) {
        AccountAuthorize accountAuthorize = new AccountAuthorize()
            .firstName(UPDATED_FIRST_NAME)
            .middleName(UPDATED_MIDDLE_NAME)
            .lastName(UPDATED_LAST_NAME)
            .position(UPDATED_POSITION)
            .ktp(UPDATED_KTP)
            .ktpExpDate(UPDATED_KTP_EXP_DATE)
            .npwp(UPDATED_NPWP)
            .npwpRegDate(UPDATED_NPWP_REG_DATE)
            .passport(UPDATED_PASSPORT)
            .passportExpDate(UPDATED_PASSPORT_EXP_DATE)
            .kitas(UPDATED_KITAS)
            .kitasExpDate(UPDATED_KITAS_EXP_DATE)
            .createSystemDate(UPDATED_CREATE_SYSTEM_DATE)
            .createDate(UPDATED_CREATE_DATE)
            .createUserId(UPDATED_CREATE_USER_ID)
            .lastModificationSystemDate(UPDATED_LAST_MODIFICATION_SYSTEM_DATE)
            .lastModificationDate(UPDATED_LAST_MODIFICATION_DATE)
            .lastModificationUserId(UPDATED_LAST_MODIFICATION_USER_ID);
        return accountAuthorize;
    }

    @BeforeEach
    public void initTest() {
        accountAuthorize = createEntity(em);
    }

    @Test
    @Transactional
    public void createAccountAuthorize() throws Exception {
        int databaseSizeBeforeCreate = accountAuthorizeRepository.findAll().size();

        // Create the AccountAuthorize
        AccountAuthorizeDTO accountAuthorizeDTO = accountAuthorizeMapper.toDto(accountAuthorize);
        restAccountAuthorizeMockMvc.perform(post("/api/account-authorizes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(accountAuthorizeDTO)))
            .andExpect(status().isCreated());

        // Validate the AccountAuthorize in the database
        List<AccountAuthorize> accountAuthorizeList = accountAuthorizeRepository.findAll();
        assertThat(accountAuthorizeList).hasSize(databaseSizeBeforeCreate + 1);
        AccountAuthorize testAccountAuthorize = accountAuthorizeList.get(accountAuthorizeList.size() - 1);
        assertThat(testAccountAuthorize.getFirstName()).isEqualTo(DEFAULT_FIRST_NAME);
        assertThat(testAccountAuthorize.getMiddleName()).isEqualTo(DEFAULT_MIDDLE_NAME);
        assertThat(testAccountAuthorize.getLastName()).isEqualTo(DEFAULT_LAST_NAME);
        assertThat(testAccountAuthorize.getPosition()).isEqualTo(DEFAULT_POSITION);
        assertThat(testAccountAuthorize.getKtp()).isEqualTo(DEFAULT_KTP);
        assertThat(testAccountAuthorize.getKtpExpDate()).isEqualTo(DEFAULT_KTP_EXP_DATE);
        assertThat(testAccountAuthorize.getNpwp()).isEqualTo(DEFAULT_NPWP);
        assertThat(testAccountAuthorize.getNpwpRegDate()).isEqualTo(DEFAULT_NPWP_REG_DATE);
        assertThat(testAccountAuthorize.getPassport()).isEqualTo(DEFAULT_PASSPORT);
        assertThat(testAccountAuthorize.getPassportExpDate()).isEqualTo(DEFAULT_PASSPORT_EXP_DATE);
        assertThat(testAccountAuthorize.getKitas()).isEqualTo(DEFAULT_KITAS);
        assertThat(testAccountAuthorize.getKitasExpDate()).isEqualTo(DEFAULT_KITAS_EXP_DATE);
        assertThat(testAccountAuthorize.getCreateSystemDate()).isEqualTo(DEFAULT_CREATE_SYSTEM_DATE);
        assertThat(testAccountAuthorize.getCreateDate()).isEqualTo(DEFAULT_CREATE_DATE);
        assertThat(testAccountAuthorize.getCreateUserId()).isEqualTo(DEFAULT_CREATE_USER_ID);
        assertThat(testAccountAuthorize.getLastModificationSystemDate()).isEqualTo(DEFAULT_LAST_MODIFICATION_SYSTEM_DATE);
        assertThat(testAccountAuthorize.getLastModificationDate()).isEqualTo(DEFAULT_LAST_MODIFICATION_DATE);
        assertThat(testAccountAuthorize.getLastModificationUserId()).isEqualTo(DEFAULT_LAST_MODIFICATION_USER_ID);
    }

    @Test
    @Transactional
    public void createAccountAuthorizeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = accountAuthorizeRepository.findAll().size();

        // Create the AccountAuthorize with an existing ID
        accountAuthorize.setId(1L);
        AccountAuthorizeDTO accountAuthorizeDTO = accountAuthorizeMapper.toDto(accountAuthorize);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAccountAuthorizeMockMvc.perform(post("/api/account-authorizes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(accountAuthorizeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AccountAuthorize in the database
        List<AccountAuthorize> accountAuthorizeList = accountAuthorizeRepository.findAll();
        assertThat(accountAuthorizeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllAccountAuthorizes() throws Exception {
        // Initialize the database
        accountAuthorizeRepository.saveAndFlush(accountAuthorize);

        // Get all the accountAuthorizeList
        restAccountAuthorizeMockMvc.perform(get("/api/account-authorizes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(accountAuthorize.getId().intValue())))
            .andExpect(jsonPath("$.[*].firstName").value(hasItem(DEFAULT_FIRST_NAME)))
            .andExpect(jsonPath("$.[*].middleName").value(hasItem(DEFAULT_MIDDLE_NAME)))
            .andExpect(jsonPath("$.[*].lastName").value(hasItem(DEFAULT_LAST_NAME)))
            .andExpect(jsonPath("$.[*].position").value(hasItem(DEFAULT_POSITION)))
            .andExpect(jsonPath("$.[*].ktp").value(hasItem(DEFAULT_KTP)))
            .andExpect(jsonPath("$.[*].ktpExpDate").value(hasItem(DEFAULT_KTP_EXP_DATE.toString())))
            .andExpect(jsonPath("$.[*].npwp").value(hasItem(DEFAULT_NPWP)))
            .andExpect(jsonPath("$.[*].npwpRegDate").value(hasItem(DEFAULT_NPWP_REG_DATE.toString())))
            .andExpect(jsonPath("$.[*].passport").value(hasItem(DEFAULT_PASSPORT)))
            .andExpect(jsonPath("$.[*].passportExpDate").value(hasItem(DEFAULT_PASSPORT_EXP_DATE.toString())))
            .andExpect(jsonPath("$.[*].kitas").value(hasItem(DEFAULT_KITAS)))
            .andExpect(jsonPath("$.[*].kitasExpDate").value(hasItem(DEFAULT_KITAS_EXP_DATE.toString())))
            .andExpect(jsonPath("$.[*].createSystemDate").value(hasItem(DEFAULT_CREATE_SYSTEM_DATE.toString())))
            .andExpect(jsonPath("$.[*].createDate").value(hasItem(sameInstant(DEFAULT_CREATE_DATE))))
            .andExpect(jsonPath("$.[*].createUserId").value(hasItem(DEFAULT_CREATE_USER_ID.intValue())))
            .andExpect(jsonPath("$.[*].lastModificationSystemDate").value(hasItem(DEFAULT_LAST_MODIFICATION_SYSTEM_DATE.toString())))
            .andExpect(jsonPath("$.[*].lastModificationDate").value(hasItem(sameInstant(DEFAULT_LAST_MODIFICATION_DATE))))
            .andExpect(jsonPath("$.[*].lastModificationUserId").value(hasItem(DEFAULT_LAST_MODIFICATION_USER_ID.intValue())));
    }
    
    @Test
    @Transactional
    public void getAccountAuthorize() throws Exception {
        // Initialize the database
        accountAuthorizeRepository.saveAndFlush(accountAuthorize);

        // Get the accountAuthorize
        restAccountAuthorizeMockMvc.perform(get("/api/account-authorizes/{id}", accountAuthorize.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(accountAuthorize.getId().intValue()))
            .andExpect(jsonPath("$.firstName").value(DEFAULT_FIRST_NAME))
            .andExpect(jsonPath("$.middleName").value(DEFAULT_MIDDLE_NAME))
            .andExpect(jsonPath("$.lastName").value(DEFAULT_LAST_NAME))
            .andExpect(jsonPath("$.position").value(DEFAULT_POSITION))
            .andExpect(jsonPath("$.ktp").value(DEFAULT_KTP))
            .andExpect(jsonPath("$.ktpExpDate").value(DEFAULT_KTP_EXP_DATE.toString()))
            .andExpect(jsonPath("$.npwp").value(DEFAULT_NPWP))
            .andExpect(jsonPath("$.npwpRegDate").value(DEFAULT_NPWP_REG_DATE.toString()))
            .andExpect(jsonPath("$.passport").value(DEFAULT_PASSPORT))
            .andExpect(jsonPath("$.passportExpDate").value(DEFAULT_PASSPORT_EXP_DATE.toString()))
            .andExpect(jsonPath("$.kitas").value(DEFAULT_KITAS))
            .andExpect(jsonPath("$.kitasExpDate").value(DEFAULT_KITAS_EXP_DATE.toString()))
            .andExpect(jsonPath("$.createSystemDate").value(DEFAULT_CREATE_SYSTEM_DATE.toString()))
            .andExpect(jsonPath("$.createDate").value(sameInstant(DEFAULT_CREATE_DATE)))
            .andExpect(jsonPath("$.createUserId").value(DEFAULT_CREATE_USER_ID.intValue()))
            .andExpect(jsonPath("$.lastModificationSystemDate").value(DEFAULT_LAST_MODIFICATION_SYSTEM_DATE.toString()))
            .andExpect(jsonPath("$.lastModificationDate").value(sameInstant(DEFAULT_LAST_MODIFICATION_DATE)))
            .andExpect(jsonPath("$.lastModificationUserId").value(DEFAULT_LAST_MODIFICATION_USER_ID.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingAccountAuthorize() throws Exception {
        // Get the accountAuthorize
        restAccountAuthorizeMockMvc.perform(get("/api/account-authorizes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAccountAuthorize() throws Exception {
        // Initialize the database
        accountAuthorizeRepository.saveAndFlush(accountAuthorize);

        int databaseSizeBeforeUpdate = accountAuthorizeRepository.findAll().size();

        // Update the accountAuthorize
        AccountAuthorize updatedAccountAuthorize = accountAuthorizeRepository.findById(accountAuthorize.getId()).get();
        // Disconnect from session so that the updates on updatedAccountAuthorize are not directly saved in db
        em.detach(updatedAccountAuthorize);
        updatedAccountAuthorize
            .firstName(UPDATED_FIRST_NAME)
            .middleName(UPDATED_MIDDLE_NAME)
            .lastName(UPDATED_LAST_NAME)
            .position(UPDATED_POSITION)
            .ktp(UPDATED_KTP)
            .ktpExpDate(UPDATED_KTP_EXP_DATE)
            .npwp(UPDATED_NPWP)
            .npwpRegDate(UPDATED_NPWP_REG_DATE)
            .passport(UPDATED_PASSPORT)
            .passportExpDate(UPDATED_PASSPORT_EXP_DATE)
            .kitas(UPDATED_KITAS)
            .kitasExpDate(UPDATED_KITAS_EXP_DATE)
            .createSystemDate(UPDATED_CREATE_SYSTEM_DATE)
            .createDate(UPDATED_CREATE_DATE)
            .createUserId(UPDATED_CREATE_USER_ID)
            .lastModificationSystemDate(UPDATED_LAST_MODIFICATION_SYSTEM_DATE)
            .lastModificationDate(UPDATED_LAST_MODIFICATION_DATE)
            .lastModificationUserId(UPDATED_LAST_MODIFICATION_USER_ID);
        AccountAuthorizeDTO accountAuthorizeDTO = accountAuthorizeMapper.toDto(updatedAccountAuthorize);

        restAccountAuthorizeMockMvc.perform(put("/api/account-authorizes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(accountAuthorizeDTO)))
            .andExpect(status().isOk());

        // Validate the AccountAuthorize in the database
        List<AccountAuthorize> accountAuthorizeList = accountAuthorizeRepository.findAll();
        assertThat(accountAuthorizeList).hasSize(databaseSizeBeforeUpdate);
        AccountAuthorize testAccountAuthorize = accountAuthorizeList.get(accountAuthorizeList.size() - 1);
        assertThat(testAccountAuthorize.getFirstName()).isEqualTo(UPDATED_FIRST_NAME);
        assertThat(testAccountAuthorize.getMiddleName()).isEqualTo(UPDATED_MIDDLE_NAME);
        assertThat(testAccountAuthorize.getLastName()).isEqualTo(UPDATED_LAST_NAME);
        assertThat(testAccountAuthorize.getPosition()).isEqualTo(UPDATED_POSITION);
        assertThat(testAccountAuthorize.getKtp()).isEqualTo(UPDATED_KTP);
        assertThat(testAccountAuthorize.getKtpExpDate()).isEqualTo(UPDATED_KTP_EXP_DATE);
        assertThat(testAccountAuthorize.getNpwp()).isEqualTo(UPDATED_NPWP);
        assertThat(testAccountAuthorize.getNpwpRegDate()).isEqualTo(UPDATED_NPWP_REG_DATE);
        assertThat(testAccountAuthorize.getPassport()).isEqualTo(UPDATED_PASSPORT);
        assertThat(testAccountAuthorize.getPassportExpDate()).isEqualTo(UPDATED_PASSPORT_EXP_DATE);
        assertThat(testAccountAuthorize.getKitas()).isEqualTo(UPDATED_KITAS);
        assertThat(testAccountAuthorize.getKitasExpDate()).isEqualTo(UPDATED_KITAS_EXP_DATE);
        assertThat(testAccountAuthorize.getCreateSystemDate()).isEqualTo(UPDATED_CREATE_SYSTEM_DATE);
        assertThat(testAccountAuthorize.getCreateDate()).isEqualTo(UPDATED_CREATE_DATE);
        assertThat(testAccountAuthorize.getCreateUserId()).isEqualTo(UPDATED_CREATE_USER_ID);
        assertThat(testAccountAuthorize.getLastModificationSystemDate()).isEqualTo(UPDATED_LAST_MODIFICATION_SYSTEM_DATE);
        assertThat(testAccountAuthorize.getLastModificationDate()).isEqualTo(UPDATED_LAST_MODIFICATION_DATE);
        assertThat(testAccountAuthorize.getLastModificationUserId()).isEqualTo(UPDATED_LAST_MODIFICATION_USER_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingAccountAuthorize() throws Exception {
        int databaseSizeBeforeUpdate = accountAuthorizeRepository.findAll().size();

        // Create the AccountAuthorize
        AccountAuthorizeDTO accountAuthorizeDTO = accountAuthorizeMapper.toDto(accountAuthorize);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAccountAuthorizeMockMvc.perform(put("/api/account-authorizes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(accountAuthorizeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AccountAuthorize in the database
        List<AccountAuthorize> accountAuthorizeList = accountAuthorizeRepository.findAll();
        assertThat(accountAuthorizeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAccountAuthorize() throws Exception {
        // Initialize the database
        accountAuthorizeRepository.saveAndFlush(accountAuthorize);

        int databaseSizeBeforeDelete = accountAuthorizeRepository.findAll().size();

        // Delete the accountAuthorize
        restAccountAuthorizeMockMvc.perform(delete("/api/account-authorizes/{id}", accountAuthorize.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AccountAuthorize> accountAuthorizeList = accountAuthorizeRepository.findAll();
        assertThat(accountAuthorizeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AccountAuthorize.class);
        AccountAuthorize accountAuthorize1 = new AccountAuthorize();
        accountAuthorize1.setId(1L);
        AccountAuthorize accountAuthorize2 = new AccountAuthorize();
        accountAuthorize2.setId(accountAuthorize1.getId());
        assertThat(accountAuthorize1).isEqualTo(accountAuthorize2);
        accountAuthorize2.setId(2L);
        assertThat(accountAuthorize1).isNotEqualTo(accountAuthorize2);
        accountAuthorize1.setId(null);
        assertThat(accountAuthorize1).isNotEqualTo(accountAuthorize2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AccountAuthorizeDTO.class);
        AccountAuthorizeDTO accountAuthorizeDTO1 = new AccountAuthorizeDTO();
        accountAuthorizeDTO1.setId(1L);
        AccountAuthorizeDTO accountAuthorizeDTO2 = new AccountAuthorizeDTO();
        assertThat(accountAuthorizeDTO1).isNotEqualTo(accountAuthorizeDTO2);
        accountAuthorizeDTO2.setId(accountAuthorizeDTO1.getId());
        assertThat(accountAuthorizeDTO1).isEqualTo(accountAuthorizeDTO2);
        accountAuthorizeDTO2.setId(2L);
        assertThat(accountAuthorizeDTO1).isNotEqualTo(accountAuthorizeDTO2);
        accountAuthorizeDTO1.setId(null);
        assertThat(accountAuthorizeDTO1).isNotEqualTo(accountAuthorizeDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(accountAuthorizeMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(accountAuthorizeMapper.fromId(null)).isNull();
    }
}
