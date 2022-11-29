package com.ncs.iconnect.sample.lab.generated.repository;

import com.ncs.iconnect.sample.lab.generated.domain.Ward;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Ward entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WardRepository extends JpaRepository<Ward, Long>, JpaSpecificationExecutor<Ward> {
}
