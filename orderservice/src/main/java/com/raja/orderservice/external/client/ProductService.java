package com.raja.orderservice.external.client;

import com.raja.orderservice.exception.CustomException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@CircuitBreaker(name = "external", fallbackMethod = "fallback")
@FeignClient(name = "product-service/product", url = "http://localhost:8082/product/")
public interface ProductService {

    @PutMapping("/reducequantity/{id}")
    public Product reduceQuantity(@PathVariable Integer id, @RequestParam Integer quantity);

    default void fallback(Exception ex){
        throw new CustomException("Product Service is not found.", HttpStatus.SERVICE_UNAVAILABLE.name());
    }
}
