package id.tech.cakra.ecfcoresvc.web.rest;

import id.tech.cakra.ecfcoresvc.EcfcoresvcApp;
import id.tech.cakra.ecfcoresvc.domain.CampaignPayment;
import id.tech.cakra.ecfcoresvc.repository.CampaignPaymentRepository;
import id.tech.cakra.ecfcoresvc.service.CampaignPaymentService;
import id.tech.cakra.ecfcoresvc.service.dto.CampaignPaymentDTO;
import id.tech.cakra.ecfcoresvc.service.mapper.CampaignPaymentMapper;
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
 * Integration tests for the {@link CampaignPaymentResource} REST controller.
 */
@SpringBootTest(classes = EcfcoresvcApp.class)
public class CampaignPaymentResourceIT {

    private static final String DEFAULT_PAYMENT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_PAYMENT_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_PAYMENT_DESC = "AAAAAAAAAA";
    private static final String UPDATED_PAYMENT_DESC = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_AMOUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_AMOUNT = new BigDecimal(2);

    private static final String DEFAULT_STATUS = "A";
    private static final String UPDATED_STATUS = "B";

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
    private CampaignPaymentRepository campaignPaymentRepository;

    @Autowired
    private CampaignPaymentMapper campaignPaymentMapper;

    @Autowired
    private CampaignPaymentService campaignPaymentService;

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

    private MockMvc restCampaignPaymentMockMvc;

