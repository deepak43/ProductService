package com.techailez.ProductService.controller;

import com.techailez.ProductService.dao.Product;
import com.techailez.ProductService.dto.request.ProductRequest;
import com.techailez.ProductService.dto.response.ProductResponse;
import com.techailez.ProductService.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService service;
    @PostMapping
    public ResponseEntity<Long> addProduct(@RequestBody ProductRequest product) {
        ProductResponse saved = service.addProduct(product);
        return new ResponseEntity<>(saved.getId(), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        List<ProductResponse> results = service.getProducts();
        return new ResponseEntity<>(results, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProdcutById(@PathVariable Long id) {
        ProductResponse result = service.getProductById(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping("/reducequantity/{id}")
    public ResponseEntity<Void> reduceQuantity(@PathVariable Long id, @RequestParam Long quantity) {
        service.reduceQuantity(id, quantity);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
