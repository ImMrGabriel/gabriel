package com.github.immrgabriel.prometheus.algorithms.MethodKaratsuba;

import java.math.BigInteger;
import java.util.Map;
import java.util.TreeMap;

public class Karatsuba {

    public static void main(String[] args) {
        executor("1685287499328328297814655639278583667919355849391453456921116729", "7114192848577754587969744626558571536728983167954552999895348492", 105, 72, 12);
        executor("49823261", "44269423", 7, 8);
//        executor("54761293", "65394884", 5);
//        executor("9313685456934674", "7658898761837539", 18, 9, 8);
//        executor("3957322621234423", "7748313756335578", 14, 9, 8);
//        executor("34215432964249374812219364786397", "94541964835273822784327848699719", 45, 9, 8);
//        executor("71611955325935479159397713213124", "93567726499788166681348352945366", 40, 36, 10);
//        executor("8436939677358274975644341226884162349535836199962392872868456892", "3864264464372346883776335161325428226997417338413342945574123327", 72, 69, 67);
//        executor("8711129198194917883527844183686122989894424943636426448417394566", "2924825637132661199799711722273977411715641477832758942277358764", 64, 60, 58);
    }

    private static void executor(String x, String y, final int... find) {
        Map<BigInteger, Integer> map = new TreeMap<BigInteger, Integer>(){{
            for(int i : find) {
                put(new BigInteger(String.valueOf(i)), 0);
            }
        }};
        Map<BigInteger, Integer> mapCurrent = new TreeMap<>(map);
        System.out.println("multiply     = " + multiply(x, y, mapCurrent));
        for(Map.Entry<BigInteger, Integer> e : mapCurrent.entrySet())
            System.out.println(e.getKey() + " -> " + e.getValue());
        mapCurrent = new TreeMap<>(map);
        System.out.println("multiplyPow2 = " + multiplyPow2(x, y, mapCurrent));
        for(Map.Entry<BigInteger, Integer> e : mapCurrent.entrySet())
            System.out.println(e.getKey() + " -> " + e.getValue());
        mapCurrent = new TreeMap<>(map);
        System.out.println("multiply2 = " + multiply2(x, y, mapCurrent));
        for(Map.Entry<BigInteger, Integer> e : mapCurrent.entrySet())
            System.out.println(e.getKey() + " -> " + e.getValue());


    }

    private static BigInteger multiplyPow2(String x, String y, Map<BigInteger, Integer> map) {
        if(x.length() == 1 && y.length() == 1)
            return new BigInteger(x).multiply(new BigInteger(y));
        int lenX = x.length();
        int lenY = y.length();
        if((lenX & (lenX - 1)) != 0) {
            int count = 2;
            while (lenX > count)
                count *= 2;
            x = new String(new char[count - lenX]).replace('\u0000', '0') + x;
            lenX = x.length();
        }
        if((lenY & (lenY - 1)) != 0) {
            int count = 2;
            while (lenY > count)
                count *= 2;
            y = new String(new char[count - lenY]).replace('\u0000', '0') + y;
            lenY = y.length();
        }
        if(lenX != lenY) {
            if(lenX > lenY) {
                y = new String(new char[lenX - lenY]).replace('\u0000', '0') + y;
                lenY = y.length();
            } else {
                x = new String(new char[lenY - lenX]).replace('\u0000', '0') + x;
                lenX = x.length();
            }
        }

        String sA = x.substring(0, lenX / 2);
        String sB = x.substring(lenX / 2, lenX);
        String sC = y.substring(0, lenY / 2);
        String sD = y.substring(lenY / 2, lenY);

        BigInteger AC = multiplyPow2(sA, sC, map);
        BigInteger BD = multiplyPow2(sB, sD, map);
        BigInteger ADBC = multiplyPow2((new BigInteger(sA).add(new BigInteger(sB))).toString(), (new BigInteger(sC).add(new BigInteger(sD))).toString(), map)
                .subtract(AC).subtract(BD);
        if(map.containsKey(ADBC))
            map.put(ADBC, map.get(ADBC) + 1);
        return new BigInteger(AC + new String(new char[lenY]).replace('\u0000', '0')).add(new BigInteger(ADBC + new String(new char[lenY / 2]).replace('\u0000', '0'))).add(BD);

    }

    private static BigInteger multiply(String x, String y, Map<BigInteger, Integer> map) {
        if(x.length() == 1 && y.length() == 1) {
            return new BigInteger(x).multiply(new BigInteger(y));
        }
        if(x.length() > y.length()) {
            if(x.length() % 2 != 0)
                x = "0" + x;
            while (y.length() < x.length())
                y = "0" + y;
        } else if(x.length() < y.length()) {
            if(y.length() % 2 != 0)
                y = "0" + y;
            while (x.length() < y.length())
                x = "0" + x;
        } else {
            if(y.length() % 2 != 0) {
                y = "0" + y;
                x = "0" + x;
            }
        }

        String sA = x.substring(0, x.length() / 2);
        String sB = x.substring(x.length() / 2, x.length());
        String sC = y.substring(0, y.length() / 2);
        String sD = y.substring(y.length() / 2, y.length());

        BigInteger AC = multiply(sA, sC, map);
        BigInteger BD = multiply(sB, sD, map);
        BigInteger ADBC = multiply((new BigInteger(sA).add(new BigInteger(sB))).toString(), (new BigInteger(sC).add(new BigInteger(sD))).toString(), map)
                .subtract(AC).subtract(BD);

        if(map.containsKey(ADBC))
            map.put(ADBC, map.get(ADBC) + 1);
        return new BigInteger("10").pow(y.length()).multiply(AC).add(new BigInteger("10").pow(y.length()/2).multiply(ADBC)).add(BD);
    }

    private static BigInteger multiply2(String x, String y, Map<BigInteger, Integer> map) {
        if(x.length() == 1 || y.length() == 1)
            return new BigInteger(x).multiply(new BigInteger(y));
//        if(x.length() < 2)
//            x = "0" + x;
//        if(y.length() < 2)
//            y = "0" + y;
        int lenX = x.length();
        int lenY = y.length();

        String sA = x.substring(0, lenX / 2);
        String sB = x.substring(lenX / 2, lenX);
        String sC = y.substring(0, lenY / 2);
        String sD = y.substring(lenY / 2, lenY);

        BigInteger AC = multiply2(sA, sC, map);
        BigInteger BD = multiply2(sB, sD, map);
        BigInteger ADBC = multiply2((new BigInteger(sA).add(new BigInteger(sB))).toString(), (new BigInteger(sC).add(new BigInteger(sD))).toString(), map)
                .subtract(AC).subtract(BD);
        if(map.containsKey(ADBC))
            map.put(ADBC, map.get(ADBC) + 1);
        return new BigInteger(AC + new String(new char[lenY]).replace('\u0000', '0')).add(new BigInteger(ADBC + new String(new char[lenY / 2]).replace('\u0000', '0'))).add(BD);

    }
}