    private CampaignPayment campaignPayment;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CampaignPaymentResource campaignPaymentResource = new CampaignPaymentResource(campaignPaymentService);
        this.restCampaignPaymentMockMvc = MockMvcBuilders.standaloneSetup(campaignPaymentResource)
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
    public static CampaignPayment createEntity(EntityManager em) {
        CampaignPayment campaignPayment = new CampaignPayment()
            .paymentCode(DEFAULT_PAYMENT_CODE)
            .paymentDesc(DEFAULT_PAYMENT_DESC)
            .amount(DEFAULT_AMOUNT)
            .status(DEFAULT_STATUS)
            .createSystemDate(DEFAULT_CREATE_SYSTEM_DATE)
            .createDate(DEFAULT_CREATE_DATE)
            .createUserId(DEFAULT_CREATE_USER_ID)
            .lastModificationSystemDate(DEFAULT_LAST_MODIFICATION_SYSTEM_DATE)
            .lastModificationDate(DEFAULT_LAST_MODIFICATION_DATE)
            .lastModificationUserId(DEFAULT_LAST_MODIFICATION_USER_ID);
        return campaignPayment;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CampaignPayment createUpdatedEntity(EntityManager em) {
        CampaignPayment campaignPayment = new CampaignPayment()
            .paymentCode(UPDATED_PAYMENT_CODE)
            .paymentDesc(UPDATED_PAYMENT_DESC)
            .amount(UPDATED_AMOUNT)
            .status(UPDATED_STATUS)
            .createSystemDate(UPDATED_CREATE_SYSTEM_DATE)
            .createDate(UPDATED_CREATE_DATE)
            .createUserId(UPDATED_CREATE_USER_ID)
            .lastModificationSystemDate(UPDATED_LAST_MODIFICATION_SYSTEM_DATE)
            .lastModificationDate(UPDATED_LAST_MODIFICATION_DATE)
            .lastModificationUserId(UPDATED_LAST_MODIFICATION_USER_ID);
        return campaignPayment;
    }

    @BeforeEach
    public void initTest() {
        campaignPayment = createEntity(em);
    }

    @Test
    @Transactional
    public void createCampaignPayment() throws Exception {
        int databaseSizeBeforeCreate = campaignPaymentRepository.findAll().size();

        // Create the CampaignPayment
        CampaignPaymentDTO campaignPaymentDTO = campaignPaymentMapper.toDto(campaignPayment);
        restCampaignPaymentMockMvc.perform(post("/api/campaign-payments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(campaignPaymentDTO)))
            .andExpect(status().isCreated());

        // Validate the CampaignPayment in the database
        List<CampaignPayment> campaignPaymentList = campaignPaymentRepository.findAll();
        assertThat(campaignPaymentList).hasSize(databaseSizeBeforeCreate + 1);
        CampaignPayment testCampaignPayment = campaignPaymentList.get(campaignPaymentList.size() - 1);
        assertThat(testCampaignPayment.getPaymentCode()).isEqualTo(DEFAULT_PAYMENT_CODE);
        assertThat(testCampaignPayment.getPaymentDesc()).isEqualTo(DEFAULT_PAYMENT_DESC);
        assertThat(testCampaignPayment.getAmount()).isEqualTo(DEFAULT_AMOUNT);
        assertThat(testCampaignPayment.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testCampaignPayment.getCreateSystemDate()).isEqualTo(DEFAULT_CREATE_SYSTEM_DATE);
        assertThat(testCampaignPayment.getCreateDate()).isEqualTo(DEFAULT_CREATE_DATE);
        assertThat(testCampaignPayment.getCreateUserId()).isEqualTo(DEFAULT_CREATE_USER_ID);
        assertThat(testCampaignPayment.getLastModificationSystemDate()).isEqualTo(DEFAULT_LAST_MODIFICATION_SYSTEM_DATE);
        assertThat(testCampaignPayment.getLastModificationDate()).isEqualTo(DEFAULT_LAST_MODIFICATION_DATE);
        assertThat(testCampaignPayment.getLastModificationUserId()).isEqualTo(DEFAULT_LAST_MODIFICATION_USER_ID);
    }

    @Test
    @Transactional
    public void createCampaignPaymentWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = campaignPaymentRepository.findAll().size();

        // Create the CampaignPayment with an existing ID
        campaignPayment.setId(1L);
        CampaignPaymentDTO campaignPaymentDTO = campaignPaymentMapper.toDto(campaignPayment);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCampaignPaymentMockMvc.perform(post("/api/campaign-payments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(campaignPaymentDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CampaignPayment in the database
        List<CampaignPayment> campaignPaymentList = campaignPaymentRepository.findAll();
        assertThat(campaignPaymentList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllCampaignPayments() throws Exception {
        // Initialize the database
        campaignPaymentRepository.saveAndFlush(campaignPayment);

        // Get all the campaignPaymentList
        restCampaignPaymentMockMvc.perform(get("/api/campaign-payments?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(campaignPayment.getId().intValue())))
            .andExpect(jsonPath("$.[*].paymentCode").value(hasItem(DEFAULT_PAYMENT_CODE)))
            .andExpect(jsonPath("$.[*].paymentDesc").value(hasItem(DEFAULT_PAYMENT_DESC)))
            .andExpect(jsonPath("$.[*].amount").value(hasItem(DEFAULT_AMOUNT.intValue())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].createSystemDate").value(hasItem(DEFAULT_CREATE_SYSTEM_DATE.toString())))
            .andExpect(jsonPath("$.[*].createDate").value(hasItem(sameInstant(DEFAULT_CREATE_DATE))))
            .andExpect(jsonPath("$.[*].createUserId").value(hasItem(DEFAULT_CREATE_USER_ID.intValue())))
            .andExpect(jsonPath("$.[*].lastModificationSystemDate").value(hasItem(DEFAULT_LAST_MODIFICATION_SYSTEM_DATE.toString())))
            .andExpect(jsonPath("$.[*].lastModificationDate").value(hasItem(sameInstant(DEFAULT_LAST_MODIFICATION_DATE))))
            .andExpect(jsonPath("$.[*].lastModificationUserId").value(hasItem(DEFAULT_LAST_MODIFICATION_USER_ID.intValue())));
    }
    
    @Test
    @Transactional
    public void getCampaignPayment() throws Exception {
        // Initialize the database
        campaignPaymentRepository.saveAndFlush(campaignPayment);

        // Get the campaignPayment
        restCampaignPaymentMockMvc.perform(get("/api/campaign-payments/{id}", campaignPayment.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(campaignPayment.getId().intValue()))
            .andExpect(jsonPath("$.paymentCode").value(DEFAULT_PAYMENT_CODE))
            .andExpect(jsonPath("$.paymentDesc").value(DEFAULT_PAYMENT_DESC))
            .andExpect(jsonPath("$.amount").value(DEFAULT_AMOUNT.intValue()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.createSystemDate").value(DEFAULT_CREATE_SYSTEM_DATE.toString()))
            .andExpect(jsonPath("$.createDate").value(sameInstant(DEFAULT_CREATE_DATE)))
            .andExpect(jsonPath("$.createUserId").value(DEFAULT_CREATE_USER_ID.intValue()))
            .andExpect(jsonPath("$.lastModificationSystemDate").value(DEFAULT_LAST_MODIFICATION_SYSTEM_DATE.toString()))
            .andExpect(jsonPath("$.lastModificationDate").value(sameInstant(DEFAULT_LAST_MODIFICATION_DATE)))
            .andExpect(jsonPath("$.lastModificationUserId").value(DEFAULT_LAST_MODIFICATION_USER_ID.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingCampaignPayment() throws Exception {
        // Get the campaignPayment
        restCampaignPaymentMockMvc.perform(get("/api/campaign-payments/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCampaignPayment() throws Exception {
        // Initialize the database
        campaignPaymentRepository.saveAndFlush(campaignPayment);

        int databaseSizeBeforeUpdate = campaignPaymentRepository.findAll().size();

        // Update the campaignPayment
        CampaignPayment updatedCampaignPayment = campaignPaymentRepository.findById(campaignPayment.getId()).get();
        // Disconnect from session so that the updates on updatedCampaignPayment are not directly saved in db
        em.detach(updatedCampaignPayment);
        updatedCampaignPayment
            .paymentCode(UPDATED_PAYMENT_CODE)
            .paymentDesc(UPDATED_PAYMENT_DESC)
            .amount(UPDATED_AMOUNT)
            .status(UPDATED_STATUS)
            .createSystemDate(UPDATED_CREATE_SYSTEM_DATE)
            .createDate(UPDATED_CREATE_DATE)
            .createUserId(UPDATED_CREATE_USER_ID)
            .lastModificationSystemDate(UPDATED_LAST_MODIFICATION_SYSTEM_DATE)
            .lastModificationDate(UPDATED_LAST_MODIFICATION_DATE)
            .lastModificationUserId(UPDATED_LAST_MODIFICATION_USER_ID);
        CampaignPaymentDTO campaignPaymentDTO = campaignPaymentMapper.toDto(updatedCampaignPayment);

        restCampaignPaymentMockMvc.perform(put("/api/campaign-payments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(campaignPaymentDTO)))
            .andExpect(status().isOk());

        // Validate the CampaignPayment in the database
        List<CampaignPayment> campaignPaymentList = campaignPaymentRepository.findAll();
        assertThat(campaignPaymentList).hasSize(databaseSizeBeforeUpdate);
        CampaignPayment testCampaignPayment = campaignPaymentList.get(campaignPaymentList.size() - 1);
        assertThat(testCampaignPayment.getPaymentCode()).isEqualTo(UPDATED_PAYMENT_CODE);
        assertThat(testCampaignPayment.getPaymentDesc()).isEqualTo(UPDATED_PAYMENT_DESC);
        assertThat(testCampaignPayment.getAmount()).isEqualTo(UPDATED_AMOUNT);
        assertThat(testCampaignPayment.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testCampaignPayment.getCreateSystemDate()).isEqualTo(UPDATED_CREATE_SYSTEM_DATE);
        assertThat(testCampaignPayment.getCreateDate()).isEqualTo(UPDATED_CREATE_DATE);
        assertThat(testCampaignPayment.getCreateUserId()).isEqualTo(UPDATED_CREATE_USER_ID);
        assertThat(testCampaignPayment.getLastModificationSystemDate()).isEqualTo(UPDATED_LAST_MODIFICATION_SYSTEM_DATE);
        assertThat(testCampaignPayment.getLastModificationDate()).isEqualTo(UPDATED_LAST_MODIFICATION_DATE);
        assertThat(testCampaignPayment.getLastModificationUserId()).isEqualTo(UPDATED_LAST_MODIFICATION_USER_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingCampaignPayment() throws Exception {
        int databaseSizeBeforeUpdate = campaignPaymentRepository.findAll().size();

        // Create the CampaignPayment
        CampaignPaymentDTO campaignPaymentDTO = campaignPaymentMapper.toDto(campaignPayment);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCampaignPaymentMockMvc.perform(put("/api/campaign-payments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(campaignPaymentDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CampaignPayment in the database
        List<CampaignPayment> campaignPaymentList = campaignPaymentRepository.findAll();
        assertThat(campaignPaymentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCampaignPayment() throws Exception {
        // Initialize the database
        campaignPaymentRepository.saveAndFlush(campaignPayment);

        int databaseSizeBeforeDelete = campaignPaymentRepository.findAll().size();

        // Delete the campaignPayment
        restCampaignPaymentMockMvc.perform(delete("/api/campaign-payments/{id}", campaignPayment.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CampaignPayment> campaignPaymentList = campaignPaymentRepository.findAll();
        assertThat(campaignPaymentList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
