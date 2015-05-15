package com.github.immrgabriel.prometheus.algorithms.MethodKaratsuba;

import java.math.BigInteger;
import java.util.Map;
import java.util.TreeMap;

public class KaratsubaTest {

    public static void main(String[] args) {
        Map<BigInteger, Integer> map = new TreeMap<>();
        map.put(new BigInteger("10"), 0);
        map.put(new BigInteger("15"), 0);
        test(map, 3);
        System.out.println(map);
    }

    private static void test(Map<BigInteger, Integer> map, int count) {
        if(count == 0)
            return;
        for (int i = 0; i < 20; i++) {
            BigInteger temp = new BigInteger(String.valueOf(i));
            if(map.containsKey(temp))
                map.put(temp, map.get(temp) + 1);
        }
        test(map, count - 1);
    }

}
