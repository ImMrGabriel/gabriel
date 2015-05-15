package com.github.immrgabriel.prometheus.algorithms.Algorithms.Sort;

import java.util.Comparator;

/**
 * Created by gabriel on 20.02.2015.
 */
public class RadixSort {
    public static String[] sort(String[] arr, int radix) {
        if (arr == null || arr.length < 2 || radix < 0)
            return arr;
        String[] container = arr;
        for (int i = radix; i >= 0; i--) {
            final int r = i;
            container = CountingSort.sort(container, new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    return o1.charAt(r) - o2.charAt(r);
                }
            });
//            System.out.println("step[" + i + "] = " + container[0]);    //TASK
        }
        return container;
    }


//    public static void main(String[] args) throws IOException {
//        for (int i = 0; i < 26; i++)
//            System.out.show((char)('a' + i) + " ");
//        System.out.println();
//        java.util.Random rand = new java.util.Random();
////        String[] arr = new String[rand.nextInt(10)];
//        String[] arr = new String[10];
//        for (int i = 0; i < arr.length; i++)
//            arr[i] = (char)('a' + rand.nextInt(26)) + "" + (char)('a' + rand.nextInt(26)) + "" + (char)('a' + rand.nextInt(26));
////            arr[i] = rand.nextInt(10) + "" + rand.nextInt(10) + "" + rand.nextInt(10);
//
//        System.out.println(" Raw array     - > " + Arrays.toString(arr));
//        System.out.println(" Radix.sort    - > " + Arrays.toString(sort(arr, arr[0].length() - 1)));
//        Arrays.sort(arr);
//        System.out.println("Arrays.sort    - > " + Arrays.toString(arr));
//    }


}


