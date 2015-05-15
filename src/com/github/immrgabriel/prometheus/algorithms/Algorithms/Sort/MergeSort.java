package com.github.immrgabriel.prometheus.algorithms.Algorithms.Sort;

import java.util.Arrays;

public class MergeSort {
    public static <T extends Comparable<T>> void sort(T[] arr) {
        if (arr == null || arr.length < 2)
            return;
        sort0(arr);
    }

    private static <T extends Comparable<T>> void sort0(T[] arr) {
//        merge(arr, 0, arr.length / 2, arr.length);
//        mergeM(arr, 0, arr.length / 2, arr.length);
        mergeMM(arr, 0, arr.length / 2, arr.length);
    }

    private static <T extends Comparable<T>> void merge(T[] arr, int left, int middle, int right) {
        if (right - middle != 1) {
            int newMiddle = left + ((right - left) >> 1);
            merge(arr, left, left + ((newMiddle - left) >> 1), newMiddle);
            merge(arr, newMiddle, newMiddle + ((right - newMiddle) >> 1), right);
        }
        if (middle - left == 0 || right - middle == 0)
            return;
        int limitL = middle;
        int limitR = right;
//        T[] container = Arrays.copyOfRange(arr, left, right);
        @SuppressWarnings("unchecked")
        T[] container = (T[]) java.lang.reflect.Array.newInstance(arr[left].getClass(), right - left);
        right = middle;
        middle = left;
        int dex = 0;
        while (left < limitL && right < limitR)
            if (arr[right].compareTo(arr[left]) < 0)
                container[dex++] = arr[right++];
            else
                container[dex++] = arr[left++];
        if (left < limitL)
            System.arraycopy(arr, left, container, dex, limitL - left);
        else
            System.arraycopy(arr, right, container, dex, limitR - right);
//        System.out.println("Arrays.toString(container) = " + Arrays.toString(container));
        System.arraycopy(container, 0, arr, middle, container.length);
    }

    private static <T extends Comparable<T>> void mergeM(T[] arr, int left, int middle, int right) {
        if (right - middle != 1) {
            int newMiddle = left + ((right - left) >> 1);
            mergeM(arr, left, left + ((newMiddle - left) >> 1), newMiddle);
            mergeM(arr, newMiddle, newMiddle + ((right - newMiddle) >> 1), right);
        }
        if (middle - left == 0 || right - middle == 0)
            return;
        int limitL = middle;
        int limitR = right;
//        T[] container = Arrays.copyOfRange(arr, left, right);
        @SuppressWarnings("unchecked")
        T[] container = (T[]) java.lang.reflect.Array.newInstance(arr[left].getClass(), right - left);
        right = middle;
        middle = left;
        int dex = 0;
        while (left < limitL && right < limitR)
            if (arr[right].compareTo(arr[left]) < 0)
                container[dex++] = arr[right++];
            else
                container[dex++] = arr[left++];
        if (left < limitL) {
            System.arraycopy(arr, left, arr, middle + dex, limitL - left);
            System.arraycopy(container, 0, arr, middle, dex);
        } else {
            System.arraycopy(arr, right, arr, middle + dex, limitR - right);
            System.arraycopy(container, 0, arr, middle, dex);
        }
    }

    private static <T extends Comparable<T>> void mergeMM(T[] arr, int left, int middle, int right) {
        if (right - middle != 1) {
            int newMiddle = left + ((right - left) >> 1);
            mergeMM(arr, left, left + ((newMiddle - left) >> 1), newMiddle);
            mergeMM(arr, newMiddle, newMiddle + ((right - newMiddle) >> 1), right);
        }
        if (middle - left == 0 || right - middle == 0)
            return;
        int limitL = middle;
        int limitR = right;
//        T[] container = Arrays.copyOfRange(arr, left, right);
        @SuppressWarnings("unchecked")
        T[] container = (T[]) java.lang.reflect.Array.newInstance(arr[left].getClass(), right - left);
        right = middle;
        middle = left;
        int dex = 0;
        while(true) {
            int count = 0;
            if(arr[left].compareTo(arr[right]) < 0) {
                while((left + ++count) < limitL && arr[left + count].compareTo(arr[right]) <= 0);
                System.arraycopy(arr, left, container, dex, count);
                if((left += count) >= limitL) {
                    System.arraycopy(arr, right, arr, middle + dex + count, limitR - right);
                    System.arraycopy(container, 0, arr, middle, dex + count);
                    break;
                }
            } else {
                while((right + ++count) < limitR && arr[right + count].compareTo(arr[left]) <= 0);
                System.arraycopy(arr, right, container, dex, count);
                if((right += count) >= limitR) {
                    System.arraycopy(arr, left, arr, middle + dex + count, limitL - left);
                    System.arraycopy(container, 0, arr, middle, dex + count);
                    break;
                }
            }
            dex += count;
        }
    }


    public static void main(String[] args) {
        java.util.Random rand = new java.util.Random();

        String[] arr = new String[rand.nextInt(1_00_000)];
        for (int i = 0; i < arr.length; i++)
            arr[i] = (char)('a' + rand.nextInt(26)) + "";
//            arr[i] = String.valueOf((char)('a' + rand.nextInt(26)));

//        String[] arrClone = arr;
//        long start = System.currentTimeMillis();
//        merge(arrClone, 0, arr.length / 2, arr.length);
//        System.out.println(System.currentTimeMillis() - start);
//        System.out.println(" -> " + isSort(arrClone));

        String[] arrClone = arr.clone();
//        System.out.println("merge   = " + Arrays.toString(arrClone));
        long start = System.currentTimeMillis();
        merge(arrClone, 0, arr.length / 2, arr.length);
        System.out.println(System.currentTimeMillis() - start);
//        System.out.show("merge   = " + Arrays.toString(arrClone));
        System.out.println(" -> " + isSort(arrClone));

        arrClone = arr.clone();
//        System.out.println("mergeM  = " + Arrays.toString(arrClone));
        start = System.currentTimeMillis();
        mergeM(arrClone, 0, arr.length / 2, arr.length);
        System.out.println(System.currentTimeMillis() - start);
//        System.out.show("mergeM  = " + Arrays.toString(arrClone));
        System.out.println(" -> " + isSort(arrClone));

        arrClone = arr.clone();
//        System.out.println("mergeMM = " + Arrays.toString(arrClone));
        start = System.currentTimeMillis();
        mergeMM(arrClone, 0, arr.length / 2, arr.length);
        System.out.println(System.currentTimeMillis() - start);
//        System.out.show("mergeMM = " + Arrays.toString(arrClone));
        System.out.println(" -> " + isSort(arrClone));
    }


    private static  <T extends Comparable<T>> boolean isSort(T[] arr) {
        T[] arrClone = arr.clone();
        java.util.Arrays.sort(arrClone);
//        System.out.println("arrClone = " + Arrays.toString(arrClone));
//        System.out.println("arr      = " + Arrays.toString(arr));
        for (int i = 0; i < arr.length; i++) {
            if(arr[i].compareTo(arrClone[i]) != 0)
                return false;
        }
        return true;
    }



}


