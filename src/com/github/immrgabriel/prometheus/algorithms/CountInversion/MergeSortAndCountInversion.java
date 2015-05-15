package com.github.immrgabriel.prometheus.algorithms.CountInversion;

import java.util.ArrayList;
import java.util.List;

public class MergeSortAndCountInversion {

    public static <T extends Comparable<T>> int[] SortAndCountInversionBy(List<T[]> list, int base) {
        T[] arr = list.remove(base);
        datePreparationAll(list, arr, 0, arr.length / 2, arr.length);
        int[] counts = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            int[] localCount = new int[1];
            T[] current = list.get(i);
            mergeMMAndCount(current, localCount, 0, current.length / 2, current.length);
            counts[i] = localCount[0];
        }
        list.add(base, arr);
        return counts;
    }

    public static <T extends Comparable<T>> int SortAndCountInversionBy(T[] base, T[] join) {
        datePreparation(base, join, 0, base.length / 2, base.length);
        int[] counts = new int[1];
        mergeMMAndCount(join, counts, 0, join.length / 2, join.length);
        return counts[0];
    }

    private static <T extends Comparable<T>> void mergeMMAndCount(T[] arr, int[] counts, int left, int middle, int right) {
        if (right - middle != 1) {
            int newMiddle = left + ((right - left) >> 1);
            mergeMMAndCount(arr, counts, left, left + ((newMiddle - left) >> 1), newMiddle);
            mergeMMAndCount(arr, counts, newMiddle, newMiddle + ((right - newMiddle) >> 1), right);
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
                counts[0] = counts[0] + ((limitL - left) * count);
                if((right += count) >= limitR) {
                    System.arraycopy(arr, left, arr, middle + dex + count, limitL - left);
                    System.arraycopy(container, 0, arr, middle, dex + count);
                    break;
                }
            }
            dex += count;
        }
    }

    private static <T extends Comparable<T>> void datePreparationAll(List<T[]> list, T[] arr, int left, int middle, int right) {
        if (right - middle != 1) {
            int newMiddle = left + ((right - left) >> 1);
            datePreparationAll(list, arr, left, left + ((newMiddle - left) >> 1), newMiddle);
            datePreparationAll(list, arr, newMiddle, newMiddle + ((right - newMiddle) >> 1), right);
        }
        if (middle - left == 0 || right - middle == 0)
            return;
        int limitL = middle;
        int limitR = right;
        @SuppressWarnings("unchecked")
        T[] container = (T[]) java.lang.reflect.Array.newInstance(arr[left].getClass(), right - left);
        T[][] containers = (T[][]) java.lang.reflect.Array.newInstance(arr.getClass(), list.size());
        for (int i = 0; i < list.size(); i++) {
            containers[i] = (T[]) java.lang.reflect.Array.newInstance(arr[left].getClass(), right - left);
        }
        right = middle;
        middle = left;
        int dex = 0;
        while(true) {
            int count = 0;
            if(arr[left].compareTo(arr[right]) < 0) {
                while((left + ++count) < limitL && arr[left + count].compareTo(arr[right]) <= 0);
                System.arraycopy(arr, left, container, dex, count);
                for (int i = 0; i < list.size(); i++)
                    System.arraycopy(list.get(i), left, containers[i], dex, count);
                if((left += count) >= limitL) {
                    System.arraycopy(arr, right, arr, middle + dex + count, limitR - right);
                    System.arraycopy(container, 0, arr, middle, dex + count);
                    for (int i = 0; i < list.size(); i++) {
                        System.arraycopy(list.get(i), right, list.get(i), middle + dex + count, limitR - right);
                        System.arraycopy(containers[i], 0, list.get(i), middle, dex + count);
                    }
                    break;
                }
            } else {
                while((right + ++count) < limitR && arr[right + count].compareTo(arr[left]) <= 0);
                System.arraycopy(arr, right, container, dex, count);
                for (int i = 0; i < list.size(); i++)
                    System.arraycopy(list.get(i), right, containers[i], dex, count);
                if((right += count) >= limitR) {
                    System.arraycopy(arr, left, arr, middle + dex + count, limitL - left);
                    System.arraycopy(container, 0, arr, middle, dex + count);
                    for (int i = 0; i < list.size(); i++) {
                        System.arraycopy(list.get(i), left, list.get(i), middle + dex + count, limitL - left);
                        System.arraycopy(containers[i], 0, list.get(i), middle, dex + count);
                    }
                    break;
                }
            }
            dex += count;
        }
    }

    private static <T extends Comparable<T>> void datePreparation(T[] arr, T[] join, int left, int middle, int right) {
        if (right - middle != 1) {
            int newMiddle = left + ((right - left) >> 1);
            datePreparation(arr, join, left, left + ((newMiddle - left) >> 1), newMiddle);
            datePreparation(arr, join, newMiddle, newMiddle + ((right - newMiddle) >> 1), right);
        }
        if (middle - left == 0 || right - middle == 0)
            return;
        int limitL = middle;
        int limitR = right;
        @SuppressWarnings("unchecked")
        T[] container = (T[]) java.lang.reflect.Array.newInstance(arr[left].getClass(), right - left);
        @SuppressWarnings("unchecked")
        T[] containerJoin = (T[]) java.lang.reflect.Array.newInstance(arr[left].getClass(), right - left);        
        right = middle;
        middle = left;
        int dex = 0;
        while(true) {
            int count = 0;
            if(arr[left].compareTo(arr[right]) < 0) {
                while((left + ++count) < limitL && arr[left + count].compareTo(arr[right]) <= 0);
                System.arraycopy(arr, left, container, dex, count);
                System.arraycopy(join, left, containerJoin, dex, count);                
                if((left += count) >= limitL) {
                    System.arraycopy(arr, right, arr, middle + dex + count, limitR - right);
                    System.arraycopy(container, 0, arr, middle, dex + count);                    
                    System.arraycopy(join, right, join, middle + dex + count, limitR - right);
                    System.arraycopy(containerJoin, 0, join, middle, dex + count);                    
                    break;
                }
            } else {
                while((right + ++count) < limitR && arr[right + count].compareTo(arr[left]) <= 0);
                System.arraycopy(arr, right, container, dex, count);
                System.arraycopy(join, right, containerJoin, dex, count);                
                if((right += count) >= limitR) {
                    System.arraycopy(arr, left, arr, middle + dex + count, limitL - left);
                    System.arraycopy(container, 0, arr, middle, dex + count);                    
                    System.arraycopy(join, left, join, middle + dex + count, limitL - left);
                    System.arraycopy(containerJoin, 0, join, middle, dex + count);
                    break;
                }
            }
            dex += count;
        }
    }


    public static void main(String[] args) {
        java.util.Random rand = new java.util.Random();

        String[] arr = new String[rand.nextInt(100)];
        for (int i = 0; i < arr.length; i++)
            arr[i] = (char)('a' + rand.nextInt(26)) + "";

        datePreparationAll(new ArrayList<String[]>(10), arr, 0, arr.length / 2, arr.length);
    }

}
