package id.tech.cakra.ecfcoresvc.web.rest;

import id.tech.cakra.ecfcoresvc.EcfcoresvcApp;
import id.tech.cakra.ecfcoresvc.domain.CampaignCategory;
import id.tech.cakra.ecfcoresvc.repository.CampaignCategoryRepository;
import id.tech.cakra.ecfcoresvc.service.CampaignCategoryService;
import id.tech.cakra.ecfcoresvc.service.dto.CampaignCategoryDTO;
import id.tech.cakra.ecfcoresvc.service.mapper.CampaignCategoryMapper;
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
 * Integration tests for the {@link CampaignCategoryResource} REST controller.
 */
@SpringBootTest(classes = EcfcoresvcApp.class)
public class CampaignCategoryResourceIT {

    private static final Long DEFAULT_CATEGORY_ID = 1L;
    private static final Long UPDATED_CATEGORY_ID = 2L;

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
    private CampaignCategoryRepository campaignCategoryRepository;

    @Autowired
    private CampaignCategoryMapper campaignCategoryMapper;

    @Autowired
    private CampaignCategoryService campaignCategoryService;

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

    private MockMvc restCampaignCategoryMockMvc;

    private CampaignCategory campaignCategory;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CampaignCategoryResource campaignCategoryResource = new CampaignCategoryResource(campaignCategoryService);
        this.restCampaignCategoryMockMvc = MockMvcBuilders.standaloneSetup(campaignCategoryResource)
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
    public static CampaignCategory createEntity(EntityManager em) {
        CampaignCategory campaignCategory = new CampaignCategory()
            .categoryId(DEFAULT_CATEGORY_ID)
            .createSystemDate(DEFAULT_CREATE_SYSTEM_DATE)
            .createDate(DEFAULT_CREATE_DATE)
            .createUserId(DEFAULT_CREATE_USER_ID)
            .lastModificationSystemDate(DEFAULT_LAST_MODIFICATION_SYSTEM_DATE)
            .lastModificationDate(DEFAULT_LAST_MODIFICATION_DATE)
            .lastModificationUserId(DEFAULT_LAST_MODIFICATION_USER_ID);
        return campaignCategory;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CampaignCategory createUpdatedEntity(EntityManager em) {
        CampaignCategory campaignCategory = new CampaignCategory()
            .categoryId(UPDATED_CATEGORY_ID)
            .createSystemDate(UPDATED_CREATE_SYSTEM_DATE)
            .createDate(UPDATED_CREATE_DATE)
            .createUserId(UPDATED_CREATE_USER_ID)
            .lastModificationSystemDate(UPDATED_LAST_MODIFICATION_SYSTEM_DATE)
            .lastModificationDate(UPDATED_LAST_MODIFICATION_DATE)
            .lastModificationUserId(UPDATED_LAST_MODIFICATION_USER_ID);
        return campaignCategory;
    }

    @BeforeEach
    public void initTest() {
        campaignCategory = createEntity(em);
    }

    @Test
    @Transactional
    public void createCampaignCategory() throws Exception {
        int databaseSizeBeforeCreate = campaignCategoryRepository.findAll().size();

        // Create the CampaignCategory
        CampaignCategoryDTO campaignCategoryDTO = campaignCategoryMapper.toDto(campaignCategory);
        restCampaignCategoryMockMvc.perform(post("/api/campaign-categories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(campaignCategoryDTO)))
            .andExpect(status().isCreated());

        // Validate the CampaignCategory in the database
        List<CampaignCategory> campaignCategoryList = campaignCategoryRepository.findAll();
        assertThat(campaignCategoryList).hasSize(databaseSizeBeforeCreate + 1);
        CampaignCategory testCampaignCategory = campaignCategoryList.get(campaignCategoryList.size() - 1);
        assertThat(testCampaignCategory.getCategoryId()).isEqualTo(DEFAULT_CATEGORY_ID);
        assertThat(testCampaignCategory.getCreateSystemDate()).isEqualTo(DEFAULT_CREATE_SYSTEM_DATE);
        assertThat(testCampaignCategory.getCreateDate()).isEqualTo(DEFAULT_CREATE_DATE);
        assertThat(testCampaignCategory.getCreateUserId()).isEqualTo(DEFAULT_CREATE_USER_ID);
        assertThat(testCampaignCategory.getLastModificationSystemDate()).isEqualTo(DEFAULT_LAST_MODIFICATION_SYSTEM_DATE);
        assertThat(testCampaignCategory.getLastModificationDate()).isEqualTo(DEFAULT_LAST_MODIFICATION_DATE);
        assertThat(testCampaignCategory.getLastModificationUserId()).isEqualTo(DEFAULT_LAST_MODIFICATION_USER_ID);
    }

    @Test
    @Transactional
    public void createCampaignCategoryWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = campaignCategoryRepository.findAll().size();

        // Create the CampaignCategory with an existing ID
        campaignCategory.setId(1L);
        CampaignCategoryDTO campaignCategoryDTO = campaignCategoryMapper.toDto(campaignCategory);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCampaignCategoryMockMvc.perform(post("/api/campaign-categories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(campaignCategoryDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CampaignCategory in the database
        List<CampaignCategory> campaignCategoryList = campaignCategoryRepository.findAll();
        assertThat(campaignCategoryList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllCampaignCategories() throws Exception {
        // Initialize the database
        campaignCategoryRepository.saveAndFlush(campaignCategory);

        // Get all the campaignCategoryList
        restCampaignCategoryMockMvc.perform(get("/api/campaign-categories?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(campaignCategory.getId().intValue())))
            .andExpect(jsonPath("$.[*].categoryId").value(hasItem(DEFAULT_CATEGORY_ID.intValue())))
            .andExpect(jsonPath("$.[*].createSystemDate").value(hasItem(DEFAULT_CREATE_SYSTEM_DATE.toString())))
            .andExpect(jsonPath("$.[*].createDate").value(hasItem(sameInstant(DEFAULT_CREATE_DATE))))
            .andExpect(jsonPath("$.[*].createUserId").value(hasItem(DEFAULT_CREATE_USER_ID.intValue())))
            .andExpect(jsonPath("$.[*].lastModificationSystemDate").value(hasItem(DEFAULT_LAST_MODIFICATION_SYSTEM_DATE.toString())))
            .andExpect(jsonPath("$.[*].lastModificationDate").value(hasItem(sameInstant(DEFAULT_LAST_MODIFICATION_DATE))))
            .andExpect(jsonPath("$.[*].lastModificationUserId").value(hasItem(DEFAULT_LAST_MODIFICATION_USER_ID.intValue())));
    }
    
    @Test
    @Transactional
    public void getCampaignCategory() throws Exception {
        // Initialize the database
        campaignCategoryRepository.saveAndFlush(campaignCategory);

        // Get the campaignCategory
        restCampaignCategoryMockMvc.perform(get("/api/campaign-categories/{id}", campaignCategory.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(campaignCategory.getId().intValue()))
            .andExpect(jsonPath("$.categoryId").value(DEFAULT_CATEGORY_ID.intValue()))
            .andExpect(jsonPath("$.createSystemDate").value(DEFAULT_CREATE_SYSTEM_DATE.toString()))
            .andExpect(jsonPath("$.createDate").value(sameInstant(DEFAULT_CREATE_DATE)))
            .andExpect(jsonPath("$.createUserId").value(DEFAULT_CREATE_USER_ID.intValue()))
            .andExpect(jsonPath("$.lastModificationSystemDate").value(DEFAULT_LAST_MODIFICATION_SYSTEM_DATE.toString()))
            .andExpect(jsonPath("$.lastModificationDate").value(sameInstant(DEFAULT_LAST_MODIFICATION_DATE)))
            .andExpect(jsonPath("$.lastModificationUserId").value(DEFAULT_LAST_MODIFICATION_USER_ID.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingCampaignCategory() throws Exception {
        // Get the campaignCategory
        restCampaignCategoryMockMvc.perform(get("/api/campaign-categories/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCampaignCategory() throws Exception {
        // Initialize the database
        campaignCategoryRepository.saveAndFlush(campaignCategory);

        int databaseSizeBeforeUpdate = campaignCategoryRepository.findAll().size();

        // Update the campaignCategory
        CampaignCategory updatedCampaignCategory = campaignCategoryRepository.findById(campaignCategory.getId()).get();
        // Disconnect from session so that the updates on updatedCampaignCategory are not directly saved in db
        em.detach(updatedCampaignCategory);
        updatedCampaignCategory
            .categoryId(UPDATED_CATEGORY_ID)
            .createSystemDate(UPDATED_CREATE_SYSTEM_DATE)
            .createDate(UPDATED_CREATE_DATE)
            .createUserId(UPDATED_CREATE_USER_ID)
            .lastModificationSystemDate(UPDATED_LAST_MODIFICATION_SYSTEM_DATE)
            .lastModificationDate(UPDATED_LAST_MODIFICATION_DATE)
            .lastModificationUserId(UPDATED_LAST_MODIFICATION_USER_ID);
        CampaignCategoryDTO campaignCategoryDTO = campaignCategoryMapper.toDto(updatedCampaignCategory);

        restCampaignCategoryMockMvc.perform(put("/api/campaign-categories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(campaignCategoryDTO)))
            .andExpect(status().isOk());

        // Validate the CampaignCategory in the database
        List<CampaignCategory> campaignCategoryList = campaignCategoryRepository.findAll();
        assertThat(campaignCategoryList).hasSize(databaseSizeBeforeUpdate);
        CampaignCategory testCampaignCategory = campaignCategoryList.get(campaignCategoryList.size() - 1);
        assertThat(testCampaignCategory.getCategoryId()).isEqualTo(UPDATED_CATEGORY_ID);
        assertThat(testCampaignCategory.getCreateSystemDate()).isEqualTo(UPDATED_CREATE_SYSTEM_DATE);
        assertThat(testCampaignCategory.getCreateDate()).isEqualTo(UPDATED_CREATE_DATE);
        assertThat(testCampaignCategory.getCreateUserId()).isEqualTo(UPDATED_CREATE_USER_ID);
        assertThat(testCampaignCategory.getLastModificationSystemDate()).isEqualTo(UPDATED_LAST_MODIFICATION_SYSTEM_DATE);
        assertThat(testCampaignCategory.getLastModificationDate()).isEqualTo(UPDATED_LAST_MODIFICATION_DATE);
        assertThat(testCampaignCategory.getLastModificationUserId()).isEqualTo(UPDATED_LAST_MODIFICATION_USER_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingCampaignCategory() throws Exception {
        int databaseSizeBeforeUpdate = campaignCategoryRepository.findAll().size();

        // Create the CampaignCategory
        CampaignCategoryDTO campaignCategoryDTO = campaignCategoryMapper.toDto(campaignCategory);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCampaignCategoryMockMvc.perform(put("/api/campaign-categories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(campaignCategoryDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CampaignCategory in the database
        List<CampaignCategory> campaignCategoryList = campaignCategoryRepository.findAll();
        assertThat(campaignCategoryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCampaignCategory() throws Exception {
        // Initialize the database
        campaignCategoryRepository.saveAndFlush(campaignCategory);

        int databaseSizeBeforeDelete = campaignCategoryRepository.findAll().size();

        // Delete the campaignCategory
        restCampaignCategoryMockMvc.perform(delete("/api/campaign-categories/{id}", campaignCategory.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CampaignCategory> campaignCategoryList = campaignCategoryRepository.findAll();
        assertThat(campaignCategoryList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CampaignCategory.class);
        CampaignCategory campaignCategory1 = new CampaignCategory();
        campaignCategory1.setId(1L);
        CampaignCategory campaignCategory2 = new CampaignCategory();
        campaignCategory2.setId(campaignCategory1.getId());
        assertThat(campaignCategory1).isEqualTo(campaignCategory2);
        campaignCategory2.setId(2L);
        assertThat(campaignCategory1).isNotEqualTo(campaignCategory2);
        campaignCategory1.setId(null);
        assertThat(campaignCategory1).isNotEqualTo(campaignCategory2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CampaignCategoryDTO.class);
        CampaignCategoryDTO campaignCategoryDTO1 = new CampaignCategoryDTO();
        campaignCategoryDTO1.setId(1L);
        CampaignCategoryDTO campaignCategoryDTO2 = new CampaignCategoryDTO();
        assertThat(campaignCategoryDTO1).isNotEqualTo(campaignCategoryDTO2);
        campaignCategoryDTO2.setId(campaignCategoryDTO1.getId());
        assertThat(campaignCategoryDTO1).isEqualTo(campaignCategoryDTO2);
        campaignCategoryDTO2.setId(2L);
        assertThat(campaignCategoryDTO1).isNotEqualTo(campaignCategoryDTO2);
        campaignCategoryDTO1.setId(null);
        assertThat(campaignCategoryDTO1).isNotEqualTo(campaignCategoryDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(campaignCategoryMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(campaignCategoryMapper.fromId(null)).isNull();
    }
}
