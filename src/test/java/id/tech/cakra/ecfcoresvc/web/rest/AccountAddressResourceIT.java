package id.tech.cakra.ecfcoresvc.web.rest;

import id.tech.cakra.ecfcoresvc.EcfcoresvcApp;
import id.tech.cakra.ecfcoresvc.domain.AccountAddress;
import id.tech.cakra.ecfcoresvc.repository.AccountAddressRepository;
import id.tech.cakra.ecfcoresvc.service.AccountAddressService;
import id.tech.cakra.ecfcoresvc.service.dto.AccountAddressDTO;
import id.tech.cakra.ecfcoresvc.service.mapper.AccountAddressMapper;
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
 * Integration tests for the {@link AccountAddressResource} REST controller.
 */
@SpringBootTest(classes = EcfcoresvcApp.class)
public class AccountAddressResourceIT {

    private static final String DEFAULT_ADDRESS_TYPE = "A";
    private static final String UPDATED_ADDRESS_TYPE = "B";

    private static final String DEFAULT_ADDRESS_1 = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS_1 = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS_2 = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS_2 = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS_3 = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS_3 = "BBBBBBBBBB";

    private static final String DEFAULT_POSTAL_CODE = "AAAAA";
    private static final String UPDATED_POSTAL_CODE = "BBBBB";

    private static final String DEFAULT_PHONE = "AAAAAAAAAA";
    private static final String UPDATED_PHONE = "BBBBBBBBBB";

    private static final String DEFAULT_MOBILE_PHONE = "AAAAAAAAAA";
    private static final String UPDATED_MOBILE_PHONE = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_FAX = "AAAAAAAAAA";
    private static final String UPDATED_FAX = "BBBBBBBBBB";

    private static final Long DEFAULT_CITY_ID = 1L;
    private static final Long UPDATED_CITY_ID = 2L;

    private static final Long DEFAULT_COUNTRY_ID = 1L;
    private static final Long UPDATED_COUNTRY_ID = 2L;

    private static final Long DEFAULT_PROVINCE_ID = 1L;
    private static final Long UPDATED_PROVINCE_ID = 2L;

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
    private AccountAddressRepository accountAddressRepository;

    @Autowired
    private AccountAddressMapper accountAddressMapper;

    @Autowired
    private AccountAddressService accountAddressService;

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

    private MockMvc restAccountAddressMockMvc;

