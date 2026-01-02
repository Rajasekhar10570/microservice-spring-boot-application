package com.raja.orderservice.exception;

public class OrderServiceException extends RuntimeException{
    String errorCode;
    public OrderServiceException(String message, String errorCode){
        super(message);
        this.errorCode = errorCode;
    }
    public String getErrorCode(){
        return errorCode;
    }


}
