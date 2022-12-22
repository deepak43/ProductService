package com.techailez.ProductService.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductRequest {

    @JsonProperty("product_name")
    private String name;

    @JsonProperty("product_price")
    private Long price;

    @JsonProperty("product_quantity")
    private Long quantity;
}
