package com.ncs.iconnect.sample.lab.generated.web.rest;

import com.ncs.iconnect.sample.lab.generated.IconnectSampleAppLabApp;
import com.ncs.iconnect.sample.lab.generated.domain.Ward;
import com.ncs.iconnect.sample.lab.generated.domain.Bed;
import com.ncs.iconnect.sample.lab.generated.repository.WardRepository;
import com.ncs.iconnect.sample.lab.generated.service.WardService;
import com.ncs.iconnect.sample.lab.generated.service.dto.WardDTO;
import com.ncs.iconnect.sample.lab.generated.service.mapper.WardMapper;
import com.ncs.iconnect.sample.lab.generated.service.dto.WardCriteria;
import com.ncs.iconnect.sample.lab.generated.service.WardQueryService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.ncs.iconnect.sample.lab.generated.domain.enumeration.WardClassType;
import com.ncs.iconnect.sample.lab.generated.domain.enumeration.WardLocation;
/**
 * Integration tests for the {@link WardResource} REST controller.
 */
@SpringBootTest(classes = IconnectSampleAppLabApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class WardResourceIT {

    private static final String DEFAULT_WARD_REFERENCE_ID = "AAAAAAAAAA";
    private static final String UPDATED_WARD_REFERENCE_ID = "BBBBBBBBBB";

    private static final String DEFAULT_WARD_NAME = "AAAAAAAAAA";
    private static final String UPDATED_WARD_NAME = "BBBBBBBBBB";

    private static final WardClassType DEFAULT_WARD_CLASS_TYPE = WardClassType.A;
    private static final WardClassType UPDATED_WARD_CLASS_TYPE = WardClassType.B;

    private static final WardLocation DEFAULT_WARD_LOCATION = WardLocation.A1;
    private static final WardLocation UPDATED_WARD_LOCATION = WardLocation.A2;

    @Autowired
    private WardRepository wardRepository;

    @Autowired
    private WardMapper wardMapper;

    @Autowired
    private WardService wardService;

    @Autowired
    private WardQueryService wardQueryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restWardMockMvc;

    private Ward ward;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Ward createEntity(EntityManager em) {
        Ward ward = new Ward()
            .wardReferenceId(DEFAULT_WARD_REFERENCE_ID)
            .wardName(DEFAULT_WARD_NAME)
            .wardClassType(DEFAULT_WARD_CLASS_TYPE)
            .wardLocation(DEFAULT_WARD_LOCATION);
        return ward;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Ward createUpdatedEntity(EntityManager em) {
        Ward ward = new Ward()
            .wardReferenceId(UPDATED_WARD_REFERENCE_ID)
            .wardName(UPDATED_WARD_NAME)
            .wardClassType(UPDATED_WARD_CLASS_TYPE)
            .wardLocation(UPDATED_WARD_LOCATION);
        return ward;
    }

    @BeforeEach
    public void initTest() {
        ward = createEntity(em);
    }

    @Test
    @Transactional
    public void createWard() throws Exception {
        int databaseSizeBeforeCreate = wardRepository.findAll().size();

        // Create the Ward
        WardDTO wardDTO = wardMapper.toDto(ward);
        restWardMockMvc.perform(post("/api/wards")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(wardDTO)))
            .andExpect(status().isCreated());

        // Validate the Ward in the database
        List<Ward> wardList = wardRepository.findAll();
        assertThat(wardList).hasSize(databaseSizeBeforeCreate + 1);
        Ward testWard = wardList.get(wardList.size() - 1);
        assertThat(testWard.getWardReferenceId()).isEqualTo(DEFAULT_WARD_REFERENCE_ID);
        assertThat(testWard.getWardName()).isEqualTo(DEFAULT_WARD_NAME);
        assertThat(testWard.getWardClassType()).isEqualTo(DEFAULT_WARD_CLASS_TYPE);
        assertThat(testWard.getWardLocation()).isEqualTo(DEFAULT_WARD_LOCATION);
    }

    @Test
    @Transactional
    public void createWardWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = wardRepository.findAll().size();

        // Create the Ward with an existing ID
        ward.setId(1L);
        WardDTO wardDTO = wardMapper.toDto(ward);

        // An entity with an existing ID cannot be created, so this API call must fail
        restWardMockMvc.perform(post("/api/wards")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(wardDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Ward in the database
        List<Ward> wardList = wardRepository.findAll();
        assertThat(wardList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkWardClassTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = wardRepository.findAll().size();
        // set the field null
        ward.setWardClassType(null);

        // Create the Ward, which fails.
        WardDTO wardDTO = wardMapper.toDto(ward);

        restWardMockMvc.perform(post("/api/wards")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(wardDTO)))
            .andExpect(status().isBadRequest());

        List<Ward> wardList = wardRepository.findAll();
        assertThat(wardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkWardLocationIsRequired() throws Exception {
        int databaseSizeBeforeTest = wardRepository.findAll().size();
        // set the field null
        ward.setWardLocation(null);

        // Create the Ward, which fails.
        WardDTO wardDTO = wardMapper.toDto(ward);

        restWardMockMvc.perform(post("/api/wards")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(wardDTO)))
            .andExpect(status().isBadRequest());

        List<Ward> wardList = wardRepository.findAll();
        assertThat(wardList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllWards() throws Exception {
        // Initialize the database
        wardRepository.saveAndFlush(ward);

        // Get all the wardList
        restWardMockMvc.perform(get("/api/wards?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(ward.getId().intValue())))
            .andExpect(jsonPath("$.[*].wardReferenceId").value(hasItem(DEFAULT_WARD_REFERENCE_ID)))
            .andExpect(jsonPath("$.[*].wardName").value(hasItem(DEFAULT_WARD_NAME)))
            .andExpect(jsonPath("$.[*].wardClassType").value(hasItem(DEFAULT_WARD_CLASS_TYPE.toString())))
            .andExpect(jsonPath("$.[*].wardLocation").value(hasItem(DEFAULT_WARD_LOCATION.toString())));
    }
    
    @Test
    @Transactional
    public void getWard() throws Exception {
        // Initialize the database
        wardRepository.saveAndFlush(ward);

        // Get the ward
        restWardMockMvc.perform(get("/api/wards/{id}", ward.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(ward.getId().intValue()))
            .andExpect(jsonPath("$.wardReferenceId").value(DEFAULT_WARD_REFERENCE_ID))
            .andExpect(jsonPath("$.wardName").value(DEFAULT_WARD_NAME))
            .andExpect(jsonPath("$.wardClassType").value(DEFAULT_WARD_CLASS_TYPE.toString()))
            .andExpect(jsonPath("$.wardLocation").value(DEFAULT_WARD_LOCATION.toString()));
    }


    @Test
    @Transactional
    public void getWardsByIdFiltering() throws Exception {
        // Initialize the database
        wardRepository.saveAndFlush(ward);

        Long id = ward.getId();

        defaultWardShouldBeFound("id.equals=" + id);
        defaultWardShouldNotBeFound("id.notEquals=" + id);

        defaultWardShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultWardShouldNotBeFound("id.greaterThan=" + id);

        defaultWardShouldBeFound("id.lessThanOrEqual=" + id);
        defaultWardShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllWardsByWardReferenceIdIsEqualToSomething() throws Exception {
        // Initialize the database
        wardRepository.saveAndFlush(ward);

        // Get all the wardList where wardReferenceId equals to DEFAULT_WARD_REFERENCE_ID
        defaultWardShouldBeFound("wardReferenceId.equals=" + DEFAULT_WARD_REFERENCE_ID);

        // Get all the wardList where wardReferenceId equals to UPDATED_WARD_REFERENCE_ID
        defaultWardShouldNotBeFound("wardReferenceId.equals=" + UPDATED_WARD_REFERENCE_ID);
    }

    @Test
    @Transactional
    public void getAllWardsByWardReferenceIdIsNotEqualToSomething() throws Exception {
        // Initialize the database
        wardRepository.saveAndFlush(ward);

        // Get all the wardList where wardReferenceId not equals to DEFAULT_WARD_REFERENCE_ID
        defaultWardShouldNotBeFound("wardReferenceId.notEquals=" + DEFAULT_WARD_REFERENCE_ID);

        // Get all the wardList where wardReferenceId not equals to UPDATED_WARD_REFERENCE_ID
        defaultWardShouldBeFound("wardReferenceId.notEquals=" + UPDATED_WARD_REFERENCE_ID);
    }

    @Test
    @Transactional
    public void getAllWardsByWardReferenceIdIsInShouldWork() throws Exception {
        // Initialize the database
        wardRepository.saveAndFlush(ward);

        // Get all the wardList where wardReferenceId in DEFAULT_WARD_REFERENCE_ID or UPDATED_WARD_REFERENCE_ID
        defaultWardShouldBeFound("wardReferenceId.in=" + DEFAULT_WARD_REFERENCE_ID + "," + UPDATED_WARD_REFERENCE_ID);

        // Get all the wardList where wardReferenceId equals to UPDATED_WARD_REFERENCE_ID
        defaultWardShouldNotBeFound("wardReferenceId.in=" + UPDATED_WARD_REFERENCE_ID);
    }

    @Test
    @Transactional
    public void getAllWardsByWardReferenceIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        wardRepository.saveAndFlush(ward);

        // Get all the wardList where wardReferenceId is not null
        defaultWardShouldBeFound("wardReferenceId.specified=true");

        // Get all the wardList where wardReferenceId is null
        defaultWardShouldNotBeFound("wardReferenceId.specified=false");
    }
                @Test
    @Transactional
    public void getAllWardsByWardReferenceIdContainsSomething() throws Exception {
        // Initialize the database
        wardRepository.saveAndFlush(ward);

        // Get all the wardList where wardReferenceId contains DEFAULT_WARD_REFERENCE_ID
        defaultWardShouldBeFound("wardReferenceId.contains=" + DEFAULT_WARD_REFERENCE_ID);

        // Get all the wardList where wardReferenceId contains UPDATED_WARD_REFERENCE_ID
        defaultWardShouldNotBeFound("wardReferenceId.contains=" + UPDATED_WARD_REFERENCE_ID);
    }

    @Test
    @Transactional
    public void getAllWardsByWardReferenceIdNotContainsSomething() throws Exception {
        // Initialize the database
        wardRepository.saveAndFlush(ward);

        // Get all the wardList where wardReferenceId does not contain DEFAULT_WARD_REFERENCE_ID
        defaultWardShouldNotBeFound("wardReferenceId.doesNotContain=" + DEFAULT_WARD_REFERENCE_ID);

        // Get all the wardList where wardReferenceId does not contain UPDATED_WARD_REFERENCE_ID
        defaultWardShouldBeFound("wardReferenceId.doesNotContain=" + UPDATED_WARD_REFERENCE_ID);
    }


    @Test
    @Transactional
    public void getAllWardsByWardNameIsEqualToSomething() throws Exception {
        // Initialize the database
        wardRepository.saveAndFlush(ward);

        // Get all the wardList where wardName equals to DEFAULT_WARD_NAME
        defaultWardShouldBeFound("wardName.equals=" + DEFAULT_WARD_NAME);

        // Get all the wardList where wardName equals to UPDATED_WARD_NAME
        defaultWardShouldNotBeFound("wardName.equals=" + UPDATED_WARD_NAME);
    }

    @Test
    @Transactional
    public void getAllWardsByWardNameIsNotEqualToSomething() throws Exception {
        // Initialize the database
        wardRepository.saveAndFlush(ward);

        // Get all the wardList where wardName not equals to DEFAULT_WARD_NAME
        defaultWardShouldNotBeFound("wardName.notEquals=" + DEFAULT_WARD_NAME);

        // Get all the wardList where wardName not equals to UPDATED_WARD_NAME
        defaultWardShouldBeFound("wardName.notEquals=" + UPDATED_WARD_NAME);
    }

    @Test
    @Transactional
    public void getAllWardsByWardNameIsInShouldWork() throws Exception {
        // Initialize the database
        wardRepository.saveAndFlush(ward);

        // Get all the wardList where wardName in DEFAULT_WARD_NAME or UPDATED_WARD_NAME
        defaultWardShouldBeFound("wardName.in=" + DEFAULT_WARD_NAME + "," + UPDATED_WARD_NAME);

        // Get all the wardList where wardName equals to UPDATED_WARD_NAME
        defaultWardShouldNotBeFound("wardName.in=" + UPDATED_WARD_NAME);
    }

    @Test
    @Transactional
    public void getAllWardsByWardNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        wardRepository.saveAndFlush(ward);

        // Get all the wardList where wardName is not null
        defaultWardShouldBeFound("wardName.specified=true");

        // Get all the wardList where wardName is null
        defaultWardShouldNotBeFound("wardName.specified=false");
    }
                @Test
    @Transactional
    public void getAllWardsByWardNameContainsSomething() throws Exception {
        // Initialize the database
        wardRepository.saveAndFlush(ward);

        // Get all the wardList where wardName contains DEFAULT_WARD_NAME
        defaultWardShouldBeFound("wardName.contains=" + DEFAULT_WARD_NAME);

        // Get all the wardList where wardName contains UPDATED_WARD_NAME
        defaultWardShouldNotBeFound("wardName.contains=" + UPDATED_WARD_NAME);
    }

    @Test
    @Transactional
    public void getAllWardsByWardNameNotContainsSomething() throws Exception {
        // Initialize the database
        wardRepository.saveAndFlush(ward);

        // Get all the wardList where wardName does not contain DEFAULT_WARD_NAME
        defaultWardShouldNotBeFound("wardName.doesNotContain=" + DEFAULT_WARD_NAME);

        // Get all the wardList where wardName does not contain UPDATED_WARD_NAME
        defaultWardShouldBeFound("wardName.doesNotContain=" + UPDATED_WARD_NAME);
    }


    @Test
    @Transactional
    public void getAllWardsByWardClassTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        wardRepository.saveAndFlush(ward);

        // Get all the wardList where wardClassType equals to DEFAULT_WARD_CLASS_TYPE
        defaultWardShouldBeFound("wardClassType.equals=" + DEFAULT_WARD_CLASS_TYPE);

        // Get all the wardList where wardClassType equals to UPDATED_WARD_CLASS_TYPE
        defaultWardShouldNotBeFound("wardClassType.equals=" + UPDATED_WARD_CLASS_TYPE);
    }

    @Test
    @Transactional
    public void getAllWardsByWardClassTypeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        wardRepository.saveAndFlush(ward);

        // Get all the wardList where wardClassType not equals to DEFAULT_WARD_CLASS_TYPE
        defaultWardShouldNotBeFound("wardClassType.notEquals=" + DEFAULT_WARD_CLASS_TYPE);

        // Get all the wardList where wardClassType not equals to UPDATED_WARD_CLASS_TYPE
        defaultWardShouldBeFound("wardClassType.notEquals=" + UPDATED_WARD_CLASS_TYPE);
    }

    @Test
    @Transactional
    public void getAllWardsByWardClassTypeIsInShouldWork() throws Exception {
        // Initialize the database
        wardRepository.saveAndFlush(ward);

        // Get all the wardList where wardClassType in DEFAULT_WARD_CLASS_TYPE or UPDATED_WARD_CLASS_TYPE
        defaultWardShouldBeFound("wardClassType.in=" + DEFAULT_WARD_CLASS_TYPE + "," + UPDATED_WARD_CLASS_TYPE);

        // Get all the wardList where wardClassType equals to UPDATED_WARD_CLASS_TYPE
        defaultWardShouldNotBeFound("wardClassType.in=" + UPDATED_WARD_CLASS_TYPE);
    }

    @Test
    @Transactional
    public void getAllWardsByWardClassTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        wardRepository.saveAndFlush(ward);

        // Get all the wardList where wardClassType is not null
        defaultWardShouldBeFound("wardClassType.specified=true");

        // Get all the wardList where wardClassType is null
        defaultWardShouldNotBeFound("wardClassType.specified=false");
    }

    @Test
    @Transactional
    public void getAllWardsByWardLocationIsEqualToSomething() throws Exception {
        // Initialize the database
        wardRepository.saveAndFlush(ward);

        // Get all the wardList where wardLocation equals to DEFAULT_WARD_LOCATION
        defaultWardShouldBeFound("wardLocation.equals=" + DEFAULT_WARD_LOCATION);

        // Get all the wardList where wardLocation equals to UPDATED_WARD_LOCATION
        defaultWardShouldNotBeFound("wardLocation.equals=" + UPDATED_WARD_LOCATION);
    }

    @Test
    @Transactional
    public void getAllWardsByWardLocationIsNotEqualToSomething() throws Exception {
        // Initialize the database
        wardRepository.saveAndFlush(ward);

        // Get all the wardList where wardLocation not equals to DEFAULT_WARD_LOCATION
        defaultWardShouldNotBeFound("wardLocation.notEquals=" + DEFAULT_WARD_LOCATION);

        // Get all the wardList where wardLocation not equals to UPDATED_WARD_LOCATION
        defaultWardShouldBeFound("wardLocation.notEquals=" + UPDATED_WARD_LOCATION);
    }

    @Test
    @Transactional
    public void getAllWardsByWardLocationIsInShouldWork() throws Exception {
        // Initialize the database
        wardRepository.saveAndFlush(ward);

        // Get all the wardList where wardLocation in DEFAULT_WARD_LOCATION or UPDATED_WARD_LOCATION
        defaultWardShouldBeFound("wardLocation.in=" + DEFAULT_WARD_LOCATION + "," + UPDATED_WARD_LOCATION);

        // Get all the wardList where wardLocation equals to UPDATED_WARD_LOCATION
        defaultWardShouldNotBeFound("wardLocation.in=" + UPDATED_WARD_LOCATION);
    }

    @Test
    @Transactional
    public void getAllWardsByWardLocationIsNullOrNotNull() throws Exception {
        // Initialize the database
        wardRepository.saveAndFlush(ward);

        // Get all the wardList where wardLocation is not null
        defaultWardShouldBeFound("wardLocation.specified=true");

        // Get all the wardList where wardLocation is null
        defaultWardShouldNotBeFound("wardLocation.specified=false");
    }

    @Test
    @Transactional
    public void getAllWardsByBedIsEqualToSomething() throws Exception {
        // Initialize the database
        wardRepository.saveAndFlush(ward);
        Bed bed = BedResourceIT.createEntity(em);
        em.persist(bed);
        em.flush();
        ward.addBed(bed);
        wardRepository.saveAndFlush(ward);
        Long bedId = bed.getId();

        // Get all the wardList where bed equals to bedId
        defaultWardShouldBeFound("bedId.equals=" + bedId);

        // Get all the wardList where bed equals to bedId + 1
        defaultWardShouldNotBeFound("bedId.equals=" + (bedId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultWardShouldBeFound(String filter) throws Exception {
        restWardMockMvc.perform(get("/api/wards?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(ward.getId().intValue())))
            .andExpect(jsonPath("$.[*].wardReferenceId").value(hasItem(DEFAULT_WARD_REFERENCE_ID)))
            .andExpect(jsonPath("$.[*].wardName").value(hasItem(DEFAULT_WARD_NAME)))
            .andExpect(jsonPath("$.[*].wardClassType").value(hasItem(DEFAULT_WARD_CLASS_TYPE.toString())))
            .andExpect(jsonPath("$.[*].wardLocation").value(hasItem(DEFAULT_WARD_LOCATION.toString())));

        // Check, that the count call also returns 1
        restWardMockMvc.perform(get("/api/wards/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultWardShouldNotBeFound(String filter) throws Exception {
        restWardMockMvc.perform(get("/api/wards?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restWardMockMvc.perform(get("/api/wards/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingWard() throws Exception {
        // Get the ward
        restWardMockMvc.perform(get("/api/wards/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateWard() throws Exception {
        // Initialize the database
        wardRepository.saveAndFlush(ward);

        int databaseSizeBeforeUpdate = wardRepository.findAll().size();

        // Update the ward
        Ward updatedWard = wardRepository.findById(ward.getId()).get();
        // Disconnect from session so that the updates on updatedWard are not directly saved in db
        em.detach(updatedWard);
        updatedWard
            .wardReferenceId(UPDATED_WARD_REFERENCE_ID)
            .wardName(UPDATED_WARD_NAME)
            .wardClassType(UPDATED_WARD_CLASS_TYPE)
            .wardLocation(UPDATED_WARD_LOCATION);
        WardDTO wardDTO = wardMapper.toDto(updatedWard);

        restWardMockMvc.perform(put("/api/wards")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(wardDTO)))
            .andExpect(status().isOk());

        // Validate the Ward in the database
        List<Ward> wardList = wardRepository.findAll();
        assertThat(wardList).hasSize(databaseSizeBeforeUpdate);
        Ward testWard = wardList.get(wardList.size() - 1);
        assertThat(testWard.getWardReferenceId()).isEqualTo(UPDATED_WARD_REFERENCE_ID);
        assertThat(testWard.getWardName()).isEqualTo(UPDATED_WARD_NAME);
        assertThat(testWard.getWardClassType()).isEqualTo(UPDATED_WARD_CLASS_TYPE);
        assertThat(testWard.getWardLocation()).isEqualTo(UPDATED_WARD_LOCATION);
    }

    @Test
    @Transactional
    public void updateNonExistingWard() throws Exception {
        int databaseSizeBeforeUpdate = wardRepository.findAll().size();

        // Create the Ward
        WardDTO wardDTO = wardMapper.toDto(ward);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restWardMockMvc.perform(put("/api/wards")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(wardDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Ward in the database
        List<Ward> wardList = wardRepository.findAll();
        assertThat(wardList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteWard() throws Exception {
        // Initialize the database
        wardRepository.saveAndFlush(ward);

        int databaseSizeBeforeDelete = wardRepository.findAll().size();

        // Delete the ward
        restWardMockMvc.perform(delete("/api/wards/{id}", ward.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Ward> wardList = wardRepository.findAll();
        assertThat(wardList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
