package com.github.immrgabriel.socket;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Socket01 {
    public static void main(String[] args) throws IOException {
        InetAddress ipAddr = InetAddress.getByName("google.com");
        System.out.println(ipAddr);

    }
}
