package com.vitavehiculum.web.service;

import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class CryptoService {
    public byte[] hash(byte[] input){

        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            return digest.digest(input);

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
