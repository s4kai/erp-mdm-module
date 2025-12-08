package com.sakai.mdm.product.infra.mappers;

import com.sakai.mdm.product.application.commands.dtos.MoneyData;
import com.sakai.mdm.product.dtos.MoneyRequest;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface MoneyMapper {
    default MoneyData toMoney(MoneyRequest req) {
        return new MoneyData(req.amount(), req.currency());
    }
}
