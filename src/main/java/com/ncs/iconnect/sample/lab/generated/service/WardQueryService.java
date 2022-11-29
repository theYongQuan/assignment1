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

import com.ncs.iconnect.sample.lab.generated.domain.Ward;
import com.ncs.iconnect.sample.lab.generated.domain.*; // for static metamodels
import com.ncs.iconnect.sample.lab.generated.repository.WardRepository;
import com.ncs.iconnect.sample.lab.generated.service.dto.WardCriteria;
import com.ncs.iconnect.sample.lab.generated.service.dto.WardDTO;
import com.ncs.iconnect.sample.lab.generated.service.mapper.WardMapper;

/**
 * Service for executing complex queries for {@link Ward} entities in the database.
 * The main input is a {@link WardCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link WardDTO} or a {@link Page} of {@link WardDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class WardQueryService extends QueryService<Ward> {

    private final Logger log = LoggerFactory.getLogger(WardQueryService.class);

    private final WardRepository wardRepository;

    private final WardMapper wardMapper;

    public WardQueryService(WardRepository wardRepository, WardMapper wardMapper) {
        this.wardRepository = wardRepository;
        this.wardMapper = wardMapper;
    }

    /**
     * Return a {@link List} of {@link WardDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<WardDTO> findByCriteria(WardCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Ward> specification = createSpecification(criteria);
        return wardMapper.toDto(wardRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link WardDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<WardDTO> findByCriteria(WardCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Ward> specification = createSpecification(criteria);
        return wardRepository.findAll(specification, page)
            .map(wardMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(WardCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Ward> specification = createSpecification(criteria);
        return wardRepository.count(specification);
    }

    /**
     * Function to convert {@link WardCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Ward> createSpecification(WardCriteria criteria) {
        Specification<Ward> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Ward_.id));
            }
            if (criteria.getWardReferenceId() != null) {
                specification = specification.and(buildStringSpecification(criteria.getWardReferenceId(), Ward_.wardReferenceId));
            }
            if (criteria.getWardName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getWardName(), Ward_.wardName));
            }
            if (criteria.getWardClassType() != null) {
                specification = specification.and(buildSpecification(criteria.getWardClassType(), Ward_.wardClassType));
            }
            if (criteria.getWardLocation() != null) {
                specification = specification.and(buildSpecification(criteria.getWardLocation(), Ward_.wardLocation));
            }
            if (criteria.getBedId() != null) {
                specification = specification.and(buildSpecification(criteria.getBedId(),
                    root -> root.join(Ward_.beds, JoinType.LEFT).get(Bed_.id)));
            }
        }
        return specification;
    }
}
