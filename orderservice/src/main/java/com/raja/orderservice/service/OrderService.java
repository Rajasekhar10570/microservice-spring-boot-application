package com.raja.orderservice.service;

import com.raja.orderservice.dto.OrderDTO;
import com.raja.orderservice.dto.OrderMapper;
import com.raja.orderservice.entity.Order;
import com.raja.orderservice.exception.OrderNotFoundException;
import com.raja.orderservice.external.client.ProductService;
import com.raja.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductService productService;

    public List<Order> getAllOrders(){
        return orderRepository.findAll();
    }

    public Order getOrderDetails(Integer id){
        return orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException("Specified Order id "+id+" is not found"));
    }

    public Order createNewOrder(OrderDTO order){
        productService.reduceQuantity(order.getProductId(),order.getQuantity());

        Order order1 = Order.builder()
                .name(order.getName())
                .productId(order.getProductId())
                .quantity(order.getQuantity())
                .createDate(Instant.now())
                .status(order.getStatus())
                .build();
        return orderRepository.save(order1);
    }

    public Order updateOrder(OrderDTO order, Integer orderId){
        Order order1 = orderRepository.findById(orderId).orElseThrow(() -> new OrderNotFoundException("Specified Order id is not found."));
//        OrderMapper.INSTANCE.updateOrderFromDTO(order1, order);
        if(order.getProductId() != null){
            order1.setProductId(order.getProductId());
        }
        if(order.getStatus() != null){
            order1.setStatus(order.getStatus());
        }
        return orderRepository.save(order1);
    }
}
