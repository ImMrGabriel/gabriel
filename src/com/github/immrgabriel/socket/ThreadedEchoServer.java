package com.github.immrgabriel.socket;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ThreadedEchoServer {
    public static void main(String[] args) {
        int count = 1;
        try(ServerSocket server = new ServerSocket(8189)) {
            while (true) {
                final Socket client = server.accept();
//                factoryMethod.setSoTimeout(10000);
                final int number = count;
                System.out.println("Spawning " + count);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            try {
                                Scanner in = new Scanner(client.getInputStream());
                                PrintWriter out = new PrintWriter(client.getOutputStream(), true);

                                out.println("Hello! Enter EXIT to exit");
                                while(in.hasNext()) {
                                    String line = in.nextLine();
                                    if("exit".equalsIgnoreCase(line))
                                        break;
                                    out.println("Echo for " + number + ": " + line);
                                }
                                System.out.println(number + " was kill");
                            } finally {
                                client.close();
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
                count++;
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
