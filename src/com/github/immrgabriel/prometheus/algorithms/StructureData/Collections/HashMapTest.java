package com.github.immrgabriel.prometheus.algorithms.StructureData.Collections;

import org.junit.Test;

import java.util.Iterator;
import java.util.Map;

import static org.junit.Assert.*;

public class HashMapTest {

    @Test
    public void testPut() throws Exception {
        HashMap<String, String> map = new HashMap<>(16, 2.0f);
        assertEquals(null,map.put("0","0"));
        assertEquals(null,map.put("1","1"));
        assertEquals(null,map.put("2","2"));
        assertEquals(null,map.put("3","3"));
        assertEquals(4, map.size());
        assertEquals("2", map.put("2", "4"));
        assertEquals(4, map.size());
        for (int i = 0; i < 20; i++) {
            map.put(i + "", i + "");
        }
        assertEquals(20, map.size());
        for (int i = 0; i < 20; i++) {
            map.put(i + "*", i + "*");
        }
        map.printMap();
    }

    @Test
    public void testGet() throws Exception {
        HashMap<String, String> map = new HashMap<>();
        for (int i = 0; i < 20; i++) {
            map.put(i + "", i + "");
        }
        for (int i = 0; i < 20; i++) {
            assertEquals(i+"", map.get(i+""));
        }
        assertEquals(null, map.get(null));
    }

    @Test
    public void testRemove() throws Exception {
        HashMap<String, String> map = new HashMap<>();
        for (int i = 0; i < 20; i++) {
            map.put(i + "", i + "");
        }
        map.printMap();
        System.out.println("map.size() = " + map.size());
        int size = map.size();
        assertEquals("3", map.get("3"));
        map.remove("3");
        assertEquals(null, map.get("3"));
        assertEquals(size - 1, map.size());

        assertEquals("7", map.get("7"));
        map.remove("7");
        assertEquals(null, map.get("7"));
        assertEquals(size - 2, map.size());

        assertEquals("13", map.get("13"));
        map.remove("13");
        assertEquals(null, map.get("13"));
        assertEquals(size - 3, map.size());
        map.printMap();
        System.out.println("map.size() = " + map.size());
    }

    @Test
    public void testIterator() throws Exception {
        HashMap<String, String> map = new HashMap<>();
        for (int i = 0; i < 20; i++) {
            map.put(i + "", i + "");
        }
        map.printMap();
        System.out.println("map.size() = " + map.size());
        Map.Entry<String, String>[] array = map.toArray();
        System.out.println(java.util.Arrays.toString(array));
        Iterator<Map.Entry<String, String>> it = map.iterator();
        int count = 0;
        while(it.hasNext()) {
            Map.Entry<String, String> current = it.next();
            assertEquals(array[count].getKey() + "<=" + array[count].getValue(), current.getKey() + "<=" + current.getValue());
            count++;
        }
        it = map.iterator();
        while(it.hasNext()) {
            Map.Entry<String, String> current = it.next();
            if("3".equals(current.getKey()) || "7".equals(current.getKey()) || "13".equals(current.getKey()) || "6".equals(current.getValue())
                    || "14".equals(current.getValue()))
                it.remove();
        }
        array = map.toArray();
        System.out.println(java.util.Arrays.toString(array));
        it = map.iterator();
        count = 0;
        while(it.hasNext()) {
            Map.Entry<String, String> current = it.next();
            assertEquals(array[count].getKey() + "<=" + array[count].getValue(), current.getKey() + "<=" + current.getValue());
            count++;
        }
        map.printMap();
        System.out.println("map.size() = " + map.size());
    }
}