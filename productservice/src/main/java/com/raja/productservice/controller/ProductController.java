package com.raja.productservice.controller;

import com.raja.productservice.entity.Product;
import com.raja.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PreAuthorize("hasAnyRole('admin', 'Customer')")
    @GetMapping("/list")
    public ResponseEntity<List<Product>> getAllProducts(){
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('admin', 'Customer')")
    @GetMapping("/find/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Integer id){
        return new ResponseEntity<>(productService.getProductById(id), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('admin')")
    @PostMapping("/create")
    public ResponseEntity<Product> createProdcut(@RequestBody Product product){
        return new ResponseEntity<>(productService.createProduct(product), HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole('admin', 'Customer')")
    @PutMapping("/reducequantity/{id}")
    public Product reduceQuantity(@PathVariable Integer id, @RequestParam Integer quantity){
        return productService.reduceQuantity(id, quantity);
    }

    @PreAuthorize("hasAnyRole('admin')")
    @PutMapping("/update/{id}")
    public Product updateProduct(@PathVariable Integer id, @RequestBody Product product){
        return productService.updateProduct(id, product);
    }

}
