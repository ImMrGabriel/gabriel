package com.github.immrgabriel.prometheus.algorithms.StructureData.Collections;

public class Queue<E> {
    private E[] array;
    private int head = 0;
    private int tail = 0;
    private boolean isEmpty = true;

    public Queue(Class<E> type, int capacity) {
        array = (E[]) java.lang.reflect.Array.newInstance(type, capacity);
    }

    public Queue(E... array) {
        this.array = array;
        tail = array.length;
        isEmpty = false;
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public boolean isFull() {
        if(tail == 0 || isEmpty)
            return false;
        return tail % array.length == head;
    }

    public int size() {
        if(isEmpty)
            return 0;
        return head >= tail ? array.length - head + tail : tail - head;
    }

    public boolean enquue(E element) {
        if(isFull())
            return false;
        if(isEmpty)
            isEmpty = false;
        tail = tail % array.length;
        array[tail++] = element;
        return true;
    }

    public E dequeue() {
        if(isEmpty())
            return null;
        E element = array[head];
        head = (head + 1) % array.length;
        if(head == tail || (head == 0 && tail == array.length))
            clear();
        return element;
    }

    public void clear() {
        head = 0;
        tail = 0;
        isEmpty = true;
    }

    public void setCapacity(int capacity) {
        if(array.length == capacity)
            return;
        if(capacity < 1) {
            throw new IllegalArgumentException("capacity <= 0!");
//            array = java.util.Arrays.copyOf(array, 0);
//            clear();
//            return;
        }
        if(isEmpty) {
            array = (E[]) java.lang.reflect.Array.newInstance(array.getClass().getComponentType(), capacity);
        } else {
            trimToSize();
            array = java.util.Arrays.copyOf(array, capacity);
            tail = size();
        }

    }

    public void trimToSize() {
        if(isEmpty) {
            throw new IllegalArgumentException("it is Empty");
//            array = java.util.Arrays.copyOf(array, 0);
//            return;
        }
        if(tail > head) {
            array = java.util.Arrays.copyOfRange(array, head, tail);
        } else {
            E[] tmp = array;
            array = (E[]) java.lang.reflect.Array.newInstance(array.getClass().getComponentType(), size());
            System.arraycopy(tmp, head, array, 0, tmp.length - head);
            System.arraycopy(tmp, 0, array, tmp.length - head, tail);
        }
        head = 0;
        tail = array.length;
    }

    @Override
    public String toString() {
        if(isEmpty())
            return "[]";
        StringBuilder sb = new StringBuilder("[");
        sb.append(array[head]);
        if(head >= tail) {
            for (int i = head + 1; i < array.length; i++)
                sb.append(',').append(' ').append(array[i]);
            for (int i = 0; i < tail; i++)
                sb.append(',').append(' ').append(array[i]);
        } else {
            for (int i = head + 1; i < tail; i++)
                sb.append(',').append(' ').append(array[i]);
        }
        sb.append(']');
        return sb.toString();
    }

//    public static void main(String[] args) {
//        Queue<Integer> queue = new Queue<>(Integer.class, 5);
////        Queue<Integer> queue = new Queue<Integer>(1,2,3,4,5);
////        Queue<Integer> queue = new Queue<>(new Integer[] {1,100,211,54,12,24});
//        for (int i = 0; i < 6; i++) {
//            System.out.show("isFull() = " + queue.isFull() + " ");
//            System.out.show("isEmpty() = " + queue.isEmpty() + " ");
//            System.out.show(queue.enquue(i + 10));
//            System.out.println(queue);
//        }
//        for (int i = 0; i < 3; i++) {
//            System.out.show("isFull() = " + queue.isFull() + " ");
//            System.out.show("isEmpty() = " + queue.isEmpty() + " ");
//            System.out.show(queue.dequeue());
//            System.out.println(queue);
//        }
//        for (int i = 0; i < 6; i++) {
//            System.out.show("isFull() = " + queue.isFull() + " ");
//            System.out.show("isEmpty() = " + queue.isEmpty() + " ");
//            System.out.show(queue.enquue(i + 100));
//            System.out.println(queue);
//        }
//        for (int i = 0; i < 4; i++) {
//            System.out.show("isFull() = " + queue.isFull() + " ");
//            System.out.show("isEmpty() = " + queue.isEmpty() + " ");
//            System.out.show(queue.dequeue());
//            System.out.println(queue);
//        }
//        queue.setCapacity(1);
//
//        for (int i = 0; i < 6; i++) {
//            System.out.show("isFull() = " + queue.isFull() + " ");
//            System.out.show("isEmpty() = " + queue.isEmpty() + " ");
//            System.out.show(queue.enquue(i + 1000));
//            System.out.println(queue);
//        }
////        queue.trimToSize();
////        queue.clear();
//        for (int i = 0; i < 7; i++) {
//            System.out.show("isFull() = " + queue.isFull() + " ");
//            System.out.show("isEmpty() = " + queue.isEmpty() + " ");
//            System.out.show(queue.dequeue());
//            System.out.println(queue);
//        }
//        queue.setCapacity(4);
//        for (int i = 0; i < 6; i++) {
//            System.out.show("isFull() = " + queue.isFull() + " ");
//            System.out.show("isEmpty() = " + queue.isEmpty() + " ");
//            System.out.show(queue.enquue(i + 10000));
//            System.out.println(queue);
//        }
//        for (int i = 0; i < 7; i++) {
//            System.out.show("isFull() = " + queue.isFull() + " ");
//            System.out.show("isEmpty() = " + queue.isEmpty() + " ");
//            System.out.show(queue.dequeue());
//            System.out.println(queue);
//        }
//    }
}


