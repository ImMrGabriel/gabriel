package com.github.immrgabriel.prometheus.algorithms.CountInversion;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SearchCountInversion {
    public static void main(String[] args) throws IOException {
        if(args.length < 2) {
            System.out.println("no arguments");
            return;
        }

        List<Integer[]> list;
        int quantity = 0;
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(args[0]))) {
            String[] str = bufferedReader.readLine().split(" ", 2);
            quantity = Integer.parseInt(str[0]);
            list = new ArrayList<Integer[]>(quantity);
            while (bufferedReader.ready()) {
                String[] strings = bufferedReader.readLine().split(" ", 2)[1].split(" ");
                Integer[] integers = new Integer[strings.length];
                for (int i = 0; i < integers.length; i++)
                    integers[i] = Integer.parseInt(strings[i]);
                list.add(integers);
            }
        }
        int index = Integer.parseInt(args[1]) - 1;
        if(index < 0 || index >= quantity)
            return;



//        System.out.println(MergeSortAndCountInversion.SortAndCountInversionBy(list.get(452-1).clone(), list.get(100-1).clone()));
//        System.out.println(MergeSortAndCountInversion.SortAndCountInversionBy(list.get(100-1).clone(), list.get(452-1).clone()));
//        System.out.println(MergeSortAndCountInversion.SortAndCountInversionBy(list.get(863-1).clone(), list.get(29-1).clone()));
//        System.out.println(MergeSortAndCountInversion.SortAndCountInversionBy(list.get(29-1).clone(), list.get(863-1).clone()));

        System.out.println(MergeSortAndCountInversion.SortAndCountInversionBy(list.get(618-1).clone(), list.get(1-1).clone()));
        System.out.println(MergeSortAndCountInversion.SortAndCountInversionBy(list.get(1-1).clone(), list.get(618-1).clone()));
        System.out.println(MergeSortAndCountInversion.SortAndCountInversionBy(list.get(951-1).clone(), list.get(178-1).clone()));
        System.out.println(MergeSortAndCountInversion.SortAndCountInversionBy(list.get(178-1).clone(), list.get(951-1).clone()));

//        int[] counts = MergeSortAndCountInversion.SortAndCountInversionBy(list, index);
//        for (int i = 0; i < counts.length; i++)
//            System.out.println(String.format("%d %d", i < index ? i + 1 : i + 2, counts[i]));

//        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(args[0]).getParent() + "/output.txt"))) {
//            int count = 1;
//            for(Integer[] x : list) {
//                StringBuilder sb = new StringBuilder(count + " ");
//                for (Integer y : x)
//                    sb.append(y).append(" ");
//                sb.delete(sb.length() - 1, sb.length());
//                bufferedWriter.write("\n");
//                bufferedWriter.write(sb.toString());
//                count++;
//            }
//            bufferedWriter.flush();
//        }
    }

}
