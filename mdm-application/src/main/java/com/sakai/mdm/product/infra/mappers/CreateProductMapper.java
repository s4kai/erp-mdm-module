package com.sakai.mdm.product.infra.mappers;

import com.sakai.mdm.product.api.CreateProductRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.sakai.mdm.product.application.commands.CreateProductCommand;

import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring", uses = {
    MoneyMapper.class,
    DimensionsMapper.class,
    VariantMapper.class
})
public interface CreateProductMapper {
    @Mapping(target = "baseUom", expression = "java(new UomData(request.baseUom()))")
    CreateProductCommand toCommand(CreateProductRequest request);
}
