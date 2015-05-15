package com.github.immrgabriel.socket;

import java.io.IOException;
import java.net.InetAddress;

public class Socket02 {
    public static void main(String[] args) throws IOException {
        InetAddress[] ipAddrs = InetAddress.getAllByName("google.com");
        for(InetAddress address : ipAddrs) {
            System.out.println(address);
        }

    }
}
