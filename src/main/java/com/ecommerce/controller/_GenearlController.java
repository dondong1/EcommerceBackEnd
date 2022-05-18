package com.ecommerce.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.common.RequestPojo.ApiResponse;
import com.ecommerce.service._AuthService;

@RestController
@RequestMapping("api")
public class _GenearlController {
    
    private static final Logger logger = LoggerFactory.getLogger(_GenearlController.class);

    @RequestMapping("status")//post and get
    public ResponseEntity<?> serverStatus() {
        return new ResponseEntity<>(new ApiResponse("Server is running.", ""), HttpStatus.OK);
    }
}
