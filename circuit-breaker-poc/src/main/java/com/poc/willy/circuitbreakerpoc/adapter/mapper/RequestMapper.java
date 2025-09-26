package com.poc.willy.circuitbreakerpoc.adapter.mapper;

import com.poc.willy.circuitbreakerpoc.adapter.dto.PaymentRequest;
import com.poc.willy.circuitbreakerpoc.application.dto.PaymentDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RequestMapper {

    RequestMapper INSTANCE = Mappers.getMapper(RequestMapper.class);

    PaymentDto mapToDto(PaymentRequest paymentRequest);
}
