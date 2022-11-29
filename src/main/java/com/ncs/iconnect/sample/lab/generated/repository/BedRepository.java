package com.ncs.iconnect.sample.lab.generated.repository;

import com.ncs.iconnect.sample.lab.generated.domain.Bed;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Bed entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BedRepository extends JpaRepository<Bed, Long>, JpaSpecificationExecutor<Bed> {
}
