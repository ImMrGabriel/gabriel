package com.github.immrgabriel.prometheus.algorithms.StructureData.Collections;

import java.util.Collection;
import java.util.Iterator;

/**
 *    tail                                head
 * -> elem4 -> elem3 -> elem2 -> elem1 -> elem0 -> null
 */
public class SingleLinkedList<E> {
    private Node<E> tail;
    private int size = 0;

    private static class Node<E> {
        E item;
        Node<E> next;

        Node(E item, Node<E> next) {
            this.item = item;
            this.next = next;
        }
    }

    public SingleLinkedList() {
    }

    public SingleLinkedList(Collection<? extends E> collection) {
        this();
        add(collection);
    }

    public SingleLinkedList(E... array) {
        this();
        add(array);
    }

    private Node<E> jump(int index) {
        if(tail == null || index < 0 || index >= size)
            throw new IllegalArgumentException(tail == null ? "tail == null" : index < 0 ? "index < 0" : "index >= size");
        Node<E> current = tail;
        for (int i = index + 1; i < size; i++)
            current = current.next;
        return current;
    }

    public Node<E> getHead() {
        if(tail == null)
            return null;
        Node<E> current = tail;
        while(current.next != null)
            current = current.next;
        return current;
    }

    public Node<E> getTail() {
        return tail;
    }

    public void setTail(Node<E> tail) {
        this.tail = tail;
        size = size();
    }

    public int size() {
        return sizeRec(tail);
//        return sizeIter(tail);
    }

    public int sizeRec(Node<E> tail) {
        if(tail == null)
            return 0;
        return 1 + sizeRec(tail.next);
    }

    public int sizeIter(Node<E> tail) {
        int count = 0;
        while (tail != null) {
            tail = tail.next;
            count++;
        }
        return count;
    }

    public void add(E item) {
        tail = new Node<>(item, tail);
        size++;
    }

    public void add(Node<E> tail) {
        if(tail == null)
            return;
        if(this.tail != null) {
            Node<E> end = tail;
            while(end.next != null)
                end = end.next;
            end.next = this.tail;
        }
        setTail(tail);
    }

    public void add(Collection<? extends E> collection) {
        Iterator<? extends E> iterator = collection.iterator();
        while (iterator.hasNext())
            add(iterator.next());
    }

    public void add(E[] array) {
        for(E item : array)
            add(item);
    }

    public void insert(int index, E item) {
        if(index  >= size) {
            add(item);
            return;
        }
        if(index < 0)
            index = 0;
        Node<E> current = jump(index);
        current.next = new Node<>(item, current.next);
        size++;
    }

    public E get(int index) {
        return jump(index).item;
    }

    public E getFirst() {
        return get(0);
    }

    public E getLast() {
        return get(size - 1);
    }

    public void set(int index, E item) {
        jump(index).item = item;
    }

    public E remove(int index) {
        if(index + 1 == size) {
            E result = tail.item;
            tail = tail.next;
            size--;
            return result;
        }
        Node<E> current = jump(index + 1);
        E result = current.next.item;
        current.next = current.next.next;
        size--;
        return result;
    }

    public E removeFirst() {
        return remove(0);
    }

    public E removeLast() {
        return remove(size - 1);
    }

    public void push(E item) {
        add(item);
    }

    public E pop() {
        return removeLast();
    }

    public Node<E> getInversionList() {
        return getInversionListRec(tail);
    }

    public Node<E> getInversionListRec(Node<E> node) {
        return node == null ? null : new Node<>(node.item, getInversionListRec(node.next));
    }

    public  Node<E> copyRec(Node<E> tail) {
        return tail == null ? null : new Node(tail.item, copyRec(tail.next));
    }

    public E[] toArray(E[] a) {
        if(a == null)
            throw new IllegalArgumentException("Bad argument");
        @SuppressWarnings("unchecked")
        E[] array = (E[]) java.lang.reflect.Array.newInstance(a.getClass().getComponentType(), size);
        Node<E> current = tail;
        int count = size - 1;
        while(current != null) {
            array[count--] = current.item;
            current = current.next;
        }
        return array;
    }

    public E[] toArray(E[] a, Node<E> tail) {
        if(a == null)
            throw new IllegalArgumentException("Bad argument");
        int count = sizeRec(tail);
        @SuppressWarnings("unchecked")
        E[] array = (E[]) java.lang.reflect.Array.newInstance(a.getClass().getComponentType(), count);
        Node<E> current = tail;
        count--;
        while(current != null) {
            array[count--] = current.item;
            current = current.next;
        }
        return array;
    }

