package com.github.immrgabriel.prometheus.algorithms.RadixPassword;

import com.github.immrgabriel.prometheus.algorithms.Algorithms.Sort.RadixSort;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * Created by gabriel on 20.02.2015.
 */
public class RadixPassword {

    public static void main(String[] args) throws IOException {
        for (int i = 0; i < 26; i++)
            System.out.print((char)('a' + i) + " ");

        // TASK
        System.out.println("Enter file name");
        String[] arr;
        try(BufferedReader readerFileName = new BufferedReader(new InputStreamReader(System.in));
            BufferedReader reader = new BufferedReader(new FileReader(readerFileName.readLine()))) {
            List<String> list = new ArrayList<>(1000);
            String str;
            TreeMap<Character, Integer> map = new TreeMap<>();
            while ((str = reader.readLine()) != null) {
                list.add(str);
                for(char ch : str.toCharArray()) {
                    if(map.containsKey(ch))
                        map.put(ch, map.get(ch) + 1);
                    else
                        map.put(ch, 1);
                }
            }
            arr = new String[list.size()];
            arr = list.toArray(arr);
            System.out.println(map);
        }
        if(arr == null || arr.length < 1)
            return;
        arr = RadixSort.sort(arr, arr[0].length() - 1);
        System.out.println("arr[0] = " + arr[0]);
        System.out.println("arr[arr.length - 1] = " + arr[arr.length - 1]);
    }


}


