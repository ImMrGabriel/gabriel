package com.github.immrgabriel.prometheus.algorithms.Algorithms.Sort;

import java.util.Arrays;
import java.util.Random;

public class TestQuickSort {
    public static void main(String[] args) {
        for (int i = 0; i < 26; i++)
            System.out.print((char)('a' + i) + " ");
        System.out.println();
        Random rand = new Random();
        String[] arr = new String[rand.nextInt(50)];
        for (int i = 0; i < arr.length; i++)
            arr[i] = (char)('a' + rand.nextInt(26)) + ""/* + (char)('a' + rand.nextInt(26))*/;
//        Integer[] arr = {3,7,8,5,2,1,9,5,4};
        System.out.println("Raw array   - > " + Arrays.toString(arr));
        QuickSort.sort(arr);
        System.out.println("Quick.sort  - > " + Arrays.toString(arr));
        System.out.println(QuickSort.isSort(arr));

    }
}