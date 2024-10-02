package com.bank.application.dto;

import java.time.Year;

public class AccNumGeneration {

    public static String generateAccountNumer(){
        int max = 999999;
        int min = 100000;
        Year currentYear = Year.now();
        int ranNumber = (int)Math.floor(Math.random()*(max - min +1));
        String year = String.valueOf(currentYear);
        String randomNumber = String.valueOf(ranNumber);
        StringBuilder accNum = new StringBuilder();
        return accNum.append(year).append(randomNumber).toString();
    }

}
