package com.raja.orderservice.dto;

import com.raja.orderservice.entity.Order;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    Order toEntity(OrderDTO orderDTO);
    OrderDTO toDto(Order order);


//    @Mapping(target = "id", ignore = true)  // Never update ID
//    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
//    void updateOrderFromDTO(@MappingTarget Order order, OrderDTO orderDTO);

}