    public E searchElement(E key) {
        if(key == null)
            throw new IllegalArgumentException("key == null");
        Node<E> current = tail;
        while(current != null && !key.equals(current.item))
            current = current.next;
        return current == null ? null : current.item;
    }

    public int search(E key) {
        if(key == null)
            return -1;
        Node<E> current = tail;
        int index = size - 1;
        while(current != null && !key.equals(current.item)) {
            current = current.next;
            index--;
        }
        return index;
    }

    public E searchElement(Node<E> tail, E key) {
        if(key == null)
            throw new IllegalArgumentException("key == null");
        while(tail != null && !key.equals(tail.item))
            tail = tail.next;
        return tail == null ? null : tail.item;
    }

    public int search(Node<E> tail, E key) {
        if(key == null)
            return -1;
        int index = sizeRec(tail) - 1;
        while(tail != null && !key.equals(tail.item)) {
            tail = tail.next;
            index--;
        }
        return index;
    }

    @Override
    public String toString() {
        return toStringRec(tail);
    }

    public String toStringRec(Node<E> node) {
        return node == null ? "*" : toStringRec(node.next) + "->" + node.item;
    }

    public String toStringRecInv(Node<E> node) {
        return node == null ? "*" : node.item + "<-" + toStringRecInv(node.next);
    }

    public String toStringIterInv(Node<E> node) {
        StringBuilder sb = new StringBuilder();
        while (node != null) {
            sb.append(node.item).append("<-");
            node = node.next;
        }
        return sb.append("*").toString();
    }

//    public static void main(String[] args) {
//        SingleLinkedList<Integer> list = new SingleLinkedList<>();
//        for (int i = 0; i < 5; i++) {
//            list.add(i);
//        }
//        System.out.println(list);
//        System.out.println(list.toStringRecInv(list.getTail()));
//        System.out.println(list.toStringIterInv(list.getTail()));
//        System.out.println(list.toStringRec(list.getInversionList()));
//        System.out.println(list.toStringIterInv(list.getInversionList()));
//        System.out.println(list.toStringRec(list.getInversionListRec(list.tail.next.next)));
//        System.out.println(list.size());
//        System.out.println(list.sizeRec(list.tail.next.next));
//        System.out.println("list.getHead().item = " + list.getHead().item);
//        System.out.println("list.getTail().item = " + list.getTail().item);
////        list.setTail(list.tail.next.next);
////        System.out.println(list.size());
////        System.out.println(list);
////        System.out.println(list.toStringRecInv(list.getTail()));
//        list.add(list.getInversionListRec(list.tail.next.next));
//        System.out.println(list + ", size = " + list.size);
//        System.out.println(list.remove(1));
//        System.out.println(list + ", size = " + list.size);
//        System.out.println(list.remove(2));
//        System.out.println(list + ", size = " + list.size);
////        System.out.println(list.remove(4));
////        System.out.println(list + ", size = " + list.size);
//        System.out.println(list.removeLast());
//        System.out.println(list + ", size = " + list.size);
//        System.out.println(java.util.Arrays.toString(list.toArray(new Integer[0])));
//        System.out.println("list.get(0) = " + list.get(0));
//        System.out.println("list.get(1) = " + list.get(1));
//        System.out.println("list.get(2) = " + list.get(2));
//        System.out.println("list.get(3) = " + list.get(3));
//        System.out.println("list.get(N) = " + list.get(list.size - 1));
//        System.out.println(list + ", size = " + list.size);
//        list.insert(-1, 8); System.out.println(list + ", size = " + list.size);
//        list.insert(3, 8); System.out.println(list + ", size = " + list.size);
//        list.insert(list.size - 1, 8); System.out.println(list + ", size = " + list.size);
////        System.out.show(list.removeFirst()); System.out.println(list + ", size = " + list.size);
////        System.out.show(list.removeFirst()); System.out.println(list + ", size = " + list.size);
////        System.out.show(list.removeFirst()); System.out.println(list + ", size = " + list.size);
////        System.out.show(list.removeFirst()); System.out.println(list + ", size = " + list.size);
////        System.out.show(list.removeFirst()); System.out.println(list + ", size = " + list.size);
////        list.set(2, 123);
////        System.out.println(list);
////        System.out.println(java.util.Arrays.toString(list.toArray(new Integer[0], list.tail.next)));
//        System.out.println(list.search(list.tail, 2));
//        System.out.println(list.search(list.tail, 3));
//        System.out.println(list.search(list.tail, 8));
//    }



}