    private AccountAddress accountAddress;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AccountAddressResource accountAddressResource = new AccountAddressResource(accountAddressService);
        this.restAccountAddressMockMvc = MockMvcBuilders.standaloneSetup(accountAddressResource)
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
    public static AccountAddress createEntity(EntityManager em) {
        AccountAddress accountAddress = new AccountAddress()
            .addressType(DEFAULT_ADDRESS_TYPE)
            .address1(DEFAULT_ADDRESS_1)
            .address2(DEFAULT_ADDRESS_2)
            .address3(DEFAULT_ADDRESS_3)
            .postalCode(DEFAULT_POSTAL_CODE)
            .phone(DEFAULT_PHONE)
            .mobilePhone(DEFAULT_MOBILE_PHONE)
            .email(DEFAULT_EMAIL)
            .fax(DEFAULT_FAX)
            .cityId(DEFAULT_CITY_ID)
            .countryId(DEFAULT_COUNTRY_ID)
            .provinceId(DEFAULT_PROVINCE_ID)
            .createSystemDate(DEFAULT_CREATE_SYSTEM_DATE)
            .createDate(DEFAULT_CREATE_DATE)
            .createUserId(DEFAULT_CREATE_USER_ID)
            .lastModificationSystemDate(DEFAULT_LAST_MODIFICATION_SYSTEM_DATE)
            .lastModificationDate(DEFAULT_LAST_MODIFICATION_DATE)
            .lastModificationUserId(DEFAULT_LAST_MODIFICATION_USER_ID);
        return accountAddress;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AccountAddress createUpdatedEntity(EntityManager em) {
        AccountAddress accountAddress = new AccountAddress()
            .addressType(UPDATED_ADDRESS_TYPE)
            .address1(UPDATED_ADDRESS_1)
            .address2(UPDATED_ADDRESS_2)
            .address3(UPDATED_ADDRESS_3)
            .postalCode(UPDATED_POSTAL_CODE)
            .phone(UPDATED_PHONE)
            .mobilePhone(UPDATED_MOBILE_PHONE)
            .email(UPDATED_EMAIL)
            .fax(UPDATED_FAX)
            .cityId(UPDATED_CITY_ID)
            .countryId(UPDATED_COUNTRY_ID)
            .provinceId(UPDATED_PROVINCE_ID)
            .createSystemDate(UPDATED_CREATE_SYSTEM_DATE)
            .createDate(UPDATED_CREATE_DATE)
            .createUserId(UPDATED_CREATE_USER_ID)
            .lastModificationSystemDate(UPDATED_LAST_MODIFICATION_SYSTEM_DATE)
            .lastModificationDate(UPDATED_LAST_MODIFICATION_DATE)
            .lastModificationUserId(UPDATED_LAST_MODIFICATION_USER_ID);
        return accountAddress;
    }

    @BeforeEach
    public void initTest() {
        accountAddress = createEntity(em);
    }

    @Test
    @Transactional
    public void createAccountAddress() throws Exception {
        int databaseSizeBeforeCreate = accountAddressRepository.findAll().size();

        // Create the AccountAddress
        AccountAddressDTO accountAddressDTO = accountAddressMapper.toDto(accountAddress);
        restAccountAddressMockMvc.perform(post("/api/account-addresses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(accountAddressDTO)))
            .andExpect(status().isCreated());

        // Validate the AccountAddress in the database
        List<AccountAddress> accountAddressList = accountAddressRepository.findAll();
        assertThat(accountAddressList).hasSize(databaseSizeBeforeCreate + 1);
        AccountAddress testAccountAddress = accountAddressList.get(accountAddressList.size() - 1);
        assertThat(testAccountAddress.getAddressType()).isEqualTo(DEFAULT_ADDRESS_TYPE);
        assertThat(testAccountAddress.getAddress1()).isEqualTo(DEFAULT_ADDRESS_1);
        assertThat(testAccountAddress.getAddress2()).isEqualTo(DEFAULT_ADDRESS_2);
        assertThat(testAccountAddress.getAddress3()).isEqualTo(DEFAULT_ADDRESS_3);
        assertThat(testAccountAddress.getPostalCode()).isEqualTo(DEFAULT_POSTAL_CODE);
        assertThat(testAccountAddress.getPhone()).isEqualTo(DEFAULT_PHONE);
        assertThat(testAccountAddress.getMobilePhone()).isEqualTo(DEFAULT_MOBILE_PHONE);
        assertThat(testAccountAddress.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testAccountAddress.getFax()).isEqualTo(DEFAULT_FAX);
        assertThat(testAccountAddress.getCityId()).isEqualTo(DEFAULT_CITY_ID);
        assertThat(testAccountAddress.getCountryId()).isEqualTo(DEFAULT_COUNTRY_ID);
        assertThat(testAccountAddress.getProvinceId()).isEqualTo(DEFAULT_PROVINCE_ID);
        assertThat(testAccountAddress.getCreateSystemDate()).isEqualTo(DEFAULT_CREATE_SYSTEM_DATE);
        assertThat(testAccountAddress.getCreateDate()).isEqualTo(DEFAULT_CREATE_DATE);
        assertThat(testAccountAddress.getCreateUserId()).isEqualTo(DEFAULT_CREATE_USER_ID);
        assertThat(testAccountAddress.getLastModificationSystemDate()).isEqualTo(DEFAULT_LAST_MODIFICATION_SYSTEM_DATE);
        assertThat(testAccountAddress.getLastModificationDate()).isEqualTo(DEFAULT_LAST_MODIFICATION_DATE);
        assertThat(testAccountAddress.getLastModificationUserId()).isEqualTo(DEFAULT_LAST_MODIFICATION_USER_ID);
    }

    @Test
    @Transactional
    public void createAccountAddressWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = accountAddressRepository.findAll().size();

        // Create the AccountAddress with an existing ID
        accountAddress.setId(1L);
        AccountAddressDTO accountAddressDTO = accountAddressMapper.toDto(accountAddress);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAccountAddressMockMvc.perform(post("/api/account-addresses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(accountAddressDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AccountAddress in the database
        List<AccountAddress> accountAddressList = accountAddressRepository.findAll();
        assertThat(accountAddressList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllAccountAddresses() throws Exception {
        // Initialize the database
        accountAddressRepository.saveAndFlush(accountAddress);

        // Get all the accountAddressList
        restAccountAddressMockMvc.perform(get("/api/account-addresses?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(accountAddress.getId().intValue())))
            .andExpect(jsonPath("$.[*].addressType").value(hasItem(DEFAULT_ADDRESS_TYPE)))
            .andExpect(jsonPath("$.[*].address1").value(hasItem(DEFAULT_ADDRESS_1)))
            .andExpect(jsonPath("$.[*].address2").value(hasItem(DEFAULT_ADDRESS_2)))
            .andExpect(jsonPath("$.[*].address3").value(hasItem(DEFAULT_ADDRESS_3)))
            .andExpect(jsonPath("$.[*].postalCode").value(hasItem(DEFAULT_POSTAL_CODE)))
            .andExpect(jsonPath("$.[*].phone").value(hasItem(DEFAULT_PHONE)))
            .andExpect(jsonPath("$.[*].mobilePhone").value(hasItem(DEFAULT_MOBILE_PHONE)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].fax").value(hasItem(DEFAULT_FAX)))
            .andExpect(jsonPath("$.[*].cityId").value(hasItem(DEFAULT_CITY_ID.intValue())))
            .andExpect(jsonPath("$.[*].countryId").value(hasItem(DEFAULT_COUNTRY_ID.intValue())))
            .andExpect(jsonPath("$.[*].provinceId").value(hasItem(DEFAULT_PROVINCE_ID.intValue())))
            .andExpect(jsonPath("$.[*].createSystemDate").value(hasItem(DEFAULT_CREATE_SYSTEM_DATE.toString())))
            .andExpect(jsonPath("$.[*].createDate").value(hasItem(sameInstant(DEFAULT_CREATE_DATE))))
            .andExpect(jsonPath("$.[*].createUserId").value(hasItem(DEFAULT_CREATE_USER_ID.intValue())))
            .andExpect(jsonPath("$.[*].lastModificationSystemDate").value(hasItem(DEFAULT_LAST_MODIFICATION_SYSTEM_DATE.toString())))
            .andExpect(jsonPath("$.[*].lastModificationDate").value(hasItem(sameInstant(DEFAULT_LAST_MODIFICATION_DATE))))
            .andExpect(jsonPath("$.[*].lastModificationUserId").value(hasItem(DEFAULT_LAST_MODIFICATION_USER_ID.intValue())));
    }
    
    @Test
    @Transactional
    public void getAccountAddress() throws Exception {
        // Initialize the database
        accountAddressRepository.saveAndFlush(accountAddress);

        // Get the accountAddress
        restAccountAddressMockMvc.perform(get("/api/account-addresses/{id}", accountAddress.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(accountAddress.getId().intValue()))
            .andExpect(jsonPath("$.addressType").value(DEFAULT_ADDRESS_TYPE))
            .andExpect(jsonPath("$.address1").value(DEFAULT_ADDRESS_1))
            .andExpect(jsonPath("$.address2").value(DEFAULT_ADDRESS_2))
            .andExpect(jsonPath("$.address3").value(DEFAULT_ADDRESS_3))
            .andExpect(jsonPath("$.postalCode").value(DEFAULT_POSTAL_CODE))
            .andExpect(jsonPath("$.phone").value(DEFAULT_PHONE))
            .andExpect(jsonPath("$.mobilePhone").value(DEFAULT_MOBILE_PHONE))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
            .andExpect(jsonPath("$.fax").value(DEFAULT_FAX))
            .andExpect(jsonPath("$.cityId").value(DEFAULT_CITY_ID.intValue()))
            .andExpect(jsonPath("$.countryId").value(DEFAULT_COUNTRY_ID.intValue()))
            .andExpect(jsonPath("$.provinceId").value(DEFAULT_PROVINCE_ID.intValue()))
            .andExpect(jsonPath("$.createSystemDate").value(DEFAULT_CREATE_SYSTEM_DATE.toString()))
            .andExpect(jsonPath("$.createDate").value(sameInstant(DEFAULT_CREATE_DATE)))
            .andExpect(jsonPath("$.createUserId").value(DEFAULT_CREATE_USER_ID.intValue()))
            .andExpect(jsonPath("$.lastModificationSystemDate").value(DEFAULT_LAST_MODIFICATION_SYSTEM_DATE.toString()))
            .andExpect(jsonPath("$.lastModificationDate").value(sameInstant(DEFAULT_LAST_MODIFICATION_DATE)))
            .andExpect(jsonPath("$.lastModificationUserId").value(DEFAULT_LAST_MODIFICATION_USER_ID.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingAccountAddress() throws Exception {
        // Get the accountAddress
        restAccountAddressMockMvc.perform(get("/api/account-addresses/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAccountAddress() throws Exception {
        // Initialize the database
        accountAddressRepository.saveAndFlush(accountAddress);

        int databaseSizeBeforeUpdate = accountAddressRepository.findAll().size();

        // Update the accountAddress
        AccountAddress updatedAccountAddress = accountAddressRepository.findById(accountAddress.getId()).get();
        // Disconnect from session so that the updates on updatedAccountAddress are not directly saved in db
        em.detach(updatedAccountAddress);
        updatedAccountAddress
            .addressType(UPDATED_ADDRESS_TYPE)
            .address1(UPDATED_ADDRESS_1)
            .address2(UPDATED_ADDRESS_2)
            .address3(UPDATED_ADDRESS_3)
            .postalCode(UPDATED_POSTAL_CODE)
            .phone(UPDATED_PHONE)
            .mobilePhone(UPDATED_MOBILE_PHONE)
            .email(UPDATED_EMAIL)
            .fax(UPDATED_FAX)
            .cityId(UPDATED_CITY_ID)
            .countryId(UPDATED_COUNTRY_ID)
            .provinceId(UPDATED_PROVINCE_ID)
            .createSystemDate(UPDATED_CREATE_SYSTEM_DATE)
            .createDate(UPDATED_CREATE_DATE)
            .createUserId(UPDATED_CREATE_USER_ID)
            .lastModificationSystemDate(UPDATED_LAST_MODIFICATION_SYSTEM_DATE)
            .lastModificationDate(UPDATED_LAST_MODIFICATION_DATE)
            .lastModificationUserId(UPDATED_LAST_MODIFICATION_USER_ID);
        AccountAddressDTO accountAddressDTO = accountAddressMapper.toDto(updatedAccountAddress);

        restAccountAddressMockMvc.perform(put("/api/account-addresses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(accountAddressDTO)))
            .andExpect(status().isOk());

        // Validate the AccountAddress in the database
        List<AccountAddress> accountAddressList = accountAddressRepository.findAll();
        assertThat(accountAddressList).hasSize(databaseSizeBeforeUpdate);
        AccountAddress testAccountAddress = accountAddressList.get(accountAddressList.size() - 1);
        assertThat(testAccountAddress.getAddressType()).isEqualTo(UPDATED_ADDRESS_TYPE);
        assertThat(testAccountAddress.getAddress1()).isEqualTo(UPDATED_ADDRESS_1);
        assertThat(testAccountAddress.getAddress2()).isEqualTo(UPDATED_ADDRESS_2);
        assertThat(testAccountAddress.getAddress3()).isEqualTo(UPDATED_ADDRESS_3);
        assertThat(testAccountAddress.getPostalCode()).isEqualTo(UPDATED_POSTAL_CODE);
        assertThat(testAccountAddress.getPhone()).isEqualTo(UPDATED_PHONE);
        assertThat(testAccountAddress.getMobilePhone()).isEqualTo(UPDATED_MOBILE_PHONE);
        assertThat(testAccountAddress.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testAccountAddress.getFax()).isEqualTo(UPDATED_FAX);
        assertThat(testAccountAddress.getCityId()).isEqualTo(UPDATED_CITY_ID);
        assertThat(testAccountAddress.getCountryId()).isEqualTo(UPDATED_COUNTRY_ID);
        assertThat(testAccountAddress.getProvinceId()).isEqualTo(UPDATED_PROVINCE_ID);
        assertThat(testAccountAddress.getCreateSystemDate()).isEqualTo(UPDATED_CREATE_SYSTEM_DATE);
        assertThat(testAccountAddress.getCreateDate()).isEqualTo(UPDATED_CREATE_DATE);
        assertThat(testAccountAddress.getCreateUserId()).isEqualTo(UPDATED_CREATE_USER_ID);
        assertThat(testAccountAddress.getLastModificationSystemDate()).isEqualTo(UPDATED_LAST_MODIFICATION_SYSTEM_DATE);
        assertThat(testAccountAddress.getLastModificationDate()).isEqualTo(UPDATED_LAST_MODIFICATION_DATE);
        assertThat(testAccountAddress.getLastModificationUserId()).isEqualTo(UPDATED_LAST_MODIFICATION_USER_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingAccountAddress() throws Exception {
        int databaseSizeBeforeUpdate = accountAddressRepository.findAll().size();

        // Create the AccountAddress
        AccountAddressDTO accountAddressDTO = accountAddressMapper.toDto(accountAddress);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAccountAddressMockMvc.perform(put("/api/account-addresses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(accountAddressDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AccountAddress in the database
        List<AccountAddress> accountAddressList = accountAddressRepository.findAll();
        assertThat(accountAddressList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAccountAddress() throws Exception {
        // Initialize the database
        accountAddressRepository.saveAndFlush(accountAddress);

        int databaseSizeBeforeDelete = accountAddressRepository.findAll().size();

        // Delete the accountAddress
        restAccountAddressMockMvc.perform(delete("/api/account-addresses/{id}", accountAddress.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AccountAddress> accountAddressList = accountAddressRepository.findAll();
        assertThat(accountAddressList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AccountAddress.class);
        AccountAddress accountAddress1 = new AccountAddress();
        accountAddress1.setId(1L);
        AccountAddress accountAddress2 = new AccountAddress();
        accountAddress2.setId(accountAddress1.getId());
        assertThat(accountAddress1).isEqualTo(accountAddress2);
        accountAddress2.setId(2L);
        assertThat(accountAddress1).isNotEqualTo(accountAddress2);
        accountAddress1.setId(null);
        assertThat(accountAddress1).isNotEqualTo(accountAddress2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AccountAddressDTO.class);
        AccountAddressDTO accountAddressDTO1 = new AccountAddressDTO();
        accountAddressDTO1.setId(1L);
        AccountAddressDTO accountAddressDTO2 = new AccountAddressDTO();
        assertThat(accountAddressDTO1).isNotEqualTo(accountAddressDTO2);
        accountAddressDTO2.setId(accountAddressDTO1.getId());
        assertThat(accountAddressDTO1).isEqualTo(accountAddressDTO2);
        accountAddressDTO2.setId(2L);
        assertThat(accountAddressDTO1).isNotEqualTo(accountAddressDTO2);
        accountAddressDTO1.setId(null);
        assertThat(accountAddressDTO1).isNotEqualTo(accountAddressDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(accountAddressMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(accountAddressMapper.fromId(null)).isNull();
    }
}
