package com.github.immrgabriel.prometheus.algorithms.Algorithms.Sort;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class TestCountingSort {
    public static void main(String[] args) {
//        Random rand = new Random();
//        int[] arr = new int[rand.nextInt(50)];
//        for (int i = 0; i < arr.length; i++)
//            arr[i] = rand.nextInt(2) == 0 ? -rand.nextInt(20) : rand.nextInt(20);
////        int[] arr = {-2,5,3,0,2,3,0,3};
//        System.out.println("   Raw array   - > " + Arrays.toString(arr));
//        System.out.println("Counting.sort  - > " + Arrays.toString(CountingSort.sort(arr)));
//        Arrays.sort(arr);
//        System.out.println("  Arrays.sort  - > " + Arrays.toString(arr));

//        Random rand = new Random();
//        Integer[] arr = new Integer[rand.nextInt(50)];
//        for (int i = 0; i < arr.length; i++)
//            arr[i] = rand.nextInt(2) == 0 ? -rand.nextInt(20) : rand.nextInt(20);
////        Integer[] arr = {2,5,3,0,2,3,0,3};
//        System.out.println("   Raw array     - > " + Arrays.toString(arr));
//        System.out.println("Counting.sort    - > " + Arrays.toString(CountingSort.sort(arr)));
//        Arrays.sort(arr);
//        System.out.println("  Arrays.sort    - > " + Arrays.toString(arr));
//        System.out.println("Counting.sortInv - > " + Arrays.toString(CountingSort.sort(arr, new Comparator<Integer>() {
//            @Override
//            public int compare(Integer o1, Integer o2) {
//                return o2 - o1;
//            }
//        })));

        for (int i = 0; i < 26; i++)
            System.out.print((char)('a' + i) + " ");
        System.out.println();
        Random rand = new Random();
        String[] arr = new String[rand.nextInt(50)];
        for (int i = 0; i < arr.length; i++)
            arr[i] = (char)('a' + rand.nextInt(26)) + "";
        System.out.println("   Raw array     - > " + Arrays.toString(arr));
        System.out.println("Counting.sort    - > " + Arrays.toString(CountingSort.sort(arr)));
        Arrays.sort(arr);
        System.out.println("  Arrays.sort    - > " + Arrays.toString(arr));
        System.out.println("Counting.sortInv - > " + Arrays.toString(CountingSort.sort(arr, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.compareTo(o1);
            }
        })));
    }
}