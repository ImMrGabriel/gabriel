package com.github.immrgabriel.socket;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.Scanner;

public class Socket00 {
    public static void main(String[] args) throws IOException {
        String host = "time-a.timefreq.bldrdoc.gov";
        int port = 13;
        try(Socket socket = new Socket(host, port)) {
            InputStream inputStream = socket.getInputStream();
            Scanner scanner = new Scanner(inputStream);

            while (scanner.hasNext()) {
                System.out.println(scanner.nextLine());
            }
        }

    }
}
