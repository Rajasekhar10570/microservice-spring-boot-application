package com.raja.productservice.service;

import com.raja.productservice.entity.Product;
import com.raja.productservice.exception.ProductNotFoundException;
import com.raja.productservice.exception.ProductServiceException;
import com.raja.productservice.repository.ProductRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product createProduct(Product product){
        return productRepository.save(product);
    }

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public Product getProductById(Integer id){
        return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Sepecified product id "+id+"is not found"));
    }

    public  Product reduceQuantity(Integer id, Integer quantity){
        log.info("::{}",id);
        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Specified product id "+id+"is not found"));
        if (product.getQuantity() < quantity){
            throw new ProductServiceException("Insufficient product quantity, found only "+product.getQuantity());
        }
        product.setQuantity(product.getQuantity() - quantity);
        return productRepository.save(product);
    }

    public Product updateProduct(Integer id, Product rproduct) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Specified product id "+id+"is not found"));
        if(rproduct.getQuantity() != null){
            product.setQuantity(rproduct.getQuantity());
        }
        if(rproduct.getName() != null){
            product.setName(rproduct.getName());
        }
        return productRepository.save(product);
    }
}
