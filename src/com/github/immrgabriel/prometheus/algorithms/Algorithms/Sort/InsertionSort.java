package com.github.immrgabriel.prometheus.algorithms.Algorithms.Sort;

import java.util.Arrays;

public class InsertionSort {
    public static <T extends Comparable<T>> void sort(T[] arr) {
        if(arr == null || arr.length < 2)
            return;
//        sort0(arr);
        sortBS(arr);
//        sortBSM(arr);
    }

    private static <T extends Comparable<T>> void sort0(T[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int key = i;
            for (int j = key - 1; j >= 0 && (arr[key].compareTo(arr[j]) < 0); j--) {
                T tmp = arr[j];
                arr[j] = arr[key];
                arr[key] = tmp;
                key = j;
            }
        }
    }

    private static <T extends Comparable<T>> void sortBS(T[] arr) {
        for (int i = 1; i < arr.length; i++) {
            T current = arr[i];
            int index = Arrays.binarySearch(arr, 0, i, arr[i]);
            if(index < 0)
                index = Math.abs(index) - 1;   // -(index + 1)
            if(index != i) {
                System.arraycopy(arr, index, arr, index + 1, i - index);
                arr[index] = current;
            }
        }
    }

    private static <T extends Comparable<T>> void sortBSM(T[] arr) {
        for (int i = 1; i < arr.length; i++) {
            T current = arr[i];
            int index = Arrays.binarySearch(arr, 0, i, arr[i]);
            if(index < 0)
                index = Math.abs(index) - 1;   // -(index + 1)
            if(index != i) {
                if(i - index > 1000)
                    System.arraycopy(arr, index, arr, index + 1, i - index);
                else
                    for (int j = i; j > index ; j--)
                        arr[j] = arr[j - 1];
                arr[index] = current;
            }
        }
    }

    public static <T extends Comparable<T>> void compareTime(T[] arr) {
        sortBS(arr.clone());
        long start = System.currentTimeMillis();
        sortBS(arr.clone());
        System.out.println(System.currentTimeMillis() - start);

        sortBSM(arr.clone());
        start = System.currentTimeMillis();
        sortBSM(arr.clone());
        System.out.println(System.currentTimeMillis() - start);

    }
}


