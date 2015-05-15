package com.github.immrgabriel.prometheus.algorithms.StructureData.Collections;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Arrays;

public class HeapTest {



    @Test
    public void testSetCapacity() throws Exception {
        Heap<Integer> heap = new Heap<>(new Integer[0]);
//        Heap<Integer> heap = new Heap<>(null);
        System.out.println(Arrays.toString(heap.toArray()));
        heap.setCapacity(12);
        heap.add(10);
        heap.add(11);
        System.out.println(Arrays.toString(heap.toArray()));
    }

    @Test
    public void testAdd1() throws Exception {
        Heap<Integer> heap = new Heap<>(new Integer[]{0,1,2,3,4,5,6,7,8,9});
        System.out.println(Arrays.toString(heap.toArray()));
        heap.add(10);
        heap.add(11);
        System.out.println(Arrays.toString(heap.toArray()));
    }

    @Test
    public void testAddMax() throws Exception {
        Heap<Integer> heap = new Heap<>(new Integer[]{4,5,1,3,2,9,8,6,7,0});
        System.out.println(Arrays.toString(heap.toArray()));
        heap.show();
        heap.buildMaxHeap();
        heap.show();
        heap.add(10);
        heap.add(11);
        heap.show();
//        assertEquals(new Integer(11), heap.getRoot());
    }

    @Test
    public void testAddMin() throws Exception {
        Heap<Integer> heap = new Heap<>(new Integer[]{4,5,1,3,2,9,8,6,7,0});
        System.out.println(Arrays.toString(heap.toArray()));
        heap.show();
        heap.buildMinHeap();
        heap.show();
        heap.add(-1);
        heap.add(4);
        heap.show();
//        assertEquals(new Integer(-1), heap.getRoot());
    }

    @Test
    public void testToArray() throws Exception {
        Heap<Integer> heap = new Heap<>(new Integer[]{1});
        System.out.println(Arrays.toString(heap.toArray()));
        assertArrayEquals("size = 1", new Integer[]{1}, heap.toArray());
        heap = new Heap<>(new Integer[0]);
        System.out.println(Arrays.toString(heap.toArray()));
        assertArrayEquals("size = 0", new Integer[0], heap.toArray());
    }

    @Test
    public void testRemoveMax() throws Exception {
        Heap<Integer> heap = new Heap<>(new Integer[]{4,5,1,3,2,9,8,6,7,0});
        System.out.println(Arrays.toString(heap.toArray()));
        heap.show();
        heap.buildMaxHeap();
        heap.show();
        heap.add(10);
        heap.add(11);
        heap.show();
        System.out.println(heap);
        System.out.println(heap.remove(2));
        heap.show();
    }

    @Test
    public void testRemoveMin() throws Exception {
        Heap<Integer> heap = new Heap<>(new Integer[]{4,5,1,3,2,9,8,6,7,0});
        System.out.println(Arrays.toString(heap.toArray()));
        heap.show();
        heap.buildMinHeap();
        heap.show();
        heap.add(-1);
        heap.add(4);
        heap.show();
        System.out.println(heap);
        System.out.println(heap.remove(2));
        heap.show();
    }

    @Test
    public void testSet() throws Exception {
        Heap<Integer> heap = new Heap<>(new Integer[]{4,5,1,3,2,9,8,6,7,0});
        System.out.println(Arrays.toString(heap.toArray()));
        heap.show();
//        heap.buildMinHeap();
        heap.buildMaxHeap();
        heap.show();
        System.out.println(heap);
        System.out.println(heap.set(2, 10));
        heap.show();
        System.out.println(heap);
    }

    @Test
    public void testHeight() throws Exception {
        assertEquals(0, new Heap<>(new Integer[0]).height());
        assertEquals(1, new Heap<>(new Integer[1]).height());
        assertEquals(2, new Heap<>(new Integer[2]).height());
        assertEquals(2, new Heap<>(new Integer[3]).height());
        assertEquals(3, new Heap<>(new Integer[4]).height());
        assertEquals(3, new Heap<>(new Integer[5]).height());
        assertEquals(3, new Heap<>(new Integer[6]).height());
        assertEquals(3, new Heap<>(new Integer[7]).height());
        assertEquals(4, new Heap<>(new Integer[8]).height());
        assertEquals(4, new Heap<>(new Integer[9]).height());
        assertEquals(4, new Heap<>(new Integer[10]).height());
        assertEquals(4, new Heap<>(new Integer[11]).height());
        assertEquals(4, new Heap<>(new Integer[12]).height());
        assertEquals(4, new Heap<>(new Integer[13]).height());
        assertEquals(4, new Heap<>(new Integer[14]).height());
        assertEquals(4, new Heap<>(new Integer[15]).height());
        assertEquals(5, new Heap<>(new Integer[16]).height());
        assertEquals(5, new Heap<>(new Integer[17]).height());
        assertEquals(5, new Heap<>(new Integer[18]).height());
//        for (int i = 0; i < 20; i++) {
//            System.out.println("i = " + i + ", heap.height() = " + new Heap<>(new Integer[i]).height());
//        }
    }

    @Test
    public void testShow() throws Exception {
        Heap<Integer> heap = new Heap<>(new Integer[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32});
//        Heap<Integer> heap = new Heap<>(new Integer[]{0});
//        for (int i = 0; i < 32; i++) {
//            heap.add((i + 1) * 10000);
//        }
        System.out.println(Arrays.toString(heap.toArray()));
        heap.show();
    }

    @Test
    public void testGetMax() throws Exception {
        Heap<Integer> heap = new Heap<>(new Integer[]{4,5,1,3,2,9,8,6,7,0});
        System.out.println(Arrays.toString(heap.toArray()));
        heap.show();
        System.out.println("heap.getMax() = " + heap.getMax());
        System.out.println("heap.getMin() = " + heap.getMin());
        heap.buildMinHeap();
        heap.show();
        System.out.println("heap.getMax() = " + heap.getMax());
        System.out.println("heap.getMin() = " + heap.getMin());
        heap.buildMaxHeap();
        heap.show();
        System.out.println("heap.getMax() = " + heap.getMax());
        System.out.println("heap.getMin() = " + heap.getMin());
    }

    @Test
    public void testExtractMax() throws Exception {
        Heap<Integer> heap = new Heap<>(new Integer[]{4,5,1,3,2,9,8,6,7,0});
        System.out.println(Arrays.toString(heap.toArray()));
        heap.show();
        System.out.println("heap.extractMax() = " + heap.extractMax());
        System.out.println("heap.extractMin() = " + heap.extractMin());
        heap.buildMinHeap();
        heap.show();
        System.out.println("heap.extractMax() = " + heap.extractMax());
        System.out.println("heap.extractMin() = " + heap.extractMin());
        heap.buildMaxHeap();
        heap.show();
        System.out.println("heap.extractMax() = " + heap.extractMax());
        System.out.println("heap.extractMin() = " + heap.extractMin());
        heap.show();
    }
}