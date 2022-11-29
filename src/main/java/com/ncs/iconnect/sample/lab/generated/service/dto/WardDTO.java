package com.ncs.iconnect.sample.lab.generated.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import com.ncs.iconnect.sample.lab.generated.domain.enumeration.WardClassType;
import com.ncs.iconnect.sample.lab.generated.domain.enumeration.WardLocation;

/**
 * A DTO for the {@link com.ncs.iconnect.sample.lab.generated.domain.Ward} entity.
 */
public class WardDTO implements Serializable {
    
    private Long id;

    @Size(min = 7)
    private String wardReferenceId;

    @Size(max = 10)
    private String wardName;

    @NotNull
    private WardClassType wardClassType;

    @NotNull
    private WardLocation wardLocation;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWardReferenceId() {
        return wardReferenceId;
    }

    public void setWardReferenceId(String wardReferenceId) {
        this.wardReferenceId = wardReferenceId;
    }

    public String getWardName() {
        return wardName;
    }

    public void setWardName(String wardName) {
        this.wardName = wardName;
    }

    public WardClassType getWardClassType() {
        return wardClassType;
    }

    public void setWardClassType(WardClassType wardClassType) {
        this.wardClassType = wardClassType;
    }

    public WardLocation getWardLocation() {
        return wardLocation;
    }

    public void setWardLocation(WardLocation wardLocation) {
        this.wardLocation = wardLocation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        WardDTO wardDTO = (WardDTO) o;
        if (wardDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), wardDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "WardDTO{" +
            "id=" + getId() +
            ", wardReferenceId='" + getWardReferenceId() + "'" +
            ", wardName='" + getWardName() + "'" +
            ", wardClassType='" + getWardClassType() + "'" +
            ", wardLocation='" + getWardLocation() + "'" +
            "}";
    }
}
