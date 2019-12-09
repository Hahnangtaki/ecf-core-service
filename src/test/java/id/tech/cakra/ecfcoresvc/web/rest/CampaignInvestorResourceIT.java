package id.tech.cakra.ecfcoresvc.web.rest;

import id.tech.cakra.ecfcoresvc.EcfcoresvcApp;
import id.tech.cakra.ecfcoresvc.domain.CampaignInvestor;
import id.tech.cakra.ecfcoresvc.repository.CampaignInvestorRepository;
import id.tech.cakra.ecfcoresvc.service.CampaignInvestorService;
import id.tech.cakra.ecfcoresvc.service.dto.CampaignInvestorDTO;
import id.tech.cakra.ecfcoresvc.service.mapper.CampaignInvestorMapper;
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
import java.math.BigDecimal;
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
 * Integration tests for the {@link CampaignInvestorResource} REST controller.
 */
@SpringBootTest(classes = EcfcoresvcApp.class)
public class CampaignInvestorResourceIT {

    private static final BigDecimal DEFAULT_QTY_ON_HAND = new BigDecimal(1);
    private static final BigDecimal UPDATED_QTY_ON_HAND = new BigDecimal(2);

    private static final BigDecimal DEFAULT_QTY_DEPOSIT = new BigDecimal(1);
    private static final BigDecimal UPDATED_QTY_DEPOSIT = new BigDecimal(2);

    private static final BigDecimal DEFAULT_QTY_WITHDRAW = new BigDecimal(1);
    private static final BigDecimal UPDATED_QTY_WITHDRAW = new BigDecimal(2);

    private static final BigDecimal DEFAULT_CAST_VALUE = new BigDecimal(1);
    private static final BigDecimal UPDATED_CAST_VALUE = new BigDecimal(2);

    private static final BigDecimal DEFAULT_PROFIT_LOSS = new BigDecimal(1);
    private static final BigDecimal UPDATED_PROFIT_LOSS = new BigDecimal(2);

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
    private CampaignInvestorRepository campaignInvestorRepository;

    @Autowired
    private CampaignInvestorMapper campaignInvestorMapper;

    @Autowired
    private CampaignInvestorService campaignInvestorService;

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

    private MockMvc restCampaignInvestorMockMvc;

