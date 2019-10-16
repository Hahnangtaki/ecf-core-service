package id.tech.cakra.ecfcoresvc.web.rest;

import id.tech.cakra.ecfcoresvc.EcfcoresvcApp;
import id.tech.cakra.ecfcoresvc.domain.AccountInstitution;
import id.tech.cakra.ecfcoresvc.repository.AccountInstitutionRepository;
import id.tech.cakra.ecfcoresvc.service.AccountInstitutionService;
import id.tech.cakra.ecfcoresvc.service.dto.AccountInstitutionDTO;
import id.tech.cakra.ecfcoresvc.service.mapper.AccountInstitutionMapper;
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
 * Integration tests for the {@link AccountInstitutionResource} REST controller.
 */
@SpringBootTest(classes = EcfcoresvcApp.class)
public class AccountInstitutionResourceIT {

    private static final String DEFAULT_SID = "AAAAAAAAAA";
    private static final String UPDATED_SID = "BBBBBBBBBB";

    private static final String DEFAULT_COMPANY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_COMPANY_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_BIC_CODE = "AAAAAAAAAA";
    private static final String UPDATED_BIC_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_NPWP = "AAAAAAAAAA";
    private static final String UPDATED_NPWP = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_NPWP_REG_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_NPWP_REG_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_SKD = "AAAAAAAAAA";
    private static final String UPDATED_SKD = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_SKD_EXP_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_SKD_EXP_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_COMPANY_ESTABLISH_PLACE = "AAAAAAAAAA";
    private static final String UPDATED_COMPANY_ESTABLISH_PLACE = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_COMPANY_ESTABLISH_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_COMPANY_ESTABLISH_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_BUSINESS_TYPE = "A";
    private static final String UPDATED_BUSINESS_TYPE = "B";

    private static final String DEFAULT_COMPANY_CHARACTERISTIC = "A";
    private static final String UPDATED_COMPANY_CHARACTERISTIC = "B";

    private static final String DEFAULT_FUND_SOURCE = "AAAAAAAAAA";
    private static final String UPDATED_FUND_SOURCE = "BBBBBBBBBB";

    private static final String DEFAULT_FUND_SOURCE_TEXT = "AAAAAAAAAA";
    private static final String UPDATED_FUND_SOURCE_TEXT = "BBBBBBBBBB";

    private static final String DEFAULT_ARTICLE_ASSOCIATION = "AAAAAAAAAA";
    private static final String UPDATED_ARTICLE_ASSOCIATION = "BBBBBBBBBB";

    private static final String DEFAULT_BUSINESS_REG_NO = "AAAAAAAAAA";
    private static final String UPDATED_BUSINESS_REG_NO = "BBBBBBBBBB";

    private static final Double DEFAULT_FINANCIAL_ASSET_1 = 1D;
    private static final Double UPDATED_FINANCIAL_ASSET_1 = 2D;

    private static final Double DEFAULT_FINANCIAL_ASSET_2 = 1D;
    private static final Double UPDATED_FINANCIAL_ASSET_2 = 2D;

    private static final Double DEFAULT_FINANCIAL_ASSET_3 = 1D;
    private static final Double UPDATED_FINANCIAL_ASSET_3 = 2D;

    private static final Double DEFAULT_OPERATING_PROFIT_1 = 1D;
    private static final Double UPDATED_OPERATING_PROFIT_1 = 2D;

    private static final Double DEFAULT_OPERATING_PROFIT_2 = 1D;
    private static final Double UPDATED_OPERATING_PROFIT_2 = 2D;

    private static final Double DEFAULT_OPERATING_PROFIT_3 = 1D;
    private static final Double UPDATED_OPERATING_PROFIT_3 = 2D;

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_INVESTMENT_OBJECTIVE = "AAAAAAAAAA";
    private static final String UPDATED_INVESTMENT_OBJECTIVE = "BBBBBBBBBB";

    private static final String DEFAULT_DIRECT_SID = "AAAAAAAAAA";
    private static final String UPDATED_DIRECT_SID = "BBBBBBBBBB";

    private static final String DEFAULT_ASSET_OWNER = "A";
    private static final String UPDATED_ASSET_OWNER = "B";

    private static final String DEFAULT_SUP_DOC_TYPE = "A";
    private static final String UPDATED_SUP_DOC_TYPE = "B";

    private static final LocalDate DEFAULT_SUP_DOC_EXP_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_SUP_DOC_EXP_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final Long DEFAULT_TAX_ID = 1L;
    private static final Long UPDATED_TAX_ID = 2L;

