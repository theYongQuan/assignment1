package com.ncs.iconnect.sample.lab.generated.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;
import java.time.LocalDate;

/**
 * A Bed.
 */
@Entity
@Table(name = "tbl_bed")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Bed implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "bed_reference_id", nullable = false)
    private String bedReferenceId;

    @Column(name = "bed_name")
    private String bedName;

    @NotNull
    @Column(name = "ward_allocation_date", nullable = false)
    private LocalDate wardAllocationDate;

    @ManyToOne
    @JsonIgnoreProperties("beds")
    private Ward ward;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBedReferenceId() {
        return bedReferenceId;
    }

    public Bed bedReferenceId(String bedReferenceId) {
        this.bedReferenceId = bedReferenceId;
        return this;
    }

    public void setBedReferenceId(String bedReferenceId) {
        this.bedReferenceId = bedReferenceId;
    }

    public String getBedName() {
        return bedName;
    }

    public Bed bedName(String bedName) {
        this.bedName = bedName;
        return this;
    }

    public void setBedName(String bedName) {
        this.bedName = bedName;
    }

    public LocalDate getWardAllocationDate() {
        return wardAllocationDate;
    }

    public Bed wardAllocationDate(LocalDate wardAllocationDate) {
        this.wardAllocationDate = wardAllocationDate;
        return this;
    }

    public void setWardAllocationDate(LocalDate wardAllocationDate) {
        this.wardAllocationDate = wardAllocationDate;
    }

    public Ward getWard() {
        return ward;
    }

    public Bed ward(Ward ward) {
        this.ward = ward;
        return this;
    }

    public void setWard(Ward ward) {
        this.ward = ward;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Bed)) {
            return false;
        }
        return id != null && id.equals(((Bed) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Bed{" +
            "id=" + getId() +
            ", bedReferenceId='" + getBedReferenceId() + "'" +
            ", bedName='" + getBedName() + "'" +
            ", wardAllocationDate='" + getWardAllocationDate() + "'" +
            "}";
    }
}
