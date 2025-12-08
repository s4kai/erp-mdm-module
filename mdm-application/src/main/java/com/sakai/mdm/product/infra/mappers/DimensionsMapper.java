package com.sakai.mdm.product.infra.mappers;

import com.sakai.mdm.product.application.commands.dtos.DimensionsData;
import com.sakai.mdm.product.dtos.DimensionsRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DimensionsMapper {

    default DimensionsData toDimensions(DimensionsRequest req) {
        return new DimensionsData(
            req.height(),
            req.width(),
            req.depth(),
            req.weight()
        );
    }
}
