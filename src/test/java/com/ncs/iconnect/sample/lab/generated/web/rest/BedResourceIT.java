package com.ncs.iconnect.sample.lab.generated.web.rest;

import com.ncs.iconnect.sample.lab.generated.IconnectSampleAppLabApp;
import com.ncs.iconnect.sample.lab.generated.domain.Bed;
import com.ncs.iconnect.sample.lab.generated.domain.Ward;
import com.ncs.iconnect.sample.lab.generated.repository.BedRepository;
import com.ncs.iconnect.sample.lab.generated.service.BedService;
import com.ncs.iconnect.sample.lab.generated.service.dto.BedDTO;
import com.ncs.iconnect.sample.lab.generated.service.mapper.BedMapper;
import com.ncs.iconnect.sample.lab.generated.service.dto.BedCriteria;
import com.ncs.iconnect.sample.lab.generated.service.BedQueryService;

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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link BedResource} REST controller.
 */
@SpringBootTest(classes = IconnectSampleAppLabApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class BedResourceIT {

    private static final String DEFAULT_BED_REFERENCE_ID = "AAAAAAAAAA";
    private static final String UPDATED_BED_REFERENCE_ID = "BBBBBBBBBB";

    private static final String DEFAULT_BED_NAME = "AAAAAAAAAA";
    private static final String UPDATED_BED_NAME = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_WARD_ALLOCATION_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_WARD_ALLOCATION_DATE = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_WARD_ALLOCATION_DATE = LocalDate.ofEpochDay(-1L);

    @Autowired
    private BedRepository bedRepository;

    @Autowired
    private BedMapper bedMapper;

    @Autowired
    private BedService bedService;

    @Autowired
    private BedQueryService bedQueryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restBedMockMvc;

    private Bed bed;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Bed createEntity(EntityManager em) {
        Bed bed = new Bed()
            .bedReferenceId(DEFAULT_BED_REFERENCE_ID)
            .bedName(DEFAULT_BED_NAME)
            .wardAllocationDate(DEFAULT_WARD_ALLOCATION_DATE);
        return bed;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Bed createUpdatedEntity(EntityManager em) {
        Bed bed = new Bed()
            .bedReferenceId(UPDATED_BED_REFERENCE_ID)
            .bedName(UPDATED_BED_NAME)
            .wardAllocationDate(UPDATED_WARD_ALLOCATION_DATE);
        return bed;
    }

    @BeforeEach
    public void initTest() {
        bed = createEntity(em);
    }

    @Test
    @Transactional
    public void createBed() throws Exception {
        int databaseSizeBeforeCreate = bedRepository.findAll().size();

        // Create the Bed
        BedDTO bedDTO = bedMapper.toDto(bed);
        restBedMockMvc.perform(post("/api/beds")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bedDTO)))
            .andExpect(status().isCreated());

        // Validate the Bed in the database
        List<Bed> bedList = bedRepository.findAll();
        assertThat(bedList).hasSize(databaseSizeBeforeCreate + 1);
        Bed testBed = bedList.get(bedList.size() - 1);
        assertThat(testBed.getBedReferenceId()).isEqualTo(DEFAULT_BED_REFERENCE_ID);
        assertThat(testBed.getBedName()).isEqualTo(DEFAULT_BED_NAME);
        assertThat(testBed.getWardAllocationDate()).isEqualTo(DEFAULT_WARD_ALLOCATION_DATE);
    }

    @Test
    @Transactional
    public void createBedWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = bedRepository.findAll().size();

        // Create the Bed with an existing ID
        bed.setId(1L);
        BedDTO bedDTO = bedMapper.toDto(bed);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBedMockMvc.perform(post("/api/beds")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bedDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Bed in the database
        List<Bed> bedList = bedRepository.findAll();
        assertThat(bedList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkBedReferenceIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = bedRepository.findAll().size();
        // set the field null
        bed.setBedReferenceId(null);

        // Create the Bed, which fails.
        BedDTO bedDTO = bedMapper.toDto(bed);

        restBedMockMvc.perform(post("/api/beds")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bedDTO)))
            .andExpect(status().isBadRequest());

        List<Bed> bedList = bedRepository.findAll();
        assertThat(bedList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkWardAllocationDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = bedRepository.findAll().size();
        // set the field null
        bed.setWardAllocationDate(null);

        // Create the Bed, which fails.
        BedDTO bedDTO = bedMapper.toDto(bed);

        restBedMockMvc.perform(post("/api/beds")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bedDTO)))
            .andExpect(status().isBadRequest());

        List<Bed> bedList = bedRepository.findAll();
        assertThat(bedList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllBeds() throws Exception {
        // Initialize the database
        bedRepository.saveAndFlush(bed);

        // Get all the bedList
        restBedMockMvc.perform(get("/api/beds?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(bed.getId().intValue())))
            .andExpect(jsonPath("$.[*].bedReferenceId").value(hasItem(DEFAULT_BED_REFERENCE_ID)))
            .andExpect(jsonPath("$.[*].bedName").value(hasItem(DEFAULT_BED_NAME)))
            .andExpect(jsonPath("$.[*].wardAllocationDate").value(hasItem(DEFAULT_WARD_ALLOCATION_DATE.toString())));
    }
    
    @Test
    @Transactional
    public void getBed() throws Exception {
        // Initialize the database
        bedRepository.saveAndFlush(bed);

        // Get the bed
        restBedMockMvc.perform(get("/api/beds/{id}", bed.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(bed.getId().intValue()))
            .andExpect(jsonPath("$.bedReferenceId").value(DEFAULT_BED_REFERENCE_ID))
            .andExpect(jsonPath("$.bedName").value(DEFAULT_BED_NAME))
            .andExpect(jsonPath("$.wardAllocationDate").value(DEFAULT_WARD_ALLOCATION_DATE.toString()));
    }


    @Test
    @Transactional
    public void getBedsByIdFiltering() throws Exception {
        // Initialize the database
        bedRepository.saveAndFlush(bed);

        Long id = bed.getId();

        defaultBedShouldBeFound("id.equals=" + id);
        defaultBedShouldNotBeFound("id.notEquals=" + id);

        defaultBedShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultBedShouldNotBeFound("id.greaterThan=" + id);

        defaultBedShouldBeFound("id.lessThanOrEqual=" + id);
        defaultBedShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllBedsByBedReferenceIdIsEqualToSomething() throws Exception {
        // Initialize the database
        bedRepository.saveAndFlush(bed);

        // Get all the bedList where bedReferenceId equals to DEFAULT_BED_REFERENCE_ID
        defaultBedShouldBeFound("bedReferenceId.equals=" + DEFAULT_BED_REFERENCE_ID);

        // Get all the bedList where bedReferenceId equals to UPDATED_BED_REFERENCE_ID
        defaultBedShouldNotBeFound("bedReferenceId.equals=" + UPDATED_BED_REFERENCE_ID);
    }

    @Test
    @Transactional
    public void getAllBedsByBedReferenceIdIsNotEqualToSomething() throws Exception {
        // Initialize the database
        bedRepository.saveAndFlush(bed);

        // Get all the bedList where bedReferenceId not equals to DEFAULT_BED_REFERENCE_ID
        defaultBedShouldNotBeFound("bedReferenceId.notEquals=" + DEFAULT_BED_REFERENCE_ID);

        // Get all the bedList where bedReferenceId not equals to UPDATED_BED_REFERENCE_ID
        defaultBedShouldBeFound("bedReferenceId.notEquals=" + UPDATED_BED_REFERENCE_ID);
    }

    @Test
    @Transactional
    public void getAllBedsByBedReferenceIdIsInShouldWork() throws Exception {
        // Initialize the database
        bedRepository.saveAndFlush(bed);

        // Get all the bedList where bedReferenceId in DEFAULT_BED_REFERENCE_ID or UPDATED_BED_REFERENCE_ID
        defaultBedShouldBeFound("bedReferenceId.in=" + DEFAULT_BED_REFERENCE_ID + "," + UPDATED_BED_REFERENCE_ID);

        // Get all the bedList where bedReferenceId equals to UPDATED_BED_REFERENCE_ID
        defaultBedShouldNotBeFound("bedReferenceId.in=" + UPDATED_BED_REFERENCE_ID);
    }

    @Test
    @Transactional
    public void getAllBedsByBedReferenceIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        bedRepository.saveAndFlush(bed);

        // Get all the bedList where bedReferenceId is not null
        defaultBedShouldBeFound("bedReferenceId.specified=true");

        // Get all the bedList where bedReferenceId is null
        defaultBedShouldNotBeFound("bedReferenceId.specified=false");
    }
                @Test
    @Transactional
    public void getAllBedsByBedReferenceIdContainsSomething() throws Exception {
        // Initialize the database
        bedRepository.saveAndFlush(bed);

        // Get all the bedList where bedReferenceId contains DEFAULT_BED_REFERENCE_ID
        defaultBedShouldBeFound("bedReferenceId.contains=" + DEFAULT_BED_REFERENCE_ID);

        // Get all the bedList where bedReferenceId contains UPDATED_BED_REFERENCE_ID
        defaultBedShouldNotBeFound("bedReferenceId.contains=" + UPDATED_BED_REFERENCE_ID);
    }

    @Test
    @Transactional
    public void getAllBedsByBedReferenceIdNotContainsSomething() throws Exception {
        // Initialize the database
        bedRepository.saveAndFlush(bed);

        // Get all the bedList where bedReferenceId does not contain DEFAULT_BED_REFERENCE_ID
        defaultBedShouldNotBeFound("bedReferenceId.doesNotContain=" + DEFAULT_BED_REFERENCE_ID);

        // Get all the bedList where bedReferenceId does not contain UPDATED_BED_REFERENCE_ID
        defaultBedShouldBeFound("bedReferenceId.doesNotContain=" + UPDATED_BED_REFERENCE_ID);
    }


    @Test
    @Transactional
    public void getAllBedsByBedNameIsEqualToSomething() throws Exception {
        // Initialize the database
        bedRepository.saveAndFlush(bed);

        // Get all the bedList where bedName equals to DEFAULT_BED_NAME
        defaultBedShouldBeFound("bedName.equals=" + DEFAULT_BED_NAME);

        // Get all the bedList where bedName equals to UPDATED_BED_NAME
        defaultBedShouldNotBeFound("bedName.equals=" + UPDATED_BED_NAME);
    }

    @Test
    @Transactional
    public void getAllBedsByBedNameIsNotEqualToSomething() throws Exception {
        // Initialize the database
        bedRepository.saveAndFlush(bed);

        // Get all the bedList where bedName not equals to DEFAULT_BED_NAME
        defaultBedShouldNotBeFound("bedName.notEquals=" + DEFAULT_BED_NAME);

        // Get all the bedList where bedName not equals to UPDATED_BED_NAME
        defaultBedShouldBeFound("bedName.notEquals=" + UPDATED_BED_NAME);
    }

    @Test
    @Transactional
    public void getAllBedsByBedNameIsInShouldWork() throws Exception {
        // Initialize the database
        bedRepository.saveAndFlush(bed);

        // Get all the bedList where bedName in DEFAULT_BED_NAME or UPDATED_BED_NAME
        defaultBedShouldBeFound("bedName.in=" + DEFAULT_BED_NAME + "," + UPDATED_BED_NAME);

        // Get all the bedList where bedName equals to UPDATED_BED_NAME
        defaultBedShouldNotBeFound("bedName.in=" + UPDATED_BED_NAME);
    }

    @Test
    @Transactional
    public void getAllBedsByBedNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        bedRepository.saveAndFlush(bed);

        // Get all the bedList where bedName is not null
        defaultBedShouldBeFound("bedName.specified=true");

        // Get all the bedList where bedName is null
        defaultBedShouldNotBeFound("bedName.specified=false");
    }
                @Test
    @Transactional
    public void getAllBedsByBedNameContainsSomething() throws Exception {
        // Initialize the database
        bedRepository.saveAndFlush(bed);

        // Get all the bedList where bedName contains DEFAULT_BED_NAME
        defaultBedShouldBeFound("bedName.contains=" + DEFAULT_BED_NAME);

        // Get all the bedList where bedName contains UPDATED_BED_NAME
        defaultBedShouldNotBeFound("bedName.contains=" + UPDATED_BED_NAME);
    }

    @Test
    @Transactional
    public void getAllBedsByBedNameNotContainsSomething() throws Exception {
        // Initialize the database
        bedRepository.saveAndFlush(bed);

        // Get all the bedList where bedName does not contain DEFAULT_BED_NAME
        defaultBedShouldNotBeFound("bedName.doesNotContain=" + DEFAULT_BED_NAME);

        // Get all the bedList where bedName does not contain UPDATED_BED_NAME
        defaultBedShouldBeFound("bedName.doesNotContain=" + UPDATED_BED_NAME);
    }


    @Test
    @Transactional
    public void getAllBedsByWardAllocationDateIsEqualToSomething() throws Exception {
        // Initialize the database
        bedRepository.saveAndFlush(bed);

        // Get all the bedList where wardAllocationDate equals to DEFAULT_WARD_ALLOCATION_DATE
        defaultBedShouldBeFound("wardAllocationDate.equals=" + DEFAULT_WARD_ALLOCATION_DATE);

        // Get all the bedList where wardAllocationDate equals to UPDATED_WARD_ALLOCATION_DATE
        defaultBedShouldNotBeFound("wardAllocationDate.equals=" + UPDATED_WARD_ALLOCATION_DATE);
    }

    @Test
    @Transactional
    public void getAllBedsByWardAllocationDateIsNotEqualToSomething() throws Exception {
        // Initialize the database
        bedRepository.saveAndFlush(bed);

        // Get all the bedList where wardAllocationDate not equals to DEFAULT_WARD_ALLOCATION_DATE
        defaultBedShouldNotBeFound("wardAllocationDate.notEquals=" + DEFAULT_WARD_ALLOCATION_DATE);

        // Get all the bedList where wardAllocationDate not equals to UPDATED_WARD_ALLOCATION_DATE
        defaultBedShouldBeFound("wardAllocationDate.notEquals=" + UPDATED_WARD_ALLOCATION_DATE);
    }

    @Test
    @Transactional
    public void getAllBedsByWardAllocationDateIsInShouldWork() throws Exception {
        // Initialize the database
        bedRepository.saveAndFlush(bed);

        // Get all the bedList where wardAllocationDate in DEFAULT_WARD_ALLOCATION_DATE or UPDATED_WARD_ALLOCATION_DATE
        defaultBedShouldBeFound("wardAllocationDate.in=" + DEFAULT_WARD_ALLOCATION_DATE + "," + UPDATED_WARD_ALLOCATION_DATE);

        // Get all the bedList where wardAllocationDate equals to UPDATED_WARD_ALLOCATION_DATE
        defaultBedShouldNotBeFound("wardAllocationDate.in=" + UPDATED_WARD_ALLOCATION_DATE);
    }

    @Test
    @Transactional
    public void getAllBedsByWardAllocationDateIsNullOrNotNull() throws Exception {
        // Initialize the database
        bedRepository.saveAndFlush(bed);

        // Get all the bedList where wardAllocationDate is not null
        defaultBedShouldBeFound("wardAllocationDate.specified=true");

        // Get all the bedList where wardAllocationDate is null
        defaultBedShouldNotBeFound("wardAllocationDate.specified=false");
    }

    @Test
    @Transactional
    public void getAllBedsByWardAllocationDateIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        bedRepository.saveAndFlush(bed);

        // Get all the bedList where wardAllocationDate is greater than or equal to DEFAULT_WARD_ALLOCATION_DATE
        defaultBedShouldBeFound("wardAllocationDate.greaterThanOrEqual=" + DEFAULT_WARD_ALLOCATION_DATE);

        // Get all the bedList where wardAllocationDate is greater than or equal to UPDATED_WARD_ALLOCATION_DATE
        defaultBedShouldNotBeFound("wardAllocationDate.greaterThanOrEqual=" + UPDATED_WARD_ALLOCATION_DATE);
    }

    @Test
    @Transactional
    public void getAllBedsByWardAllocationDateIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        bedRepository.saveAndFlush(bed);

        // Get all the bedList where wardAllocationDate is less than or equal to DEFAULT_WARD_ALLOCATION_DATE
        defaultBedShouldBeFound("wardAllocationDate.lessThanOrEqual=" + DEFAULT_WARD_ALLOCATION_DATE);

        // Get all the bedList where wardAllocationDate is less than or equal to SMALLER_WARD_ALLOCATION_DATE
        defaultBedShouldNotBeFound("wardAllocationDate.lessThanOrEqual=" + SMALLER_WARD_ALLOCATION_DATE);
    }

    @Test
    @Transactional
    public void getAllBedsByWardAllocationDateIsLessThanSomething() throws Exception {
        // Initialize the database
        bedRepository.saveAndFlush(bed);

        // Get all the bedList where wardAllocationDate is less than DEFAULT_WARD_ALLOCATION_DATE
        defaultBedShouldNotBeFound("wardAllocationDate.lessThan=" + DEFAULT_WARD_ALLOCATION_DATE);

        // Get all the bedList where wardAllocationDate is less than UPDATED_WARD_ALLOCATION_DATE
        defaultBedShouldBeFound("wardAllocationDate.lessThan=" + UPDATED_WARD_ALLOCATION_DATE);
    }

    @Test
    @Transactional
    public void getAllBedsByWardAllocationDateIsGreaterThanSomething() throws Exception {
        // Initialize the database
        bedRepository.saveAndFlush(bed);

        // Get all the bedList where wardAllocationDate is greater than DEFAULT_WARD_ALLOCATION_DATE
        defaultBedShouldNotBeFound("wardAllocationDate.greaterThan=" + DEFAULT_WARD_ALLOCATION_DATE);

        // Get all the bedList where wardAllocationDate is greater than SMALLER_WARD_ALLOCATION_DATE
        defaultBedShouldBeFound("wardAllocationDate.greaterThan=" + SMALLER_WARD_ALLOCATION_DATE);
    }


    @Test
    @Transactional
    public void getAllBedsByWardIsEqualToSomething() throws Exception {
        // Initialize the database
        bedRepository.saveAndFlush(bed);
        Ward ward = WardResourceIT.createEntity(em);
        em.persist(ward);
        em.flush();
        bed.setWard(ward);
        bedRepository.saveAndFlush(bed);
        Long wardId = ward.getId();

        // Get all the bedList where ward equals to wardId
        defaultBedShouldBeFound("wardId.equals=" + wardId);

        // Get all the bedList where ward equals to wardId + 1
        defaultBedShouldNotBeFound("wardId.equals=" + (wardId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultBedShouldBeFound(String filter) throws Exception {
        restBedMockMvc.perform(get("/api/beds?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(bed.getId().intValue())))
            .andExpect(jsonPath("$.[*].bedReferenceId").value(hasItem(DEFAULT_BED_REFERENCE_ID)))
            .andExpect(jsonPath("$.[*].bedName").value(hasItem(DEFAULT_BED_NAME)))
            .andExpect(jsonPath("$.[*].wardAllocationDate").value(hasItem(DEFAULT_WARD_ALLOCATION_DATE.toString())));

        // Check, that the count call also returns 1
        restBedMockMvc.perform(get("/api/beds/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultBedShouldNotBeFound(String filter) throws Exception {
        restBedMockMvc.perform(get("/api/beds?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restBedMockMvc.perform(get("/api/beds/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingBed() throws Exception {
        // Get the bed
        restBedMockMvc.perform(get("/api/beds/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBed() throws Exception {
        // Initialize the database
        bedRepository.saveAndFlush(bed);

        int databaseSizeBeforeUpdate = bedRepository.findAll().size();

        // Update the bed
        Bed updatedBed = bedRepository.findById(bed.getId()).get();
        // Disconnect from session so that the updates on updatedBed are not directly saved in db
        em.detach(updatedBed);
        updatedBed
            .bedReferenceId(UPDATED_BED_REFERENCE_ID)
            .bedName(UPDATED_BED_NAME)
            .wardAllocationDate(UPDATED_WARD_ALLOCATION_DATE);
        BedDTO bedDTO = bedMapper.toDto(updatedBed);

        restBedMockMvc.perform(put("/api/beds")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bedDTO)))
            .andExpect(status().isOk());

        // Validate the Bed in the database
        List<Bed> bedList = bedRepository.findAll();
        assertThat(bedList).hasSize(databaseSizeBeforeUpdate);
        Bed testBed = bedList.get(bedList.size() - 1);
        assertThat(testBed.getBedReferenceId()).isEqualTo(UPDATED_BED_REFERENCE_ID);
        assertThat(testBed.getBedName()).isEqualTo(UPDATED_BED_NAME);
        assertThat(testBed.getWardAllocationDate()).isEqualTo(UPDATED_WARD_ALLOCATION_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingBed() throws Exception {
        int databaseSizeBeforeUpdate = bedRepository.findAll().size();

        // Create the Bed
        BedDTO bedDTO = bedMapper.toDto(bed);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBedMockMvc.perform(put("/api/beds")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(bedDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Bed in the database
        List<Bed> bedList = bedRepository.findAll();
        assertThat(bedList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteBed() throws Exception {
        // Initialize the database
        bedRepository.saveAndFlush(bed);

        int databaseSizeBeforeDelete = bedRepository.findAll().size();

        // Delete the bed
        restBedMockMvc.perform(delete("/api/beds/{id}", bed.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Bed> bedList = bedRepository.findAll();
        assertThat(bedList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
