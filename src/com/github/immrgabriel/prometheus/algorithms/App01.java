package com.github.immrgabriel.prometheus.algorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class App01 {
    public static void main(String[] args) {
        HashMap<String, Integer> map = new HashMap<>();
        ArrayList<String> arr = new ArrayList<>();
        LinkedList<String> list = new LinkedList<>();
        System.out.println(1 << 30);
        for (int i = 0; i < 100; i++) {
            Integer key = -(i + 100000000);
            int h;
            System.out.print((h = key.hashCode()) ^ (h >>> 16));
            System.out.println("   " + ((2000000000 - 1) & h));
        }
    }
}

