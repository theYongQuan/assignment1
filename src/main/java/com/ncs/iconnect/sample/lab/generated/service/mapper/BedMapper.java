package com.ncs.iconnect.sample.lab.generated.service.mapper;


import com.ncs.iconnect.sample.lab.generated.domain.*;
import com.ncs.iconnect.sample.lab.generated.service.dto.BedDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Bed} and its DTO {@link BedDTO}.
 */
@Mapper(componentModel = "spring", uses = {WardMapper.class})
public interface BedMapper extends EntityMapper<BedDTO, Bed> {

    @Mapping(source = "ward.id", target = "wardId")
    @Mapping(source = "ward.wardName", target = "wardWardName")
    BedDTO toDto(Bed bed);

    @Mapping(source = "wardId", target = "ward")
    Bed toEntity(BedDTO bedDTO);

    default Bed fromId(Long id) {
        if (id == null) {
            return null;
        }
        Bed bed = new Bed();
        bed.setId(id);
        return bed;
    }
}
