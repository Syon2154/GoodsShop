package com.goods.shop.mapper;

import com.goods.shop.dto.response.OrderResponseDto;
import com.goods.shop.dto.response.ProductResponseDto;
import com.goods.shop.model.Order;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {
    private final ProductMapper productMapper;

    public OrderMapper(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    public OrderResponseDto mapToDto(Order order) {
        OrderResponseDto responseDto = new OrderResponseDto();
        responseDto.setId(order.getId());
        responseDto.setUserId(order.getUser().getId());
        List<ProductResponseDto> responseDtos = order.getProducts().stream()
                .map(productMapper::mapToDto)
                .collect(Collectors.toList());
        responseDto.setProductDtos(responseDtos);
        return responseDto;
    }
}
