package com.techailez.ProductService.service;

import com.techailez.ProductService.dao.Product;
import com.techailez.ProductService.dto.request.ProductRequest;
import com.techailez.ProductService.dto.response.ProductResponse;

import java.util.List;

public interface ProductService {

    public ProductResponse addProduct(ProductRequest product);

    public List<ProductResponse> getProducts();

    public ProductResponse getProductById(Long id);

    void reduceQuantity(Long id, Long quantity);
}
