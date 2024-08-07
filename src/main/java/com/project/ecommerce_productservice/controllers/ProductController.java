package com.project.ecommerce_productservice.controllers;

import com.project.ecommerce_productservice.dtos.FakeStoreProductDTO;
import com.project.ecommerce_productservice.exceptions.PermissionDeniedException;
import com.project.ecommerce_productservice.exceptions.ProductNotExistException;
import com.project.ecommerce_productservice.models.Product;
import com.project.ecommerce_productservice.services.ProductService;
import com.project.ecommerce_productservice.services.SelfProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(@Qualifier("selfProductService") ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getSingleProduct(@PathVariable("id") Long id) throws ProductNotExistException {
        return new ResponseEntity<>(productService.getSingleProduct(id), HttpStatus.ACCEPTED);
    }

    @GetMapping()
    public ResponseEntity<List<Product>> getAllProducts() throws ProductNotExistException {
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.ACCEPTED);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Product>> getAllProductsByCategory(@PathVariable("category") String category) throws ProductNotExistException {
        return new ResponseEntity<>(productService.getAllProductsByCategory(category), HttpStatus.ACCEPTED);
    }

    @PostMapping()
    public ResponseEntity<Product> addNewProduct(@RequestBody SelfProductService product) throws PermissionDeniedException {
        return new ResponseEntity(productService.addNewProduct(product), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") Long id, @RequestBody Product product) throws PermissionDeniedException {
        return new ResponseEntity<>(productService.updateProduct(id, product), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> replaceProduct(@PathVariable("id") Long id, @RequestBody Product product) throws PermissionDeniedException {
        return new ResponseEntity<>(productService.replaceProduct(id, product), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteProduct(@PathVariable("id") Long id) throws PermissionDeniedException {
        return new ResponseEntity<>(productService.deleteProduct(id), HttpStatus.ACCEPTED);
    }
}
