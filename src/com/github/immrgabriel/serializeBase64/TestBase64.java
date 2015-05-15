package com.github.immrgabriel.serializeBase64;

import java.util.Base64;

public class TestBase64 {
    public static void main(String[] args) {
        byte[] encodedBytes = Base64.getEncoder().encode("TEST".getBytes());
        System.out.println("encodedBytes " + new String(encodedBytes));
        byte[] decodedBytes = Base64.getDecoder().decode(encodedBytes);
        System.out.println("decodedBytes " + new String(decodedBytes));
    }
}
