package com.github.immrgabriel.prometheus.algorithms.CountCompareQuickSort;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class CountCompareQuickSort {

    private static <T extends Comparable<T>> int quickSort(T[] arr, int left, int right, int[] count) {
        if(left < right) {
            int pivot = partition(arr, left, right, count);
            quickSort(arr, left, pivot - 1, count);
            quickSort(arr, pivot + 1, right, count);
        }
        return count[0];
    }

    private static <T extends Comparable<T>> int quickSortLeft(T[] arr, int left, int right, int[] count) {
        if(left < right) {
            int pivot = substitutePartitionLeft(arr, left, right, count);
            quickSortLeft(arr, left, pivot - 1, count);
            quickSortLeft(arr, pivot + 1, right, count);
        }
        return count[0];
    }

    private static <T extends Comparable<T>> int quickSortMiddle(T[] arr, int left, int right, int[] count) {
        if(left < right) {
            int pivot = substitutePartitionMiddle(arr, left, right, count);
            quickSortMiddle(arr, left, pivot - 1, count);
            quickSortMiddle(arr, pivot + 1, right, count);
        }
        return count[0];
    }

    private static <T extends Comparable<T>> int substitutePartitionLeft(T[] arr, int left, int right, int[] count) {
        T tmp = arr[left];
        arr[left] = arr[right];
        arr[right] = tmp;
        return partition(arr, left, right, count);
    }

    private static <T extends Comparable<T>> int substitutePartitionMiddle(T[] arr, int left, int right, int[] count) {
        int middle = (left + right) >>> 1;
        if((arr[middle].compareTo(arr[left]) <= 0 && arr[left].compareTo(arr[right]) <= 0) || (arr[right].compareTo(arr[left]) <= 0 && arr[left].compareTo(arr[middle]) <= 0)) {
            middle = left;
        } else if((arr[left].compareTo(arr[right]) <= 0 && arr[right].compareTo(arr[middle]) <= 0) || (arr[middle].compareTo(arr[right]) <= 0 && arr[right].compareTo(arr[left]) <= 0)) {
            middle = right;
        }
        T tmp = arr[middle];
        arr[middle] = arr[right];
        arr[right] = tmp;
        return partition(arr, left, right, count);
    }

    private static <T extends Comparable<T>> int partition(T[] arr, int left, int right, int[] count) {
        T pivot = arr[right];
        for (int j = left--; j < right; j++) {
            count[0]++;
            if(arr[j].compareTo(pivot) <= 0) {
                T tmp = arr[j];
                arr[j] = arr[++left];
                arr[left] = tmp;
            }
        }
        arr[right] = arr[++left];
        arr[left] = pivot;
        return left;
    }

    static  <T extends Comparable<T>> boolean isSort(T[] arr) {
        T[] arrClone = arr.clone();
        Arrays.sort(arrClone);
//        System.out.println("arrClone = " + Arrays.toString(arrClone));
//        System.out.println("arr      = " + Arrays.toString(arr));
        for (int i = 0; i < arr.length; i++) {
            if(arr[i].compareTo(arrClone[i]) != 0)
                return false;
        }
        return true;
    }

    public static void main(String[] args) throws IOException{
        Integer[] arr;
        System.out.println("Enter input file: ");
        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            Scanner scanner = new Scanner(new BufferedInputStream(new FileInputStream(bufferedReader.readLine())))) {
            int length = scanner.hasNext() ? scanner.nextInt() : 0;

            arr = new Integer[length];
            for (int i = 0; scanner.hasNext() && i < length; i++) {
                arr[i] = scanner.nextInt();
            }
        }
        Integer[] clonedArr;
//        System.out.println("arr = " + Arrays.toString(arr));
        clonedArr = arr.clone();
        System.out.println("quickSort(arr,0,arr.length - 1) = " + quickSort(clonedArr = arr.clone(), 0, arr.length - 1, new int[1]));
//        System.out.println("arr = " + Arrays.toString(clonedArr));
        System.out.println("quickSortLeft(arr,0,arr.length - 1) = " + quickSortLeft(clonedArr = arr.clone(), 0, arr.length - 1, new int[1]));
//        System.out.println("arr = " + Arrays.toString(clonedArr));
        System.out.println("quickSortMiddle(arr,0,arr.length - 1) = " + quickSortMiddle(clonedArr = arr.clone(), 0, arr.length - 1, new int[1]));
//        System.out.println("arr = " + Arrays.toString(clonedArr));

    }



}


