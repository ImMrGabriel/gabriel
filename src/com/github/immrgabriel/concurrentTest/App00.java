package com.github.immrgabriel.concurrentTest;

import org.junit.Test;
import static org.junit.Assert.*;

public class App00 {
    private volatile boolean flag = false;
    private int var;

    public void tasks() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                var = 3;
                var = 4;
                var = 5;
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(!flag) {
//                    System.out.print(var);
                }
//                System.out.println();
                System.out.print(var);
            }
        }).start();
        new Thread(new Runnable() {
            @Override
        public void run() {
                var = 2;
                flag = true;
            }
        }).start();
    }
    public static void main(String[] args) {
//        new App00().tasks();   // output 0, 1, 2, 3, 4, 5 all
        for (int i = 0; i < 100; i++) {
            new App00().tasks();
        }
    }


}
