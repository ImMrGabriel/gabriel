package com.github.immrgabriel.prometheus.algorithms.Algorithms.Sort;

import java.util.Comparator;
import java.util.TreeMap;

public class CountingSort {
    public static int[] sort(int[] arr) {
        if (arr == null || arr.length < 2)
            return arr;
        int[] minmax = minmax(arr);
        return sort(arr, minmax[0], minmax[1]);
    }

    private static int[] minmax(int[] arr) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for(int e : arr) {
            if (e < min)
                min = e;
            if(e > max)
                max = e;
        }
        return new int[]{min, max};
    }

    public static int[] sort(int[] arr, int min, int max) {
        if (arr == null || arr.length < 2 || min >= max)
            return arr;
        int[] counts = new int[max - min + 1];          // 0) Tetta(k)
        int[] container = new int[arr.length];          // 1) Tetts(n)
        int first = Math.abs(min);
        for(int e : arr)
            counts[first + e]++;                        // 2) Tetta(n)
        for (int i = 1; i < counts.length; i++) 
            counts[i] += counts[i-1];                   // 3) Tetta(k)
        for (int i = container.length - 1; i > -1 ; i--) {
            int e = arr[i];
            container[counts[first + e]-- - 1] = e;     // 4) Tetta(n)
        }
        return container;
    }

    public static <T extends Comparable<T>> T[] sort(T[] arr) {
        return sort0(arr, new TreeMap<>());
    }

    public static <T> T[] sort(T[] arr, Comparator<? super T> cmp) {
        return sort0(arr, new TreeMap<>(cmp));
    }

    private static <T> T[] sort0(T[] arr, TreeMap<T, Integer> map) {
        if (arr == null || arr.length < 2)
            return arr;
        T[] container = (T[]) java.lang.reflect.Array.newInstance(arr[0].getClass(), arr.length);
        for(T e : arr)
            if(map.containsKey(e))
                map.put(e, map.get(e) + 1);
            else map.put(e, 1);
        T prev = map.firstKey();
        for(T key : map.tailMap(prev, false).keySet()) {
            map.put(key, map.get(key) + map.get(prev));
            prev = key;
        }
        for (int i = container.length - 1; i > -1 ; i--) {
            T key = arr[i];
            container[map.get(key) - 1] = key;
            map.put(key, map.get(key) - 1);
        }
        return container;
    }
}


