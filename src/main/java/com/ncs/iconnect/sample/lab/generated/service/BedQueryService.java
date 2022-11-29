package com.ncs.iconnect.sample.lab.generated.service;

import java.util.List;

import javax.persistence.criteria.JoinType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.jhipster.service.QueryService;

import com.ncs.iconnect.sample.lab.generated.domain.Bed;
import com.ncs.iconnect.sample.lab.generated.domain.*; // for static metamodels
import com.ncs.iconnect.sample.lab.generated.repository.BedRepository;
import com.ncs.iconnect.sample.lab.generated.service.dto.BedCriteria;
import com.ncs.iconnect.sample.lab.generated.service.dto.BedDTO;
import com.ncs.iconnect.sample.lab.generated.service.mapper.BedMapper;

/**
 * Service for executing complex queries for {@link Bed} entities in the database.
 * The main input is a {@link BedCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link BedDTO} or a {@link Page} of {@link BedDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class BedQueryService extends QueryService<Bed> {

    private final Logger log = LoggerFactory.getLogger(BedQueryService.class);

    private final BedRepository bedRepository;

    private final BedMapper bedMapper;

    public BedQueryService(BedRepository bedRepository, BedMapper bedMapper) {
        this.bedRepository = bedRepository;
        this.bedMapper = bedMapper;
    }

    /**
     * Return a {@link List} of {@link BedDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<BedDTO> findByCriteria(BedCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Bed> specification = createSpecification(criteria);
        return bedMapper.toDto(bedRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link BedDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<BedDTO> findByCriteria(BedCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Bed> specification = createSpecification(criteria);
        return bedRepository.findAll(specification, page)
            .map(bedMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(BedCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Bed> specification = createSpecification(criteria);
        return bedRepository.count(specification);
    }

    /**
     * Function to convert {@link BedCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Bed> createSpecification(BedCriteria criteria) {
        Specification<Bed> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Bed_.id));
            }
            if (criteria.getBedReferenceId() != null) {
                specification = specification.and(buildStringSpecification(criteria.getBedReferenceId(), Bed_.bedReferenceId));
            }
            if (criteria.getBedName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getBedName(), Bed_.bedName));
            }
            if (criteria.getWardAllocationDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getWardAllocationDate(), Bed_.wardAllocationDate));
            }
            if (criteria.getWardId() != null) {
                specification = specification.and(buildSpecification(criteria.getWardId(),
                    root -> root.join(Bed_.ward, JoinType.LEFT).get(Ward_.id)));
            }
        }
        return specification;
    }
}
