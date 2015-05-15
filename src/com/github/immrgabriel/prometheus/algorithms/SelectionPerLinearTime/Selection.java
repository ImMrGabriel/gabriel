package com.github.immrgabriel.prometheus.algorithms.SelectionPerLinearTime;

public class Selection {

    private static <T extends Comparable<T>> int partition(T[] arr, int left, int right) {
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
        return partition(arr, left, right);
    }

    private static <T extends Comparable<T>> int randomizeSelect(T[] arr, int left, int right, int index) {
        if (index < left || index > right)
            throw new IllegalArgumentException("Search in the wrong range");
        int pivot = randomizePartition(arr, left, right);
        if (pivot == index)
            return pivot;
        if (index < pivot)
            return randomizeSelect(arr, left, pivot - 1, index);
        else
            return randomizeSelect(arr, pivot + 1, right, index);
    }

    public static <T extends Comparable<T>> T[] selectionInHead(T[] arr, int quantity) {
        if (arr == null || arr.length <= quantity)
            return arr;
        T[] clonedArr = arr.clone();
        int index = randomizeSelect(clonedArr, 0, clonedArr.length - 1, clonedArr.length - quantity);
        return java.util.Arrays.copyOfRange(clonedArr, index, clonedArr.length);
    }

    public static <T extends Comparable<T>> T[] selectionInTail(T[] arr, int quantity) {
        if (arr == null || arr.length <= quantity)
            return arr;
        T[] clonedArr = arr.clone();
        randomizeSelect(clonedArr, 0, clonedArr.length - 1, quantity);
        return java.util.Arrays.copyOf(clonedArr, quantity);
    }

    public static <T extends Comparable<T>> T selectionMin(T[] arr) {
        if (arr == null || arr.length == 0)
            throw new IllegalArgumentException("Bad array");
        if(arr.length == 1)
            return arr[0];
        T[] clonedArr = arr.clone();
        randomizeSelect(clonedArr, 0, clonedArr.length - 1, 0);
        return clonedArr[0];
    }

    public static <T extends Comparable<T>> T selectionMax(T[] arr) {
        if (arr == null || arr.length == 0)
            throw new IllegalArgumentException("Bad array");
        if(arr.length == 1)
            return arr[0];
        T[] clonedArr = arr.clone();
        randomizeSelect(clonedArr, 0, clonedArr.length - 1, clonedArr.length - 1);
        return clonedArr[clonedArr.length - 1];
    }

    public static void main(String[] args) {
        java.util.Random rand = new java.util.Random();
        Integer[] arr = new Integer[50];
        for (int i = 0; i < arr.length; i++)
            arr[i] = rand.nextInt(100);
        System.out.println(java.util.Arrays.toString(arr));
        System.out.println(java.util.Arrays.toString(selectionInHead(arr, 10)));
        System.out.println(java.util.Arrays.toString(selectionInTail(arr, 10)));
        System.out.println(selectionMin(arr));
        System.out.println(selectionMax(arr));
        java.util.Arrays.sort(arr);
        System.out.println(java.util.Arrays.toString(arr));

    }
}