    private static final Long DEFAULT_LEGAL_DOMICILE_ID = 1L;
    private static final Long UPDATED_LEGAL_DOMICILE_ID = 2L;

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
    private AccountInstitutionRepository accountInstitutionRepository;

    @Autowired
    private AccountInstitutionMapper accountInstitutionMapper;

    @Autowired
    private AccountInstitutionService accountInstitutionService;

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

    private MockMvc restAccountInstitutionMockMvc;

    private AccountInstitution accountInstitution;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AccountInstitutionResource accountInstitutionResource = new AccountInstitutionResource(accountInstitutionService);
        this.restAccountInstitutionMockMvc = MockMvcBuilders.standaloneSetup(accountInstitutionResource)
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
    public static AccountInstitution createEntity(EntityManager em) {
        AccountInstitution accountInstitution = new AccountInstitution()
            .sid(DEFAULT_SID)
            .companyName(DEFAULT_COMPANY_NAME)
            .bicCode(DEFAULT_BIC_CODE)
            .npwp(DEFAULT_NPWP)
            .npwpRegDate(DEFAULT_NPWP_REG_DATE)
            .skd(DEFAULT_SKD)
            .skdExpDate(DEFAULT_SKD_EXP_DATE)
            .companyEstablishPlace(DEFAULT_COMPANY_ESTABLISH_PLACE)
            .companyEstablishDate(DEFAULT_COMPANY_ESTABLISH_DATE)
            .businessType(DEFAULT_BUSINESS_TYPE)
            .companyCharacteristic(DEFAULT_COMPANY_CHARACTERISTIC)
            .fundSource(DEFAULT_FUND_SOURCE)
            .fundSourceText(DEFAULT_FUND_SOURCE_TEXT)
            .articleAssociation(DEFAULT_ARTICLE_ASSOCIATION)
            .businessRegNo(DEFAULT_BUSINESS_REG_NO)
            .financialAsset1(DEFAULT_FINANCIAL_ASSET_1)
            .financialAsset2(DEFAULT_FINANCIAL_ASSET_2)
            .financialAsset3(DEFAULT_FINANCIAL_ASSET_3)
            .operatingProfit1(DEFAULT_OPERATING_PROFIT_1)
            .operatingProfit2(DEFAULT_OPERATING_PROFIT_2)
            .operatingProfit3(DEFAULT_OPERATING_PROFIT_3)
            .description(DEFAULT_DESCRIPTION)
            .investmentObjective(DEFAULT_INVESTMENT_OBJECTIVE)
            .directSID(DEFAULT_DIRECT_SID)
            .assetOwner(DEFAULT_ASSET_OWNER)
            .supDocType(DEFAULT_SUP_DOC_TYPE)
            .supDocExpDate(DEFAULT_SUP_DOC_EXP_DATE)
            .taxId(DEFAULT_TAX_ID)
            .legalDomicileId(DEFAULT_LEGAL_DOMICILE_ID)
            .createSystemDate(DEFAULT_CREATE_SYSTEM_DATE)
            .createDate(DEFAULT_CREATE_DATE)
            .createUserId(DEFAULT_CREATE_USER_ID)
            .lastModificationSystemDate(DEFAULT_LAST_MODIFICATION_SYSTEM_DATE)
            .lastModificationDate(DEFAULT_LAST_MODIFICATION_DATE)
            .lastModificationUserId(DEFAULT_LAST_MODIFICATION_USER_ID);
        return accountInstitution;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AccountInstitution createUpdatedEntity(EntityManager em) {
        AccountInstitution accountInstitution = new AccountInstitution()
            .sid(UPDATED_SID)
            .companyName(UPDATED_COMPANY_NAME)
            .bicCode(UPDATED_BIC_CODE)
            .npwp(UPDATED_NPWP)
            .npwpRegDate(UPDATED_NPWP_REG_DATE)
            .skd(UPDATED_SKD)
            .skdExpDate(UPDATED_SKD_EXP_DATE)
            .companyEstablishPlace(UPDATED_COMPANY_ESTABLISH_PLACE)
            .companyEstablishDate(UPDATED_COMPANY_ESTABLISH_DATE)
            .businessType(UPDATED_BUSINESS_TYPE)
            .companyCharacteristic(UPDATED_COMPANY_CHARACTERISTIC)
            .fundSource(UPDATED_FUND_SOURCE)
            .fundSourceText(UPDATED_FUND_SOURCE_TEXT)
            .articleAssociation(UPDATED_ARTICLE_ASSOCIATION)
            .businessRegNo(UPDATED_BUSINESS_REG_NO)
            .financialAsset1(UPDATED_FINANCIAL_ASSET_1)
            .financialAsset2(UPDATED_FINANCIAL_ASSET_2)
            .financialAsset3(UPDATED_FINANCIAL_ASSET_3)
            .operatingProfit1(UPDATED_OPERATING_PROFIT_1)
            .operatingProfit2(UPDATED_OPERATING_PROFIT_2)
            .operatingProfit3(UPDATED_OPERATING_PROFIT_3)
            .description(UPDATED_DESCRIPTION)
            .investmentObjective(UPDATED_INVESTMENT_OBJECTIVE)
            .directSID(UPDATED_DIRECT_SID)
            .assetOwner(UPDATED_ASSET_OWNER)
            .supDocType(UPDATED_SUP_DOC_TYPE)
            .supDocExpDate(UPDATED_SUP_DOC_EXP_DATE)
            .taxId(UPDATED_TAX_ID)
            .legalDomicileId(UPDATED_LEGAL_DOMICILE_ID)
            .createSystemDate(UPDATED_CREATE_SYSTEM_DATE)
            .createDate(UPDATED_CREATE_DATE)
            .createUserId(UPDATED_CREATE_USER_ID)
            .lastModificationSystemDate(UPDATED_LAST_MODIFICATION_SYSTEM_DATE)
            .lastModificationDate(UPDATED_LAST_MODIFICATION_DATE)
            .lastModificationUserId(UPDATED_LAST_MODIFICATION_USER_ID);
        return accountInstitution;
    }

    @BeforeEach
    public void initTest() {
        accountInstitution = createEntity(em);
    }

    @Test
    @Transactional
    public void createAccountInstitution() throws Exception {
        int databaseSizeBeforeCreate = accountInstitutionRepository.findAll().size();

        // Create the AccountInstitution
        AccountInstitutionDTO accountInstitutionDTO = accountInstitutionMapper.toDto(accountInstitution);
        restAccountInstitutionMockMvc.perform(post("/api/account-institutions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(accountInstitutionDTO)))
            .andExpect(status().isCreated());

        // Validate the AccountInstitution in the database
        List<AccountInstitution> accountInstitutionList = accountInstitutionRepository.findAll();
        assertThat(accountInstitutionList).hasSize(databaseSizeBeforeCreate + 1);
        AccountInstitution testAccountInstitution = accountInstitutionList.get(accountInstitutionList.size() - 1);
        assertThat(testAccountInstitution.getSid()).isEqualTo(DEFAULT_SID);
        assertThat(testAccountInstitution.getCompanyName()).isEqualTo(DEFAULT_COMPANY_NAME);
        assertThat(testAccountInstitution.getBicCode()).isEqualTo(DEFAULT_BIC_CODE);
        assertThat(testAccountInstitution.getNpwp()).isEqualTo(DEFAULT_NPWP);
        assertThat(testAccountInstitution.getNpwpRegDate()).isEqualTo(DEFAULT_NPWP_REG_DATE);
        assertThat(testAccountInstitution.getSkd()).isEqualTo(DEFAULT_SKD);
        assertThat(testAccountInstitution.getSkdExpDate()).isEqualTo(DEFAULT_SKD_EXP_DATE);
        assertThat(testAccountInstitution.getCompanyEstablishPlace()).isEqualTo(DEFAULT_COMPANY_ESTABLISH_PLACE);
        assertThat(testAccountInstitution.getCompanyEstablishDate()).isEqualTo(DEFAULT_COMPANY_ESTABLISH_DATE);
        assertThat(testAccountInstitution.getBusinessType()).isEqualTo(DEFAULT_BUSINESS_TYPE);
        assertThat(testAccountInstitution.getCompanyCharacteristic()).isEqualTo(DEFAULT_COMPANY_CHARACTERISTIC);
        assertThat(testAccountInstitution.getFundSource()).isEqualTo(DEFAULT_FUND_SOURCE);
        assertThat(testAccountInstitution.getFundSourceText()).isEqualTo(DEFAULT_FUND_SOURCE_TEXT);
        assertThat(testAccountInstitution.getArticleAssociation()).isEqualTo(DEFAULT_ARTICLE_ASSOCIATION);
        assertThat(testAccountInstitution.getBusinessRegNo()).isEqualTo(DEFAULT_BUSINESS_REG_NO);
        assertThat(testAccountInstitution.getFinancialAsset1()).isEqualTo(DEFAULT_FINANCIAL_ASSET_1);
        assertThat(testAccountInstitution.getFinancialAsset2()).isEqualTo(DEFAULT_FINANCIAL_ASSET_2);
        assertThat(testAccountInstitution.getFinancialAsset3()).isEqualTo(DEFAULT_FINANCIAL_ASSET_3);
        assertThat(testAccountInstitution.getOperatingProfit1()).isEqualTo(DEFAULT_OPERATING_PROFIT_1);
        assertThat(testAccountInstitution.getOperatingProfit2()).isEqualTo(DEFAULT_OPERATING_PROFIT_2);
        assertThat(testAccountInstitution.getOperatingProfit3()).isEqualTo(DEFAULT_OPERATING_PROFIT_3);
        assertThat(testAccountInstitution.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testAccountInstitution.getInvestmentObjective()).isEqualTo(DEFAULT_INVESTMENT_OBJECTIVE);
        assertThat(testAccountInstitution.getDirectSID()).isEqualTo(DEFAULT_DIRECT_SID);
        assertThat(testAccountInstitution.getAssetOwner()).isEqualTo(DEFAULT_ASSET_OWNER);
        assertThat(testAccountInstitution.getSupDocType()).isEqualTo(DEFAULT_SUP_DOC_TYPE);
        assertThat(testAccountInstitution.getSupDocExpDate()).isEqualTo(DEFAULT_SUP_DOC_EXP_DATE);
        assertThat(testAccountInstitution.getTaxId()).isEqualTo(DEFAULT_TAX_ID);
        assertThat(testAccountInstitution.getLegalDomicileId()).isEqualTo(DEFAULT_LEGAL_DOMICILE_ID);
        assertThat(testAccountInstitution.getCreateSystemDate()).isEqualTo(DEFAULT_CREATE_SYSTEM_DATE);
        assertThat(testAccountInstitution.getCreateDate()).isEqualTo(DEFAULT_CREATE_DATE);
        assertThat(testAccountInstitution.getCreateUserId()).isEqualTo(DEFAULT_CREATE_USER_ID);
        assertThat(testAccountInstitution.getLastModificationSystemDate()).isEqualTo(DEFAULT_LAST_MODIFICATION_SYSTEM_DATE);
        assertThat(testAccountInstitution.getLastModificationDate()).isEqualTo(DEFAULT_LAST_MODIFICATION_DATE);
        assertThat(testAccountInstitution.getLastModificationUserId()).isEqualTo(DEFAULT_LAST_MODIFICATION_USER_ID);
    }

    @Test
    @Transactional
    public void createAccountInstitutionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = accountInstitutionRepository.findAll().size();

        // Create the AccountInstitution with an existing ID
        accountInstitution.setId(1L);
        AccountInstitutionDTO accountInstitutionDTO = accountInstitutionMapper.toDto(accountInstitution);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAccountInstitutionMockMvc.perform(post("/api/account-institutions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(accountInstitutionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AccountInstitution in the database
        List<AccountInstitution> accountInstitutionList = accountInstitutionRepository.findAll();
        assertThat(accountInstitutionList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllAccountInstitutions() throws Exception {
        // Initialize the database
        accountInstitutionRepository.saveAndFlush(accountInstitution);

        // Get all the accountInstitutionList
        restAccountInstitutionMockMvc.perform(get("/api/account-institutions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(accountInstitution.getId().intValue())))
            .andExpect(jsonPath("$.[*].sid").value(hasItem(DEFAULT_SID)))
            .andExpect(jsonPath("$.[*].companyName").value(hasItem(DEFAULT_COMPANY_NAME)))
            .andExpect(jsonPath("$.[*].bicCode").value(hasItem(DEFAULT_BIC_CODE)))
            .andExpect(jsonPath("$.[*].npwp").value(hasItem(DEFAULT_NPWP)))
            .andExpect(jsonPath("$.[*].npwpRegDate").value(hasItem(DEFAULT_NPWP_REG_DATE.toString())))
            .andExpect(jsonPath("$.[*].skd").value(hasItem(DEFAULT_SKD)))
            .andExpect(jsonPath("$.[*].skdExpDate").value(hasItem(DEFAULT_SKD_EXP_DATE.toString())))
            .andExpect(jsonPath("$.[*].companyEstablishPlace").value(hasItem(DEFAULT_COMPANY_ESTABLISH_PLACE)))
            .andExpect(jsonPath("$.[*].companyEstablishDate").value(hasItem(DEFAULT_COMPANY_ESTABLISH_DATE.toString())))
            .andExpect(jsonPath("$.[*].businessType").value(hasItem(DEFAULT_BUSINESS_TYPE)))
            .andExpect(jsonPath("$.[*].companyCharacteristic").value(hasItem(DEFAULT_COMPANY_CHARACTERISTIC)))
            .andExpect(jsonPath("$.[*].fundSource").value(hasItem(DEFAULT_FUND_SOURCE)))
            .andExpect(jsonPath("$.[*].fundSourceText").value(hasItem(DEFAULT_FUND_SOURCE_TEXT)))
            .andExpect(jsonPath("$.[*].articleAssociation").value(hasItem(DEFAULT_ARTICLE_ASSOCIATION)))
            .andExpect(jsonPath("$.[*].businessRegNo").value(hasItem(DEFAULT_BUSINESS_REG_NO)))
            .andExpect(jsonPath("$.[*].financialAsset1").value(hasItem(DEFAULT_FINANCIAL_ASSET_1.doubleValue())))
            .andExpect(jsonPath("$.[*].financialAsset2").value(hasItem(DEFAULT_FINANCIAL_ASSET_2.doubleValue())))
            .andExpect(jsonPath("$.[*].financialAsset3").value(hasItem(DEFAULT_FINANCIAL_ASSET_3.doubleValue())))
            .andExpect(jsonPath("$.[*].operatingProfit1").value(hasItem(DEFAULT_OPERATING_PROFIT_1.doubleValue())))
            .andExpect(jsonPath("$.[*].operatingProfit2").value(hasItem(DEFAULT_OPERATING_PROFIT_2.doubleValue())))
            .andExpect(jsonPath("$.[*].operatingProfit3").value(hasItem(DEFAULT_OPERATING_PROFIT_3.doubleValue())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].investmentObjective").value(hasItem(DEFAULT_INVESTMENT_OBJECTIVE)))
            .andExpect(jsonPath("$.[*].directSID").value(hasItem(DEFAULT_DIRECT_SID)))
            .andExpect(jsonPath("$.[*].assetOwner").value(hasItem(DEFAULT_ASSET_OWNER)))
            .andExpect(jsonPath("$.[*].supDocType").value(hasItem(DEFAULT_SUP_DOC_TYPE)))
            .andExpect(jsonPath("$.[*].supDocExpDate").value(hasItem(DEFAULT_SUP_DOC_EXP_DATE.toString())))
            .andExpect(jsonPath("$.[*].taxId").value(hasItem(DEFAULT_TAX_ID.intValue())))
            .andExpect(jsonPath("$.[*].legalDomicileId").value(hasItem(DEFAULT_LEGAL_DOMICILE_ID.intValue())))
            .andExpect(jsonPath("$.[*].createSystemDate").value(hasItem(DEFAULT_CREATE_SYSTEM_DATE.toString())))
            .andExpect(jsonPath("$.[*].createDate").value(hasItem(sameInstant(DEFAULT_CREATE_DATE))))
            .andExpect(jsonPath("$.[*].createUserId").value(hasItem(DEFAULT_CREATE_USER_ID.intValue())))
            .andExpect(jsonPath("$.[*].lastModificationSystemDate").value(hasItem(DEFAULT_LAST_MODIFICATION_SYSTEM_DATE.toString())))
            .andExpect(jsonPath("$.[*].lastModificationDate").value(hasItem(sameInstant(DEFAULT_LAST_MODIFICATION_DATE))))
            .andExpect(jsonPath("$.[*].lastModificationUserId").value(hasItem(DEFAULT_LAST_MODIFICATION_USER_ID.intValue())));
    }
    
    @Test
    @Transactional
    public void getAccountInstitution() throws Exception {
        // Initialize the database
        accountInstitutionRepository.saveAndFlush(accountInstitution);

        // Get the accountInstitution
        restAccountInstitutionMockMvc.perform(get("/api/account-institutions/{id}", accountInstitution.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(accountInstitution.getId().intValue()))
            .andExpect(jsonPath("$.sid").value(DEFAULT_SID))
            .andExpect(jsonPath("$.companyName").value(DEFAULT_COMPANY_NAME))
            .andExpect(jsonPath("$.bicCode").value(DEFAULT_BIC_CODE))
            .andExpect(jsonPath("$.npwp").value(DEFAULT_NPWP))
            .andExpect(jsonPath("$.npwpRegDate").value(DEFAULT_NPWP_REG_DATE.toString()))
            .andExpect(jsonPath("$.skd").value(DEFAULT_SKD))
            .andExpect(jsonPath("$.skdExpDate").value(DEFAULT_SKD_EXP_DATE.toString()))
            .andExpect(jsonPath("$.companyEstablishPlace").value(DEFAULT_COMPANY_ESTABLISH_PLACE))
            .andExpect(jsonPath("$.companyEstablishDate").value(DEFAULT_COMPANY_ESTABLISH_DATE.toString()))
            .andExpect(jsonPath("$.businessType").value(DEFAULT_BUSINESS_TYPE))
            .andExpect(jsonPath("$.companyCharacteristic").value(DEFAULT_COMPANY_CHARACTERISTIC))
            .andExpect(jsonPath("$.fundSource").value(DEFAULT_FUND_SOURCE))
            .andExpect(jsonPath("$.fundSourceText").value(DEFAULT_FUND_SOURCE_TEXT))
            .andExpect(jsonPath("$.articleAssociation").value(DEFAULT_ARTICLE_ASSOCIATION))
            .andExpect(jsonPath("$.businessRegNo").value(DEFAULT_BUSINESS_REG_NO))
            .andExpect(jsonPath("$.financialAsset1").value(DEFAULT_FINANCIAL_ASSET_1.doubleValue()))
            .andExpect(jsonPath("$.financialAsset2").value(DEFAULT_FINANCIAL_ASSET_2.doubleValue()))
            .andExpect(jsonPath("$.financialAsset3").value(DEFAULT_FINANCIAL_ASSET_3.doubleValue()))
            .andExpect(jsonPath("$.operatingProfit1").value(DEFAULT_OPERATING_PROFIT_1.doubleValue()))
            .andExpect(jsonPath("$.operatingProfit2").value(DEFAULT_OPERATING_PROFIT_2.doubleValue()))
            .andExpect(jsonPath("$.operatingProfit3").value(DEFAULT_OPERATING_PROFIT_3.doubleValue()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.investmentObjective").value(DEFAULT_INVESTMENT_OBJECTIVE))
            .andExpect(jsonPath("$.directSID").value(DEFAULT_DIRECT_SID))
            .andExpect(jsonPath("$.assetOwner").value(DEFAULT_ASSET_OWNER))
            .andExpect(jsonPath("$.supDocType").value(DEFAULT_SUP_DOC_TYPE))
            .andExpect(jsonPath("$.supDocExpDate").value(DEFAULT_SUP_DOC_EXP_DATE.toString()))
            .andExpect(jsonPath("$.taxId").value(DEFAULT_TAX_ID.intValue()))
            .andExpect(jsonPath("$.legalDomicileId").value(DEFAULT_LEGAL_DOMICILE_ID.intValue()))
            .andExpect(jsonPath("$.createSystemDate").value(DEFAULT_CREATE_SYSTEM_DATE.toString()))
            .andExpect(jsonPath("$.createDate").value(sameInstant(DEFAULT_CREATE_DATE)))
            .andExpect(jsonPath("$.createUserId").value(DEFAULT_CREATE_USER_ID.intValue()))
            .andExpect(jsonPath("$.lastModificationSystemDate").value(DEFAULT_LAST_MODIFICATION_SYSTEM_DATE.toString()))
            .andExpect(jsonPath("$.lastModificationDate").value(sameInstant(DEFAULT_LAST_MODIFICATION_DATE)))
            .andExpect(jsonPath("$.lastModificationUserId").value(DEFAULT_LAST_MODIFICATION_USER_ID.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingAccountInstitution() throws Exception {
        // Get the accountInstitution
        restAccountInstitutionMockMvc.perform(get("/api/account-institutions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAccountInstitution() throws Exception {
        // Initialize the database
        accountInstitutionRepository.saveAndFlush(accountInstitution);

        int databaseSizeBeforeUpdate = accountInstitutionRepository.findAll().size();

        // Update the accountInstitution
        AccountInstitution updatedAccountInstitution = accountInstitutionRepository.findById(accountInstitution.getId()).get();
        // Disconnect from session so that the updates on updatedAccountInstitution are not directly saved in db
        em.detach(updatedAccountInstitution);
        updatedAccountInstitution
            .sid(UPDATED_SID)
            .companyName(UPDATED_COMPANY_NAME)
            .bicCode(UPDATED_BIC_CODE)
            .npwp(UPDATED_NPWP)
            .npwpRegDate(UPDATED_NPWP_REG_DATE)
            .skd(UPDATED_SKD)
            .skdExpDate(UPDATED_SKD_EXP_DATE)
            .companyEstablishPlace(UPDATED_COMPANY_ESTABLISH_PLACE)
            .companyEstablishDate(UPDATED_COMPANY_ESTABLISH_DATE)
            .businessType(UPDATED_BUSINESS_TYPE)
            .companyCharacteristic(UPDATED_COMPANY_CHARACTERISTIC)
            .fundSource(UPDATED_FUND_SOURCE)
            .fundSourceText(UPDATED_FUND_SOURCE_TEXT)
            .articleAssociation(UPDATED_ARTICLE_ASSOCIATION)
            .businessRegNo(UPDATED_BUSINESS_REG_NO)
            .financialAsset1(UPDATED_FINANCIAL_ASSET_1)
            .financialAsset2(UPDATED_FINANCIAL_ASSET_2)
            .financialAsset3(UPDATED_FINANCIAL_ASSET_3)
            .operatingProfit1(UPDATED_OPERATING_PROFIT_1)
            .operatingProfit2(UPDATED_OPERATING_PROFIT_2)
            .operatingProfit3(UPDATED_OPERATING_PROFIT_3)
            .description(UPDATED_DESCRIPTION)
            .investmentObjective(UPDATED_INVESTMENT_OBJECTIVE)
            .directSID(UPDATED_DIRECT_SID)
            .assetOwner(UPDATED_ASSET_OWNER)
            .supDocType(UPDATED_SUP_DOC_TYPE)
            .supDocExpDate(UPDATED_SUP_DOC_EXP_DATE)
            .taxId(UPDATED_TAX_ID)
            .legalDomicileId(UPDATED_LEGAL_DOMICILE_ID)
            .createSystemDate(UPDATED_CREATE_SYSTEM_DATE)
            .createDate(UPDATED_CREATE_DATE)
            .createUserId(UPDATED_CREATE_USER_ID)
            .lastModificationSystemDate(UPDATED_LAST_MODIFICATION_SYSTEM_DATE)
            .lastModificationDate(UPDATED_LAST_MODIFICATION_DATE)
            .lastModificationUserId(UPDATED_LAST_MODIFICATION_USER_ID);
        AccountInstitutionDTO accountInstitutionDTO = accountInstitutionMapper.toDto(updatedAccountInstitution);

        restAccountInstitutionMockMvc.perform(put("/api/account-institutions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(accountInstitutionDTO)))
            .andExpect(status().isOk());

        // Validate the AccountInstitution in the database
        List<AccountInstitution> accountInstitutionList = accountInstitutionRepository.findAll();
        assertThat(accountInstitutionList).hasSize(databaseSizeBeforeUpdate);
        AccountInstitution testAccountInstitution = accountInstitutionList.get(accountInstitutionList.size() - 1);
        assertThat(testAccountInstitution.getSid()).isEqualTo(UPDATED_SID);
        assertThat(testAccountInstitution.getCompanyName()).isEqualTo(UPDATED_COMPANY_NAME);
        assertThat(testAccountInstitution.getBicCode()).isEqualTo(UPDATED_BIC_CODE);
        assertThat(testAccountInstitution.getNpwp()).isEqualTo(UPDATED_NPWP);
        assertThat(testAccountInstitution.getNpwpRegDate()).isEqualTo(UPDATED_NPWP_REG_DATE);
        assertThat(testAccountInstitution.getSkd()).isEqualTo(UPDATED_SKD);
        assertThat(testAccountInstitution.getSkdExpDate()).isEqualTo(UPDATED_SKD_EXP_DATE);
        assertThat(testAccountInstitution.getCompanyEstablishPlace()).isEqualTo(UPDATED_COMPANY_ESTABLISH_PLACE);
        assertThat(testAccountInstitution.getCompanyEstablishDate()).isEqualTo(UPDATED_COMPANY_ESTABLISH_DATE);
        assertThat(testAccountInstitution.getBusinessType()).isEqualTo(UPDATED_BUSINESS_TYPE);
        assertThat(testAccountInstitution.getCompanyCharacteristic()).isEqualTo(UPDATED_COMPANY_CHARACTERISTIC);
        assertThat(testAccountInstitution.getFundSource()).isEqualTo(UPDATED_FUND_SOURCE);
        assertThat(testAccountInstitution.getFundSourceText()).isEqualTo(UPDATED_FUND_SOURCE_TEXT);
        assertThat(testAccountInstitution.getArticleAssociation()).isEqualTo(UPDATED_ARTICLE_ASSOCIATION);
        assertThat(testAccountInstitution.getBusinessRegNo()).isEqualTo(UPDATED_BUSINESS_REG_NO);
        assertThat(testAccountInstitution.getFinancialAsset1()).isEqualTo(UPDATED_FINANCIAL_ASSET_1);
        assertThat(testAccountInstitution.getFinancialAsset2()).isEqualTo(UPDATED_FINANCIAL_ASSET_2);
        assertThat(testAccountInstitution.getFinancialAsset3()).isEqualTo(UPDATED_FINANCIAL_ASSET_3);
        assertThat(testAccountInstitution.getOperatingProfit1()).isEqualTo(UPDATED_OPERATING_PROFIT_1);
        assertThat(testAccountInstitution.getOperatingProfit2()).isEqualTo(UPDATED_OPERATING_PROFIT_2);
        assertThat(testAccountInstitution.getOperatingProfit3()).isEqualTo(UPDATED_OPERATING_PROFIT_3);
        assertThat(testAccountInstitution.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testAccountInstitution.getInvestmentObjective()).isEqualTo(UPDATED_INVESTMENT_OBJECTIVE);
        assertThat(testAccountInstitution.getDirectSID()).isEqualTo(UPDATED_DIRECT_SID);
        assertThat(testAccountInstitution.getAssetOwner()).isEqualTo(UPDATED_ASSET_OWNER);
        assertThat(testAccountInstitution.getSupDocType()).isEqualTo(UPDATED_SUP_DOC_TYPE);
        assertThat(testAccountInstitution.getSupDocExpDate()).isEqualTo(UPDATED_SUP_DOC_EXP_DATE);
        assertThat(testAccountInstitution.getTaxId()).isEqualTo(UPDATED_TAX_ID);
        assertThat(testAccountInstitution.getLegalDomicileId()).isEqualTo(UPDATED_LEGAL_DOMICILE_ID);
        assertThat(testAccountInstitution.getCreateSystemDate()).isEqualTo(UPDATED_CREATE_SYSTEM_DATE);
        assertThat(testAccountInstitution.getCreateDate()).isEqualTo(UPDATED_CREATE_DATE);
        assertThat(testAccountInstitution.getCreateUserId()).isEqualTo(UPDATED_CREATE_USER_ID);
        assertThat(testAccountInstitution.getLastModificationSystemDate()).isEqualTo(UPDATED_LAST_MODIFICATION_SYSTEM_DATE);
        assertThat(testAccountInstitution.getLastModificationDate()).isEqualTo(UPDATED_LAST_MODIFICATION_DATE);
        assertThat(testAccountInstitution.getLastModificationUserId()).isEqualTo(UPDATED_LAST_MODIFICATION_USER_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingAccountInstitution() throws Exception {
        int databaseSizeBeforeUpdate = accountInstitutionRepository.findAll().size();

        // Create the AccountInstitution
        AccountInstitutionDTO accountInstitutionDTO = accountInstitutionMapper.toDto(accountInstitution);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAccountInstitutionMockMvc.perform(put("/api/account-institutions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(accountInstitutionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AccountInstitution in the database
        List<AccountInstitution> accountInstitutionList = accountInstitutionRepository.findAll();
        assertThat(accountInstitutionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAccountInstitution() throws Exception {
        // Initialize the database
        accountInstitutionRepository.saveAndFlush(accountInstitution);

        int databaseSizeBeforeDelete = accountInstitutionRepository.findAll().size();

        // Delete the accountInstitution
        restAccountInstitutionMockMvc.perform(delete("/api/account-institutions/{id}", accountInstitution.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AccountInstitution> accountInstitutionList = accountInstitutionRepository.findAll();
        assertThat(accountInstitutionList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AccountInstitution.class);
        AccountInstitution accountInstitution1 = new AccountInstitution();
        accountInstitution1.setId(1L);
        AccountInstitution accountInstitution2 = new AccountInstitution();
        accountInstitution2.setId(accountInstitution1.getId());
        assertThat(accountInstitution1).isEqualTo(accountInstitution2);
        accountInstitution2.setId(2L);
        assertThat(accountInstitution1).isNotEqualTo(accountInstitution2);
        accountInstitution1.setId(null);
        assertThat(accountInstitution1).isNotEqualTo(accountInstitution2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AccountInstitutionDTO.class);
        AccountInstitutionDTO accountInstitutionDTO1 = new AccountInstitutionDTO();
        accountInstitutionDTO1.setId(1L);
        AccountInstitutionDTO accountInstitutionDTO2 = new AccountInstitutionDTO();
        assertThat(accountInstitutionDTO1).isNotEqualTo(accountInstitutionDTO2);
        accountInstitutionDTO2.setId(accountInstitutionDTO1.getId());
        assertThat(accountInstitutionDTO1).isEqualTo(accountInstitutionDTO2);
        accountInstitutionDTO2.setId(2L);
        assertThat(accountInstitutionDTO1).isNotEqualTo(accountInstitutionDTO2);
        accountInstitutionDTO1.setId(null);
        assertThat(accountInstitutionDTO1).isNotEqualTo(accountInstitutionDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(accountInstitutionMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(accountInstitutionMapper.fromId(null)).isNull();
    }
}
