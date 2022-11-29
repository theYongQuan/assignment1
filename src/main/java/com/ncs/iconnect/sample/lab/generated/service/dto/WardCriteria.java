package com.ncs.iconnect.sample.lab.generated.service.dto;

import java.io.Serializable;
import java.util.Objects;
import io.github.jhipster.service.Criteria;
import com.ncs.iconnect.sample.lab.generated.domain.enumeration.WardClassType;
import com.ncs.iconnect.sample.lab.generated.domain.enumeration.WardLocation;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;

/**
 * Criteria class for the {@link com.ncs.iconnect.sample.lab.generated.domain.Ward} entity. This class is used
 * in {@link com.ncs.iconnect.sample.lab.generated.web.rest.WardResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /wards?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class WardCriteria implements Serializable, Criteria {
    /**
     * Class for filtering WardClassType
     */
    public static class WardClassTypeFilter extends Filter<WardClassType> {

        public WardClassTypeFilter() {
        }

        public WardClassTypeFilter(WardClassTypeFilter filter) {
            super(filter);
        }

        @Override
        public WardClassTypeFilter copy() {
            return new WardClassTypeFilter(this);
        }

    }
    /**
     * Class for filtering WardLocation
     */
    public static class WardLocationFilter extends Filter<WardLocation> {

        public WardLocationFilter() {
        }

        public WardLocationFilter(WardLocationFilter filter) {
            super(filter);
        }

        @Override
        public WardLocationFilter copy() {
            return new WardLocationFilter(this);
        }

    }

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter wardReferenceId;

    private StringFilter wardName;

    private WardClassTypeFilter wardClassType;

    private WardLocationFilter wardLocation;

    private LongFilter bedId;

    public WardCriteria() {
    }

    public WardCriteria(WardCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.wardReferenceId = other.wardReferenceId == null ? null : other.wardReferenceId.copy();
        this.wardName = other.wardName == null ? null : other.wardName.copy();
        this.wardClassType = other.wardClassType == null ? null : other.wardClassType.copy();
        this.wardLocation = other.wardLocation == null ? null : other.wardLocation.copy();
        this.bedId = other.bedId == null ? null : other.bedId.copy();
    }

    @Override
    public WardCriteria copy() {
        return new WardCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getWardReferenceId() {
        return wardReferenceId;
    }

    public void setWardReferenceId(StringFilter wardReferenceId) {
        this.wardReferenceId = wardReferenceId;
    }

    public StringFilter getWardName() {
        return wardName;
    }

    public void setWardName(StringFilter wardName) {
        this.wardName = wardName;
    }

    public WardClassTypeFilter getWardClassType() {
        return wardClassType;
    }

    public void setWardClassType(WardClassTypeFilter wardClassType) {
        this.wardClassType = wardClassType;
    }

    public WardLocationFilter getWardLocation() {
        return wardLocation;
    }

    public void setWardLocation(WardLocationFilter wardLocation) {
        this.wardLocation = wardLocation;
    }

    public LongFilter getBedId() {
        return bedId;
    }

    public void setBedId(LongFilter bedId) {
        this.bedId = bedId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final WardCriteria that = (WardCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(wardReferenceId, that.wardReferenceId) &&
            Objects.equals(wardName, that.wardName) &&
            Objects.equals(wardClassType, that.wardClassType) &&
            Objects.equals(wardLocation, that.wardLocation) &&
            Objects.equals(bedId, that.bedId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        wardReferenceId,
        wardName,
        wardClassType,
        wardLocation,
        bedId
        );
    }

    @Override
    public String toString() {
        return "WardCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (wardReferenceId != null ? "wardReferenceId=" + wardReferenceId + ", " : "") +
                (wardName != null ? "wardName=" + wardName + ", " : "") +
                (wardClassType != null ? "wardClassType=" + wardClassType + ", " : "") +
                (wardLocation != null ? "wardLocation=" + wardLocation + ", " : "") +
                (bedId != null ? "bedId=" + bedId + ", " : "") +
            "}";
    }

}
