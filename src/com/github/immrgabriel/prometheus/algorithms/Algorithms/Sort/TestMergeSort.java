package com.github.immrgabriel.prometheus.algorithms.Algorithms.Sort;

import java.util.Arrays;
import java.util.Random;

public class TestMergeSort {
    public static void main(String[] args) {
        for (int i = 0; i < 26; i++)
            System.out.print((char)('a' + i) + " ");
        System.out.println();
        Random rand = new Random();
        String[] arr = new String[rand.nextInt(50)];
        for (int i = 0; i < arr.length; i++)
            arr[i] = (char)('a' + rand.nextInt(26)) + ""/* + (char)('a' + rand.nextInt(26))*/;
        System.out.println("Raw array   - > " + Arrays.toString(arr));
        String[] arrClone = arr.clone();
        MergeSort.sort(arrClone);
        System.out.println("Merge.sort  - > " + Arrays.toString(arrClone));
        Arrays.sort(arr);
        System.out.println("Arrays.sort - > " + Arrays.toString(arr));

    }
}