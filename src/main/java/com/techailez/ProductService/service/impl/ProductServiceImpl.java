package com.techailez.ProductService.service.impl;

import com.techailez.ProductService.dao.Product;
import com.techailez.ProductService.dto.request.ProductRequest;
import com.techailez.ProductService.dto.response.ProductResponse;
import com.techailez.ProductService.exception.ProductServiceCustomException;
import com.techailez.ProductService.repository.ProductRepository;
import com.techailez.ProductService.service.ProductService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository repository;
    @Override
    public ProductResponse addProduct(ProductRequest entity) {

        Product product = Product.builder()
                .name(entity.getName())
                .price(entity.getPrice())
                .quantity(entity.getQuantity())
                .build();

        log.info("Adding product : {}", product);

        Product saved = repository.save(product);

        ProductResponse res = ProductResponse.builder()
                .id(saved.getId())
                .name(saved.getName())
                .price(saved.getPrice())
                .quantity(saved.getQuantity())
                .build();

        return res;
    }

    @Override
    public List<ProductResponse> getProducts() {
        log.info("Getting all Products");
        List<Product> results = repository.findAll();
        return results.stream().map(r -> ProductResponse.builder()
                .id(r.getId())
                .name(r.getName())
                .price(r.getPrice())
                .quantity(r.getQuantity())
                .build()).collect(Collectors.toList());
    }

    @Override
    public ProductResponse getProductById(Long id) {
        log.info("Getting product with id : {}", id);
        Product result = repository.findById(id)
                .orElseThrow(() -> new ProductServiceCustomException("Product with given id not present", "PRODUCT_NOT_FOUND"));

        return ProductResponse.builder()
                .id(result.getId())
                .name(result.getName())
                .price(result.getPrice())
                .quantity(result.getQuantity())
                .build();
    }

    @Override
    public void reduceQuantity(Long id, Long quantity) {
        log.info("Reducing quantity {} for product id {} ", quantity, id);

        Product product = repository.findById(id).orElseThrow(() -> new ProductServiceCustomException("Product with given id not found", "PRODUCT_NOT_FOUND"));

        if(product.getQuantity() < quantity) {
            throw new ProductServiceCustomException("Product don't have sufficient quantity", "INSUFFICIENT_QUANTITY");
        }

        product.setQuantity(product.getQuantity() - quantity);

        repository.save(product);

        log.info("Product quantity updated");
    }
}
