package com.whatsapp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/home")
     public ResponseEntity<String> homeController(){
         return new ResponseEntity<>("welcome to our whatsapp ", HttpStatus.OK);
     }
}