    private CampaignInvestor campaignInvestor;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CampaignInvestorResource campaignInvestorResource = new CampaignInvestorResource(campaignInvestorService);
        this.restCampaignInvestorMockMvc = MockMvcBuilders.standaloneSetup(campaignInvestorResource)
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
    public static CampaignInvestor createEntity(EntityManager em) {
        CampaignInvestor campaignInvestor = new CampaignInvestor()
            .qtyOnHand(DEFAULT_QTY_ON_HAND)
            .qtyDeposit(DEFAULT_QTY_DEPOSIT)
            .qtyWithdraw(DEFAULT_QTY_WITHDRAW)
            .castValue(DEFAULT_CAST_VALUE)
            .profitLoss(DEFAULT_PROFIT_LOSS)
            .createSystemDate(DEFAULT_CREATE_SYSTEM_DATE)
            .createDate(DEFAULT_CREATE_DATE)
            .createUserId(DEFAULT_CREATE_USER_ID)
            .lastModificationSystemDate(DEFAULT_LAST_MODIFICATION_SYSTEM_DATE)
            .lastModificationDate(DEFAULT_LAST_MODIFICATION_DATE)
            .lastModificationUserId(DEFAULT_LAST_MODIFICATION_USER_ID);
        return campaignInvestor;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CampaignInvestor createUpdatedEntity(EntityManager em) {
        CampaignInvestor campaignInvestor = new CampaignInvestor()
            .qtyOnHand(UPDATED_QTY_ON_HAND)
            .qtyDeposit(UPDATED_QTY_DEPOSIT)
            .qtyWithdraw(UPDATED_QTY_WITHDRAW)
            .castValue(UPDATED_CAST_VALUE)
            .profitLoss(UPDATED_PROFIT_LOSS)
            .createSystemDate(UPDATED_CREATE_SYSTEM_DATE)
            .createDate(UPDATED_CREATE_DATE)
            .createUserId(UPDATED_CREATE_USER_ID)
            .lastModificationSystemDate(UPDATED_LAST_MODIFICATION_SYSTEM_DATE)
            .lastModificationDate(UPDATED_LAST_MODIFICATION_DATE)
            .lastModificationUserId(UPDATED_LAST_MODIFICATION_USER_ID);
        return campaignInvestor;
    }

    @BeforeEach
    public void initTest() {
        campaignInvestor = createEntity(em);
    }

    @Test
    @Transactional
    public void createCampaignInvestor() throws Exception {
        int databaseSizeBeforeCreate = campaignInvestorRepository.findAll().size();

        // Create the CampaignInvestor
        CampaignInvestorDTO campaignInvestorDTO = campaignInvestorMapper.toDto(campaignInvestor);
        restCampaignInvestorMockMvc.perform(post("/api/campaign-investors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(campaignInvestorDTO)))
            .andExpect(status().isCreated());

        // Validate the CampaignInvestor in the database
        List<CampaignInvestor> campaignInvestorList = campaignInvestorRepository.findAll();
        assertThat(campaignInvestorList).hasSize(databaseSizeBeforeCreate + 1);
        CampaignInvestor testCampaignInvestor = campaignInvestorList.get(campaignInvestorList.size() - 1);
        assertThat(testCampaignInvestor.getQtyOnHand()).isEqualTo(DEFAULT_QTY_ON_HAND);
        assertThat(testCampaignInvestor.getQtyDeposit()).isEqualTo(DEFAULT_QTY_DEPOSIT);
        assertThat(testCampaignInvestor.getQtyWithdraw()).isEqualTo(DEFAULT_QTY_WITHDRAW);
        assertThat(testCampaignInvestor.getCastValue()).isEqualTo(DEFAULT_CAST_VALUE);
        assertThat(testCampaignInvestor.getProfitLoss()).isEqualTo(DEFAULT_PROFIT_LOSS);
        assertThat(testCampaignInvestor.getCreateSystemDate()).isEqualTo(DEFAULT_CREATE_SYSTEM_DATE);
        assertThat(testCampaignInvestor.getCreateDate()).isEqualTo(DEFAULT_CREATE_DATE);
        assertThat(testCampaignInvestor.getCreateUserId()).isEqualTo(DEFAULT_CREATE_USER_ID);
        assertThat(testCampaignInvestor.getLastModificationSystemDate()).isEqualTo(DEFAULT_LAST_MODIFICATION_SYSTEM_DATE);
        assertThat(testCampaignInvestor.getLastModificationDate()).isEqualTo(DEFAULT_LAST_MODIFICATION_DATE);
        assertThat(testCampaignInvestor.getLastModificationUserId()).isEqualTo(DEFAULT_LAST_MODIFICATION_USER_ID);
    }

    @Test
    @Transactional
    public void createCampaignInvestorWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = campaignInvestorRepository.findAll().size();

        // Create the CampaignInvestor with an existing ID
        campaignInvestor.setId(1L);
        CampaignInvestorDTO campaignInvestorDTO = campaignInvestorMapper.toDto(campaignInvestor);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCampaignInvestorMockMvc.perform(post("/api/campaign-investors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(campaignInvestorDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CampaignInvestor in the database
        List<CampaignInvestor> campaignInvestorList = campaignInvestorRepository.findAll();
        assertThat(campaignInvestorList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllCampaignInvestors() throws Exception {
        // Initialize the database
        campaignInvestorRepository.saveAndFlush(campaignInvestor);

        // Get all the campaignInvestorList
        restCampaignInvestorMockMvc.perform(get("/api/campaign-investors?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(campaignInvestor.getId().intValue())))
            .andExpect(jsonPath("$.[*].qtyOnHand").value(hasItem(DEFAULT_QTY_ON_HAND.intValue())))
            .andExpect(jsonPath("$.[*].qtyDeposit").value(hasItem(DEFAULT_QTY_DEPOSIT.intValue())))
            .andExpect(jsonPath("$.[*].qtyWithdraw").value(hasItem(DEFAULT_QTY_WITHDRAW.intValue())))
            .andExpect(jsonPath("$.[*].castValue").value(hasItem(DEFAULT_CAST_VALUE.intValue())))
            .andExpect(jsonPath("$.[*].profitLoss").value(hasItem(DEFAULT_PROFIT_LOSS.intValue())))
            .andExpect(jsonPath("$.[*].createSystemDate").value(hasItem(DEFAULT_CREATE_SYSTEM_DATE.toString())))
            .andExpect(jsonPath("$.[*].createDate").value(hasItem(sameInstant(DEFAULT_CREATE_DATE))))
            .andExpect(jsonPath("$.[*].createUserId").value(hasItem(DEFAULT_CREATE_USER_ID.intValue())))
            .andExpect(jsonPath("$.[*].lastModificationSystemDate").value(hasItem(DEFAULT_LAST_MODIFICATION_SYSTEM_DATE.toString())))
            .andExpect(jsonPath("$.[*].lastModificationDate").value(hasItem(sameInstant(DEFAULT_LAST_MODIFICATION_DATE))))
            .andExpect(jsonPath("$.[*].lastModificationUserId").value(hasItem(DEFAULT_LAST_MODIFICATION_USER_ID.intValue())));
    }
    
    @Test
    @Transactional
    public void getCampaignInvestor() throws Exception {
        // Initialize the database
        campaignInvestorRepository.saveAndFlush(campaignInvestor);

        // Get the campaignInvestor
        restCampaignInvestorMockMvc.perform(get("/api/campaign-investors/{id}", campaignInvestor.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(campaignInvestor.getId().intValue()))
            .andExpect(jsonPath("$.qtyOnHand").value(DEFAULT_QTY_ON_HAND.intValue()))
            .andExpect(jsonPath("$.qtyDeposit").value(DEFAULT_QTY_DEPOSIT.intValue()))
            .andExpect(jsonPath("$.qtyWithdraw").value(DEFAULT_QTY_WITHDRAW.intValue()))
            .andExpect(jsonPath("$.castValue").value(DEFAULT_CAST_VALUE.intValue()))
            .andExpect(jsonPath("$.profitLoss").value(DEFAULT_PROFIT_LOSS.intValue()))
            .andExpect(jsonPath("$.createSystemDate").value(DEFAULT_CREATE_SYSTEM_DATE.toString()))
            .andExpect(jsonPath("$.createDate").value(sameInstant(DEFAULT_CREATE_DATE)))
            .andExpect(jsonPath("$.createUserId").value(DEFAULT_CREATE_USER_ID.intValue()))
            .andExpect(jsonPath("$.lastModificationSystemDate").value(DEFAULT_LAST_MODIFICATION_SYSTEM_DATE.toString()))
            .andExpect(jsonPath("$.lastModificationDate").value(sameInstant(DEFAULT_LAST_MODIFICATION_DATE)))
            .andExpect(jsonPath("$.lastModificationUserId").value(DEFAULT_LAST_MODIFICATION_USER_ID.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingCampaignInvestor() throws Exception {
        // Get the campaignInvestor
        restCampaignInvestorMockMvc.perform(get("/api/campaign-investors/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCampaignInvestor() throws Exception {
        // Initialize the database
        campaignInvestorRepository.saveAndFlush(campaignInvestor);

        int databaseSizeBeforeUpdate = campaignInvestorRepository.findAll().size();

        // Update the campaignInvestor
        CampaignInvestor updatedCampaignInvestor = campaignInvestorRepository.findById(campaignInvestor.getId()).get();
        // Disconnect from session so that the updates on updatedCampaignInvestor are not directly saved in db
        em.detach(updatedCampaignInvestor);
        updatedCampaignInvestor
            .qtyOnHand(UPDATED_QTY_ON_HAND)
            .qtyDeposit(UPDATED_QTY_DEPOSIT)
            .qtyWithdraw(UPDATED_QTY_WITHDRAW)
            .castValue(UPDATED_CAST_VALUE)
            .profitLoss(UPDATED_PROFIT_LOSS)
            .createSystemDate(UPDATED_CREATE_SYSTEM_DATE)
            .createDate(UPDATED_CREATE_DATE)
            .createUserId(UPDATED_CREATE_USER_ID)
            .lastModificationSystemDate(UPDATED_LAST_MODIFICATION_SYSTEM_DATE)
            .lastModificationDate(UPDATED_LAST_MODIFICATION_DATE)
            .lastModificationUserId(UPDATED_LAST_MODIFICATION_USER_ID);
        CampaignInvestorDTO campaignInvestorDTO = campaignInvestorMapper.toDto(updatedCampaignInvestor);

        restCampaignInvestorMockMvc.perform(put("/api/campaign-investors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(campaignInvestorDTO)))
            .andExpect(status().isOk());

        // Validate the CampaignInvestor in the database
        List<CampaignInvestor> campaignInvestorList = campaignInvestorRepository.findAll();
        assertThat(campaignInvestorList).hasSize(databaseSizeBeforeUpdate);
        CampaignInvestor testCampaignInvestor = campaignInvestorList.get(campaignInvestorList.size() - 1);
        assertThat(testCampaignInvestor.getQtyOnHand()).isEqualTo(UPDATED_QTY_ON_HAND);
        assertThat(testCampaignInvestor.getQtyDeposit()).isEqualTo(UPDATED_QTY_DEPOSIT);
        assertThat(testCampaignInvestor.getQtyWithdraw()).isEqualTo(UPDATED_QTY_WITHDRAW);
        assertThat(testCampaignInvestor.getCastValue()).isEqualTo(UPDATED_CAST_VALUE);
        assertThat(testCampaignInvestor.getProfitLoss()).isEqualTo(UPDATED_PROFIT_LOSS);
        assertThat(testCampaignInvestor.getCreateSystemDate()).isEqualTo(UPDATED_CREATE_SYSTEM_DATE);
        assertThat(testCampaignInvestor.getCreateDate()).isEqualTo(UPDATED_CREATE_DATE);
        assertThat(testCampaignInvestor.getCreateUserId()).isEqualTo(UPDATED_CREATE_USER_ID);
        assertThat(testCampaignInvestor.getLastModificationSystemDate()).isEqualTo(UPDATED_LAST_MODIFICATION_SYSTEM_DATE);
        assertThat(testCampaignInvestor.getLastModificationDate()).isEqualTo(UPDATED_LAST_MODIFICATION_DATE);
        assertThat(testCampaignInvestor.getLastModificationUserId()).isEqualTo(UPDATED_LAST_MODIFICATION_USER_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingCampaignInvestor() throws Exception {
        int databaseSizeBeforeUpdate = campaignInvestorRepository.findAll().size();

        // Create the CampaignInvestor
        CampaignInvestorDTO campaignInvestorDTO = campaignInvestorMapper.toDto(campaignInvestor);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCampaignInvestorMockMvc.perform(put("/api/campaign-investors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(campaignInvestorDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CampaignInvestor in the database
        List<CampaignInvestor> campaignInvestorList = campaignInvestorRepository.findAll();
        assertThat(campaignInvestorList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCampaignInvestor() throws Exception {
        // Initialize the database
        campaignInvestorRepository.saveAndFlush(campaignInvestor);

        int databaseSizeBeforeDelete = campaignInvestorRepository.findAll().size();

        // Delete the campaignInvestor
        restCampaignInvestorMockMvc.perform(delete("/api/campaign-investors/{id}", campaignInvestor.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CampaignInvestor> campaignInvestorList = campaignInvestorRepository.findAll();
        assertThat(campaignInvestorList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
