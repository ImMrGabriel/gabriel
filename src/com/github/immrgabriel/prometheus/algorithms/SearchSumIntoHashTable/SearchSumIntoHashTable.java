package com.github.immrgabriel.prometheus.algorithms.SearchSumIntoHashTable;

import com.github.immrgabriel.prometheus.algorithms.StructureData.Collections.HashMap;
import org.junit.Test;
import static org.junit.Assert.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;


public class SearchSumIntoHashTable {

    @Test
    public void testQuickSearchSum() throws Exception{
        long startTime = System.currentTimeMillis();
//        String pathFile = "./src/com/github/immrgabriel/prometheus/algorithms/SearchSumIntoHashTable/test_06.txt";
        String pathFile = "./src/com/github/immrgabriel/prometheus/algorithms/SearchSumIntoHashTable/input_06.txt";
        File pFile = new File(pathFile);
        HashMap<Long, String> map = new HashMap<>(2 << 19, 3);
        try (BufferedReader reader = new BufferedReader(new FileReader(pFile))) {
            String current;
            while ((current = reader.readLine()) != null) {
                map.put(Long.parseLong(current), null);
            }
        }
        System.out.println("FillTime = " + (System.currentTimeMillis() - startTime)/1000.);
        startTime = System.currentTimeMillis();
        final int sMin = -1000;
        final int sMax = 1000;
        HashMap<Long, String> count = new HashMap<>(64);
        Iterator<Map.Entry<Long, String>> it = map.iterator();
        while (it.hasNext()) {
            long current = it.next().getKey();
            long length = sMax - current + 1;
            for (long i = sMin - current; i < length; i++) {
                if(map.containsKey(i)) {
                    count.put(i + current, null);
                    break;
                }
            }
        }
        System.out.println("count = " + count.size());
        System.out.println("SearchTime = " + (System.currentTimeMillis() - startTime)/1000.);
    }

}


