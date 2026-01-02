package com.raja.orderservice.external.decoder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.raja.orderservice.exception.CustomException;
import com.raja.orderservice.exception.OrderServiceException;
import com.raja.orderservice.external.response.ErrorRespone;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;

@Log4j2
public class CustomErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String s, Response response) {

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            ErrorRespone errorRespone = objectMapper.readValue(response.body().asInputStream(), ErrorRespone.class);
            return new CustomException(errorRespone.getDetail(), errorRespone.getTitle());
        } catch (IOException e) {
            log.error(e);
            throw new CustomException("Internal server error","INTERNAL-SERVER-ERROR");
        }
    }
}
