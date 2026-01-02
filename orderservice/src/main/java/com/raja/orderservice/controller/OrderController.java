package com.raja.orderservice.controller;

import com.raja.orderservice.dto.OrderDTO;
import com.raja.orderservice.entity.Order;
import com.raja.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PreAuthorize("hasAnyRole('admin', 'Customer')")
    @GetMapping("/list")
    public ResponseEntity<List<Order>> getlistOfOrders(){
        return new ResponseEntity<>(orderService.getAllOrders(), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('admin', 'Customer')")
    @GetMapping("/select/{id}")
    public ResponseEntity<Order> getSingleOrder(@PathVariable Integer id){
        return new ResponseEntity<>(orderService.getOrderDetails(id), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('admin','customer')")
    @PostMapping("/create")
    public ResponseEntity<Order> createNewOrder(@RequestBody OrderDTO orderDTO){
        return new ResponseEntity<>(orderService.createNewOrder(orderDTO), HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole('admin')")
    @PatchMapping("/update/{id}")
    public ResponseEntity<Order> updateOrder(@RequestBody OrderDTO orderDTO, @PathVariable Integer id){
        return new ResponseEntity<>(orderService.updateOrder(orderDTO,id), HttpStatus.OK);
    }


}
