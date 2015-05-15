package com.github.immrgabriel.prometheus.algorithms.StructureData.Collections;

import java.util.*;

public class HashMap<K, V> /*extends AbstractMap<K,V> implements Map<K,V>*/ {

    static final int DEFAULT_INITIAL_CAPACITY = 16;
    static final float DEFAULT_LOAD_FACTOR = 0.75f;

    private int size = 0;
    private final float loadFactor;
    private Node<K,V>[] table;

    public HashMap() {
        loadFactor = DEFAULT_LOAD_FACTOR;
        @SuppressWarnings({"rawtypes","unchecked"})
        Node<K,V>[] newTab = (Node<K,V>[])new Node[DEFAULT_INITIAL_CAPACITY];
        table = newTab;
    }

    public HashMap(int initialCapacity) {
        this(initialCapacity, DEFAULT_LOAD_FACTOR);
    }

    public HashMap(int initialCapacity, float loadFactor) {
        if (initialCapacity < 0)
            throw new IllegalArgumentException("Illegal initial capacity: " + initialCapacity);
        if (loadFactor <= 0 || Float.isNaN(loadFactor))
            throw new IllegalArgumentException("Illegal load factor: " + loadFactor);
        this.loadFactor = loadFactor;
        @SuppressWarnings({"rawtypes","unchecked"})
        Node<K,V>[] newTab = (Node<K,V>[])new Node[initialCapacity];
        table = newTab;
    }

    private static int index(int h, int length) {
        return h & (length - 1);
    }

    private static int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    private static class Node<K,V> implements Map.Entry<K,V> {
        final int hash;
        final K key;
        V value;
        Node<K,V> next;

        Node(int hash, K key, V value, Node<K,V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public final K getKey()        { return key; }
        public final V getValue()      { return value; }
        public final String toString() { return key + "=" + value; }

        public final int hashCode() {
            return java.util.Objects.hashCode(key) ^ java.util.Objects.hashCode(value);
        }

        public final V setValue(V newValue) {
            V oldValue = value;
            value = newValue;
            return oldValue;
        }

        public final boolean equals(Object o) {
            if (o == this)
                return true;
            if (o instanceof Map.Entry) {
                Map.Entry<?,?> e = (Map.Entry<?,?>)o;
                if (java.util.Objects.equals(key, e.getKey()) &&
                        java.util.Objects.equals(value, e.getValue()))
                    return true;
            }
            return false;
        }
    }

    private void resize(int newCapacity) {
        @SuppressWarnings({"rawtypes","unchecked"})
        Node<K,V>[] newTab = (Node<K,V>[])new Node[newCapacity];
        Node<K,V>[] oldTab = table;
        size = 0;
        table = newTab;
        for(Node<K,V> node : oldTab) {
            while(node != null) {
                put(node.getKey(), node.getValue());
                node = node.next;
            }
        }
//        System.out.println("newCapacity = " + newCapacity);
    }

    private class NodeIterator implements Iterator<Node<K,V>> {
        private Node<K,V> tail;
        private Node<K,V> next;
        private Node<K,V> current;

        private NodeIterator(Node<K, V> next) {
            this.next = tail = next;
        }

        @Override
        public boolean hasNext() {
            return next != null;
        }

        @Override
        public Node<K,V> next() {
            if(next == null)
                throw new NoSuchElementException();
            current = next;
            next = next.next;
            return current;
        }

        @Override
        public void remove() {
            if(current == null)
                throw new IllegalStateException();
            if(current == tail) {
                table[index(tail.hash, table.length)] = tail = next;
                size--;
                return;
            }
            Node<K,V> prev = tail;
            while(prev != null && prev.next != current)
                prev = prev.next;
            if(prev == null)
                throw new NoSuchElementException();
            prev.next = current.next;
            current = null;
            size--;
        }
    }

    private class MapIterator implements Iterator<Map.Entry<K,V>> {
        private int index = -1;
        private Iterator<Node<K,V>> it;

        @Override
        public boolean hasNext() {
            if(it == null || !it.hasNext()) {
                int length = table.length;
                while (++index < length && table[index] == null);
                if(index >= length)
                    return false;
                it = iterator(table[index]);
            }
            return it.hasNext();
        }

        @Override
        public Map.Entry<K, V> next() {
            return it.next();
        }

        @Override
        public void remove() {
            it.remove();
        }
    }

    private Node<K,V> getNode(Object key) {
        int h = hash(key);
        Iterator<Node<K,V>> it = new NodeIterator(table[index(h, table.length)]);
        if(key == null) {
            while (it.hasNext()) {
                Node<K, V> current = it.next();
                if (current.hash == h && current.key == null)
                    return current;
            }
        } else {
            while (it.hasNext()) {
                Node<K, V> current = it.next();
                if (current.hash == h && key.equals(current.key))
                    return current;
            }
        }
        return null;
    }

    private Iterator<Node<K,V>> iterator(Node<K,V> node) {
        return new NodeIterator(node);
    }

    public Iterator<Map.Entry<K,V>> iterator() {
        return new MapIterator();
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean containsKey(Object key) {
        int h = hash(key);
        Iterator<Node<K,V>> it = new NodeIterator(table[index(h, table.length)]);
        if(key == null) {
            while (it.hasNext()) {
                Node<K, V> current = it.next();
                if (current.hash == h && current.key == null)
                    return true;
            }
        } else {
            while (it.hasNext()) {
                Node<K, V> current = it.next();
                if (current.hash == h && key.equals(current.key))
                    return true;
            }
        }
        return false;
    }

    public V get(Object key) {
        Node<K,V> node = getNode(key);
        if(node == null)
            return null;
        return node.getValue();
    }

    public V put(K key, V value) {
        int index = index(hash(key), table.length);
        if(table[index] == null) {
            table[index] = new Node<>(hash(key), key, value, null);
        } else {
            Node<K, V> current = getNode(key);
            if(current == null) {
                Node<K, V> tail = table[index];
                table[index] = new Node<>(hash(key), key, value, tail);
            } else {
                V result = current.value;
                current.value = value;
                return result;
            }
        }
        size++;
        if(size > (int)(table.length * loadFactor))
            resize(table.length << 1);
        return null;
    }

    public V remove(Object key) {
        Node<K,V> target = getNode(key);
        if(target == null)
            return null;
        int index = index(hash(key), table.length);
        Node<K,V> tail = table[index];
        if(tail == target) {
            table[index] = target.next;
            size--;
            return target.value;
        }
        if(tail == null)
            return null;
        while(tail.next != null && tail.next != target)
            tail = tail.next;
        if(tail.next == null)
            return null;
        tail.next = target.next;
        size--;
        return target.value;
    }

    public Map.Entry<K,V>[] toArray() {
        if(isEmpty())
            throw new NoSuchElementException();
        @SuppressWarnings({"rawtypes","unchecked"})
        Map.Entry<K,V>[] result = (Map.Entry<K,V>[])new Map.Entry[size];
        int count = 0;
        for(Node<K,V> node : table) {
            while(node != null) {
                result[count++] = node;
                node = node.next;
            }
        }
        return result;
    }

    public void printMap() {
        System.out.println("****************************************************************************************************");
        for(Node<K,V> node : table) {
            if(node == null)
                System.out.print("null");
            while(node != null) {
                System.out.print(node + " -> ");
                node = node.next;
            }
            System.out.println();
        }
        System.out.println("****************************************************************************************************");
    }

    public String toString() {
        return java.util.Arrays.toString(toArray());
    }

}
