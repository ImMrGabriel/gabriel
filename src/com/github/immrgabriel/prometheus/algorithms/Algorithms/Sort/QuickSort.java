package com.github.immrgabriel.prometheus.algorithms.Algorithms.Sort;

import java.util.Arrays;

public class QuickSort {
    public static <T extends Comparable<T>> void sort(T[] arr) {
        if (arr == null || arr.length < 2)
            return;
        sort0(arr);
    }

    private static <T extends Comparable<T>> void sort0(T[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    private static <T extends Comparable<T>> void quickSort(T[] arr, int left, int right) {
        if(left < right) {
            int pivot = randomizePartition(arr, left, right);
            quickSort(arr, left, pivot - 1);
            quickSort(arr, pivot + 1, right);
        }
    }

    private static <T extends Comparable<T>> int partition(T[] arr, int left, int right) {
        int pivot = right;
        for (int j = left; j < pivot; j++) {
            if(arr[j].compareTo(arr[pivot]) > 0) {
                T tmp = arr[pivot];
                arr[pivot] = arr[j];
                arr[j--] = arr[--pivot];
                arr[pivot] = tmp;
            }
        }
        return pivot;
    }

    private static <T extends Comparable<T>> int partitionM(T[] arr, int left, int right) {
        T pivot = arr[right];
        int i = left - 1;
        for (int j = left; j < right; j++) {
            if(arr[j].compareTo(pivot) <= 0) {
                T tmp = arr[j];
                arr[j] = arr[++i];
                arr[i] = tmp;
            }
        }
        arr[right] = arr[++i];
        arr[i] = pivot;
        return i;
    }

    private static <T extends Comparable<T>> int partitionMM(T[] arr, int left, int right) {
        T pivot = arr[right];
        for (int j = left--; j < right; j++) {
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

    private static <T extends Comparable<T>> int randomizePartition(T[] arr, int left, int right) {
        int pivot = left + (int)(java.lang.Math.random() * (right - left + 1));
        T tmp = arr[pivot];
        arr[pivot] = arr[right];
        arr[right] = tmp;
        return partitionMM(arr, left, right);
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



}


