package edu.buaa.service;

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

import edu.buaa.domain.Info;
import edu.buaa.domain.*; // for static metamodels
import edu.buaa.repository.InfoRepository;
import edu.buaa.service.dto.InfoCriteria;

/**
 * Service for executing complex queries for Info entities in the database.
 * The main input is a {@link InfoCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link Info} or a {@link Page} of {@link Info} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class InfoQueryService extends QueryService<Info> {

    private final Logger log = LoggerFactory.getLogger(InfoQueryService.class);

    private final InfoRepository infoRepository;

    public InfoQueryService(InfoRepository infoRepository) {
        this.infoRepository = infoRepository;
    }

    /**
     * Return a {@link List} of {@link Info} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<Info> findByCriteria(InfoCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Info> specification = createSpecification(criteria);
        return infoRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link Info} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<Info> findByCriteria(InfoCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Info> specification = createSpecification(criteria);
        return infoRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(InfoCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Info> specification = createSpecification(criteria);
        return infoRepository.count(specification);
    }

    /**
     * Function to convert InfoCriteria to a {@link Specification}
     */
    private Specification<Info> createSpecification(InfoCriteria criteria) {
        Specification<Info> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Info_.id));
            }
            if (criteria.getFile_name() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFile_name(), Info_.fileName));
            }
            if (criteria.getFile_size() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFile_size(), Info_.fileSize));
            }
            if (criteria.getFile_type() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFile_type(), Info_.fileType));
            }
            if (criteria.getNote() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNote(), Info_.note));
            }
        }
        return specification;
    }
}
