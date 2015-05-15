package com.github.immrgabriel.prometheus.algorithms.Algorithms.Sort;

import java.util.Arrays;
import java.util.Random;

public class TestSort {
    public static void main(String[] args) {
        for (int i = 0; i < 26; i++)
            System.out.print((char)('a' + i) + " ");
        System.out.println();
        Random rand = new Random();
        String[] arr = new String[10000];
        for (int i = 0; i < arr.length; i++)
            arr[i] = (char)('a' + rand.nextInt(26)) + ""/* + (char)('a' + rand.nextInt(26))*/;

//        InsertionSort.sort(arr.clone());
        String[] tmp = arr.clone();
//        Arrays.sort(tmp);
        long start = System.currentTimeMillis();
        InsertionSort.sort(tmp);
        System.out.println(System.currentTimeMillis() - start);

//        BubleSort.sort(arr.clone());
        tmp = arr.clone();
////        Arrays.sort(tmp);
        start = System.currentTimeMillis();
        BubleSort.sort(tmp);
        System.out.println(System.currentTimeMillis() - start);

//        MergeSort.sort(arr.clone());
        tmp = arr.clone();
//        Arrays.sort(tmp);
        start = System.currentTimeMillis();
        MergeSort.sort(tmp);
        System.out.println(System.currentTimeMillis() - start);
    }
}