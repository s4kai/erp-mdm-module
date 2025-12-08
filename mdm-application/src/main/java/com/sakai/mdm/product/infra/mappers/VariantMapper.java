package com.sakai.mdm.product.infra.mappers;

import com.sakai.mdm.product.application.commands.dtos.VariantData;
import com.sakai.mdm.product.dtos.VariantRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.sakai.mdm.product.application.commands.CreateProductCommand;

@Mapper(componentModel = "spring", uses = {
    MoneyMapper.class,
    DimensionsMapper.class
})
public interface VariantMapper {
    @Mapping(target = "uom", expression = "java(new UomData(v.uom()))")
    VariantData toVariant(VariantRequest v);
}
