package com.raja.orderservice.external.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorRespone {
    private String message;
    private String errorCode;
    private String type;
    private String title;
    private String status;
    private String detail;
    private String instance;
}
