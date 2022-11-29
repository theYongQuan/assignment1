package com.ncs.iconnect.sample.lab.generated.service.dto;

import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.ncs.iconnect.sample.lab.generated.domain.Bed} entity.
 */
public class BedDTO implements Serializable {
    
    private Long id;

    @NotNull
    private String bedReferenceId;

    private String bedName;

    @NotNull
    private LocalDate wardAllocationDate;


    private Long wardId;

    private String wardWardName;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBedReferenceId() {
        return bedReferenceId;
    }

    public void setBedReferenceId(String bedReferenceId) {
        this.bedReferenceId = bedReferenceId;
    }

    public String getBedName() {
        return bedName;
    }

    public void setBedName(String bedName) {
        this.bedName = bedName;
    }

    public LocalDate getWardAllocationDate() {
        return wardAllocationDate;
    }

    public void setWardAllocationDate(LocalDate wardAllocationDate) {
        this.wardAllocationDate = wardAllocationDate;
    }

    public Long getWardId() {
        return wardId;
    }

    public void setWardId(Long wardId) {
        this.wardId = wardId;
    }

    public String getWardWardName() {
        return wardWardName;
    }

    public void setWardWardName(String wardWardName) {
        this.wardWardName = wardWardName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        BedDTO bedDTO = (BedDTO) o;
        if (bedDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), bedDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "BedDTO{" +
            "id=" + getId() +
            ", bedReferenceId='" + getBedReferenceId() + "'" +
            ", bedName='" + getBedName() + "'" +
            ", wardAllocationDate='" + getWardAllocationDate() + "'" +
            ", wardId=" + getWardId() +
            ", wardWardName='" + getWardWardName() + "'" +
            "}";
    }
}
