package com.ncs.iconnect.sample.lab.generated.service.mapper;


import com.ncs.iconnect.sample.lab.generated.domain.*;
import com.ncs.iconnect.sample.lab.generated.service.dto.WardDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Ward} and its DTO {@link WardDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface WardMapper extends EntityMapper<WardDTO, Ward> {


    @Mapping(target = "beds", ignore = true)
    @Mapping(target = "removeBed", ignore = true)
    Ward toEntity(WardDTO wardDTO);

    default Ward fromId(Long id) {
        if (id == null) {
            return null;
        }
        Ward ward = new Ward();
        ward.setId(id);
        return ward;
    }
}
