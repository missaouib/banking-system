package com.ironhack.bankingsystem.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


public class PasswordUtil {

    public static String encode(String password) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String passwordEncoded =passwordEncoder.encode(password);
        System.out.println(passwordEncoded);
        return passwordEncoded;
    }



}
