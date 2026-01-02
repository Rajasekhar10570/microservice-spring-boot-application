package com.raja.apigateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallbackController {


    @RequestMapping(value = "/orderServiceFallBack" , method = {RequestMethod.GET, RequestMethod.POST,
            RequestMethod.PUT, RequestMethod.DELETE,
            RequestMethod.PATCH})
    public String ordersFallback(){
        return "Order service is down!";
    }

    @RequestMapping(value = "/productServiceFallBack" , method = {RequestMethod.GET, RequestMethod.POST,
            RequestMethod.PUT, RequestMethod.DELETE,
            RequestMethod.PATCH})
    public String productFallback(){
        return "Product service is down!";
    }
}
