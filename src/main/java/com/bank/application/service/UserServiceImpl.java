package com.bank.application.service;

import com.bank.application.dto.*;
import com.bank.application.entity.User;
import com.bank.application.repo.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@Service
public class UserServiceImpl {

    @Autowired
    UserRepo userRepo;

    public BankResponse createAccount(UserDto userDto){

        log.info("service class");
        BankResponse bankResponse = new BankResponse();
        //check user exist
        Optional<User> user = userRepo.findByEmail(userDto.getEmail());
        if(user.isPresent()){
            log.info("user already exist");
        bankResponse.setResponseCode(UtilMessages.USER_EXIST_CODE);
        bankResponse.setResponseMessage(UtilMessages.USER_EXIST);
        AccountInfo accountInfo = AccountInfo.builder().accountBalance(user.get().getAccountBalance())
                .accountName(user.get().getFirstName())
                .accountNumber(user.get().getAccountNumber())
                .build();
        bankResponse.setAccountInfo(accountInfo);
        return bankResponse;
        }

        //user not exist
        User user1 = User.builder()
                .email(userDto.getEmail())
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .gender(userDto.getGender())
                .address(userDto.getAddress())
                .state(userDto.getState())
                .status("ACTIVE")
                .phNum(userDto.getPhNum())
                .accountNumber(AccNumGeneration.generateAccountNumer())
                .accountBalance(BigDecimal.ZERO)
                .createdDate(LocalDateTime.now())
                .build();

        User user2 = userRepo.saveAndFlush(user1);
        bankResponse.setResponseCode(UtilMessages.USER_CREATED_CODE);
        bankResponse.setResponseMessage(UtilMessages.USER_CREATED);
        AccountInfo accountInfo = AccountInfo.builder().accountBalance(user2.getAccountBalance())
                .accountName(user2.getFirstName())
                .accountNumber(user2.getAccountNumber())
                .build();
        bankResponse.setAccountInfo(accountInfo);
        return bankResponse;
    }
}
