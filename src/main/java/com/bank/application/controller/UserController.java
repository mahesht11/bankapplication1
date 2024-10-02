package com.bank.application.controller;


import com.bank.application.dto.BankResponse;
import com.bank.application.dto.UserDto;
import com.bank.application.service.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/bank")
@RestController
@Slf4j
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @PostMapping("/create")
    public ResponseEntity<BankResponse> createUser(@RequestBody UserDto userDto){
        log.info("controller class");
        return new ResponseEntity<>(userService.createAccount(userDto), HttpStatus.CREATED);

    }

    @GetMapping("/check")
    public String check(){
        return "bank application1 is working";
    }

}
