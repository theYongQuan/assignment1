package com.ncs.iconnect.sample.lab.generated.service.dto;

import java.io.Serializable;
import java.util.Objects;
import io.github.jhipster.service.Criteria;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;
import io.github.jhipster.service.filter.LocalDateFilter;

/**
 * Criteria class for the {@link com.ncs.iconnect.sample.lab.generated.domain.Bed} entity. This class is used
 * in {@link com.ncs.iconnect.sample.lab.generated.web.rest.BedResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /beds?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class BedCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter bedReferenceId;

    private StringFilter bedName;

    private LocalDateFilter wardAllocationDate;

    private LongFilter wardId;

    public BedCriteria() {
    }

    public BedCriteria(BedCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.bedReferenceId = other.bedReferenceId == null ? null : other.bedReferenceId.copy();
        this.bedName = other.bedName == null ? null : other.bedName.copy();
        this.wardAllocationDate = other.wardAllocationDate == null ? null : other.wardAllocationDate.copy();
        this.wardId = other.wardId == null ? null : other.wardId.copy();
    }

    @Override
    public BedCriteria copy() {
        return new BedCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getBedReferenceId() {
        return bedReferenceId;
    }

    public void setBedReferenceId(StringFilter bedReferenceId) {
        this.bedReferenceId = bedReferenceId;
    }

    public StringFilter getBedName() {
        return bedName;
    }

    public void setBedName(StringFilter bedName) {
        this.bedName = bedName;
    }

    public LocalDateFilter getWardAllocationDate() {
        return wardAllocationDate;
    }

    public void setWardAllocationDate(LocalDateFilter wardAllocationDate) {
        this.wardAllocationDate = wardAllocationDate;
    }

    public LongFilter getWardId() {
        return wardId;
    }

    public void setWardId(LongFilter wardId) {
        this.wardId = wardId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final BedCriteria that = (BedCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(bedReferenceId, that.bedReferenceId) &&
            Objects.equals(bedName, that.bedName) &&
            Objects.equals(wardAllocationDate, that.wardAllocationDate) &&
            Objects.equals(wardId, that.wardId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        bedReferenceId,
        bedName,
        wardAllocationDate,
        wardId
        );
    }

    @Override
    public String toString() {
        return "BedCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (bedReferenceId != null ? "bedReferenceId=" + bedReferenceId + ", " : "") +
                (bedName != null ? "bedName=" + bedName + ", " : "") +
                (wardAllocationDate != null ? "wardAllocationDate=" + wardAllocationDate + ", " : "") +
                (wardId != null ? "wardId=" + wardId + ", " : "") +
            "}";
    }

}
