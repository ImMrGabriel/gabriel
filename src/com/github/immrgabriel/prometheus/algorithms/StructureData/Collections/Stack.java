package com.github.immrgabriel.prometheus.algorithms.StructureData.Collections;

public class Stack<E> {
    private E[] array;
    private int current = 0;

    public Stack(Class<E> type, int capacity) {
        array = (E[]) java.lang.reflect.Array.newInstance(type, capacity);
    }

    public Stack(E... array) {
        this.array = array;
        current = array.length;
    }

    public boolean isEmpty() {
        return current == 0;
    }

    public boolean isFull() {
        return current == array.length;
    }

    public int size() {
        return current;
    }

    public boolean push(E element) {
        if(isFull())
            return false;
        array[current++] = element;
        return true;
    }

    public E pop() {
        if(isEmpty())
            return null;
        return array[--current];
    }

    public void clear() {
        current = 0;
    }

    public void setCapacity(int capacity) {
        if(array.length == capacity)
            return;
        array = java.util.Arrays.copyOf(array, capacity);
        if(array.length < capacity)
            capacity = array.length;

    }

    public void trimToSize() {
        array = java.util.Arrays.copyOf(array, size());
    }

    @Override
    public String toString() {
        if(isEmpty())
            return "[]";
        StringBuilder sb = new StringBuilder("[");
        sb.append(array[0]);
        for (int i = 1; i < current; i++) {
            sb.append(',').append(' ').append(array[i]);
        }
        sb.append(']');
        return sb.toString();
    }

    public static void main(String[] args) {
//        Stack<Integer> stack = new Stack<>(Integer.class, 10);
        Stack<Integer> stack = new Stack<Integer>(1,100,211,54,12,24);
//        Stack<Integer> stack = new Stack<>(new Integer[] {1,100,211,54,12,24});
        for (int i = 0; i < 3; i++) {
            System.out.println(stack.pop());
            System.out.println(stack);
        }
        for (int i = 0; i < 5; i++) {
            System.out.println(stack.push(i+100));
            System.out.println(stack);
        }
        stack.setCapacity(10);
        for (int i = 0; i < 10; i++) {
            System.out.println(stack.push(i+200));
            System.out.println(stack);
        }
        for (int i = 0; i < 3; i++) {
            System.out.println(stack.pop());
            System.out.println(stack);
        }
        stack.trimToSize();
        for (int i = 0; i < 3; i++) {
            System.out.println(stack.push(i+300));
            System.out.println(stack);
        }
    }
}


