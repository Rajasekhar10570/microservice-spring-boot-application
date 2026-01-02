package com.raja.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private Integer id;
    private String name;
    private Integer quantity;
    private Integer productId;
    private Instant createDate;
    private String status;
}
