package com.github.immrgabriel.prometheus.algorithms.Algorithms.Sort;

import java.util.Arrays;

public class BubleSort {
    public static <T extends Comparable<T>> void sort(T[] arr) {
        if(arr == null || arr.length < 2)
            return;
//        sort0(arr);
        sort0M(arr);

    }

    private static <T extends Comparable<T>> void sort0(T[] arr) {
//        System.out.println(Arrays.toString(arr));
        for (int i = 0; i < arr.length - 1; i++) {
            int k = 0;
            int j = 1;
            while (j < arr.length) {
                if (arr[j].compareTo(arr[k]) < 0) {
                    T tmp = arr[j];
                    arr[j] = arr[k];
                    arr[k] = tmp;
//                    char[] buble = new char[arr.length];
//                    buble[k] = '|';
//                    buble[j] = '|';
//                    System.out.println(Arrays.toString(buble));
//                    System.out.println(Arrays.toString(arr) + ": " + k + "<->" + j + " , i = " + i);
                }
                k = j++;
            }
        }
    }

    private static <T extends Comparable<T>> void sort0M(T[] arr) {
        boolean yet = true;
        while(yet) {
            yet = false;
            for (int i = 0, j = 1; j < arr.length; i = j++) {
                if (arr[j].compareTo(arr[i]) < 0) {
                    T tmp = arr[j];
                    arr[j] = arr[i];
                    arr[i] = tmp;
                    yet = true;
                }
            }
        }
    }
}


