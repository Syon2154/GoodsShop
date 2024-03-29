package com.goods.shop.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

public class CategoryRequestDto {
    @NotBlank
    @NotEmpty
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
