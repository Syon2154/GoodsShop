package com.goods.shop.mapper;

import com.goods.shop.dto.response.ProductResponseDto;
import com.goods.shop.dto.response.ShoppingCartResponseDto;
import com.goods.shop.model.ShoppingCart;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class ShoppingCartMapper {
    private final ProductMapper productMapper;

    public ShoppingCartMapper(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    public ShoppingCartResponseDto mapToDto(ShoppingCart shoppingCart) {
        ShoppingCartResponseDto responseDto = new ShoppingCartResponseDto();
        responseDto.setId(shoppingCart.getId());
        responseDto.setUserId(shoppingCart.getUser().getId());
        List<ProductResponseDto> responseDtos = shoppingCart.getProducts().stream()
                .map(productMapper::mapToDto)
                .collect(Collectors.toList());
        responseDto.setProductDtos(responseDtos);
        return responseDto;
    }
}
