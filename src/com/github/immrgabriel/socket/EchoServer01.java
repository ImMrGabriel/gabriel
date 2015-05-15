package com.github.immrgabriel.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class EchoServer01 {
    public static void main(String[] args) throws IOException {
        try(ServerSocket server = new ServerSocket(8189)) {
            lExit: while (true) {
                // blocking and waiting ONE factoryMethod
                try (Socket client = server.accept()) {
                    InputStream inStream = client.getInputStream();
                    OutputStream outStream = client.getOutputStream();

                    try (Scanner in = new Scanner(inStream)) {
                        PrintWriter out = new PrintWriter(outStream, true);         //true - auto flush
                        out.println("Hello! Enter EXIT to exit");

                        while (in.hasNext()) {
                            String request = in.nextLine();
                            if ("exit".equalsIgnoreCase(request))
                                break;
                            if ("exit!".equalsIgnoreCase(request))
                                break lExit;
                            out.println("Echo: " + request);
                        }
                    }
                }

            }
        }
    }
}
