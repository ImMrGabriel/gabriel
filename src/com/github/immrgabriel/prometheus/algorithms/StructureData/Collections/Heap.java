package com.github.immrgabriel.prometheus.algorithms.StructureData.Collections;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Heap<T extends Comparable<T>> {
    private T[] array;
    private int size;
    private Boolean isTopMin;

    public Heap(T[] array) {
        if(array == null)
            throw new IllegalArgumentException("bad argument");
        this.array = array;
        size = array.length;
    }

    public Heap(Class c, int capacity) {
        if(capacity < 2 || c == null)
            throw new IllegalArgumentException("bad argument");
        array = (T[]) java.lang.reflect.Array.newInstance(c, capacity);
    }

    private class HeapIterator implements Iterator<T> {
        private int current = -1;
        private boolean isRemovable = false;

        @Override
        public boolean hasNext() {
            return current + 1 < size;
        }

        @Override
        public T next() {
            if(!hasNext())
                throw new NoSuchElementException();
            isRemovable = true;
            return array[++current];
        }

        @Override
        public void remove() {
            if (isRemovable) {
                swap(current, --size);
                down(current);
                --current;
                isRemovable = false;
            } else {
                throw new IllegalStateException();
            }
        }
    }

    public Iterator<T> iterator() {
        return new HeapIterator();
    }

    @SuppressWarnings("unchecked")
    public void setCapacity(int capacity) {
        if(capacity < 1)
            throw new IllegalArgumentException("capacity <= 0!");
        if(array.length == capacity)
            return;
        if(isEmpty()) {
            array = (T[]) java.lang.reflect.Array.newInstance(array.getClass().getComponentType(), capacity);
        } else if(array.length < capacity){
            array = java.util.Arrays.copyOf(array, capacity);
        } else {
            T[] tmp = (T[]) java.lang.reflect.Array.newInstance(array.getClass().getComponentType(), capacity);
            System.arraycopy(array, 0, tmp, 0, size);
            array = tmp;
        }
    }

    public void trimToSize() {
        if(isEmpty()) {
            throw new IllegalArgumentException("it is Empty");
        }
        setCapacity(size);
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == array.length;
    }

    public boolean add(T item) {
        if(isFull())
            setCapacity(size + 10);
        array[size++] = item;
        rise(size - 1);
        return true;
    }

    /**
     * Поддержка свойства при добавлении в пирамиду
     */
    private void rise(int index) {
        if(isTopMin != null) {
            if(isTopMin) {
                minGrow(index);
            } else {
                maxGrow(index);
            }
        }
    }

    private void down(int index) {
        if(isTopMin != null) {
            if(isTopMin) {
                minHeapify(index);
            } else {
                maxHeapify(index);
            }
        }
    }

    /**
     * @param indexChild the index of the child
     * @return the index of the parent
     */
    private int parent(int indexChild) {
        return --indexChild >> 1;                // (index - 1)/2
    }

    /**
     * @param indexParent the index of the parent
     * @return the index of the left child
     */
    private int left(int indexParent) {
        indexParent = (indexParent << 1) + 1;           // index*2 + 1
        return indexParent < size ? indexParent : -1;
    }

    /**
     * @param indexParent the index of the parent
     * @return the index of the right child
     */
    private int right(int indexParent) {
        indexParent = (indexParent << 1) + 2;           // index*2 + 2
        return indexParent < size ? indexParent : -1;
    }

    public T getParent(int index) {
        index = parent(index);
        return (index < 0 || index >= size) ? null : array[index];
    }

    public T getLeft(int index) {
        index = left(index);
        return (index < 0) ? null : array[index];
    }

    public T getRight(int index) {
        index = right(index);
        return (index < 0) ? null : array[index];
    }

    public int size() {
        return size;
    }

    /**
     * Поддержка свойства пирамиды для невозрастающей пирамиды (предки больше детей) начиная с
     * @param indexParent the index of the parent
     */
    private void maxHeapify(int indexParent) {
        int left = left(indexParent);
        int right = right(indexParent);
        int largest = indexParent;
        if(left >= 0 && array[left].compareTo(array[largest]) > 0)
            largest = left;
        if(right >= 0 && array[right].compareTo(array[largest]) > 0)
            largest = right;
        if(largest != indexParent) {
            swap(largest, indexParent);
            maxHeapify(largest);
        }
    }

    /**
     * Поддержка свойства пирамиды для неубывающей пирамиды (предки меньше детей) начиная с
     * @param indexParent the index of the parent
     */
    private void minHeapify(int indexParent) {
        int left = left(indexParent);
        int right = right(indexParent);
        int least = indexParent;
        if(left >= 0 && array[left].compareTo(array[least]) < 0)
            least = left;
        if(right >= 0 && array[right].compareTo(array[least]) < 0)
            least = right;
        if(least != indexParent) {
            swap(least, indexParent);
            minHeapify(least);
        }
    }

    private void maxGrow(int index) {
        int parent = parent(index);
        while(!(parent < 0) && array[index].compareTo(array[parent]) > 0) {
            swap(index, parent);
            parent = parent(index = parent);
        }
    }

    private void minGrow(int index) {
        int parent = parent(index);
        while(!(parent < 0) && array[index].compareTo(array[parent]) < 0) {
            swap(index, parent);
            parent = parent(index = parent);
        }
    }

    /**
     * Поддержка свойства для всей пирамиды, невозрастающая пирамида (предки больше детей)
     */
    public void buildMaxHeap(){
        for (int i = size / 2; i >= 0; i--)
            maxHeapify(i);
        isTopMin = false;
    }

    /**
     * Поддержка свойства для всей пирамиды, неубывающая пирамида (предки меньше детей)
     */
    public void buildMinHeap(){
        for (int i = size / 2; i >= 0; i--)
            minHeapify(i);
        isTopMin = true;
    }

    private void swap(int i0, int i1) {
        T tmp = array[i0];
        array[i0] = array[i1];
        array[i1] = tmp;
    }

    public void sort() {
        int quantity = size;
        if(isTopMin) {
            buildMaxHeap();
            while (size > 0) {
                swap(0, --size);
                maxHeapify(0);
            }
        } else {
            buildMinHeap();
            while (size > 0) {
                swap(0, --size);
                minHeapify(0);
            }
        }
        size = quantity;
    }

    public T remove(int index) {
        if(index < 0 || index >= size)
            throw new IllegalArgumentException("bad index");
        T result = array[index];
        swap(index, --size);
        down(index);
        return result;
    }

    public T[] toArray() {
        if(isFull())
            return array;
        return java.util.Arrays.copyOf(array, size);
    }

    public T getRoot() {
        if(size > 0)
            return array[0];
        return null;
    }

    public T getMax() {
        int index = max();
        return index < 0 ? null : array[index];
    }

    public T getMin() {
        int index = min();
        return index < 0 ? null : array[index];
    }

    public T extractRoot() {
        if(size > 0)
            return remove(0);
        return null;
    }

    public T extractMax() {
        int index = max();
        if(index < 0)
            return null;
        return remove(index);
    }

    public T extractMin() {
        int index = min();
        if(index < 0)
            return null;
        return remove(index);
    }

    private int max() {
        if(size == 0)
            return -1;
        if(isTopMin != null) {
            if(isTopMin) {
                int par = parent(size - 1);
                int max = par < 0 ? 0 : par + 1;
                for (int i = max + 1; i < size; i++) {
                    if(array[i].compareTo(array[max]) > 0)
                        max = i;
                }
                return max;
            } else {
                return 0;
            }
        } else {
            int max = 0;
            for (int i = 1; i < size; i++) {
                if(array[i].compareTo(array[max]) > 0)
                    max = i;
            }
            return max;
        }
    }

    private int min() {
        if(size == 0)
            return -1;
        if(isTopMin != null) {
            if(isTopMin) {
                return 0;
            } else {
                int par = parent(size - 1);
                int min = par < 0 ? 0 : par + 1;
                for (int i = min + 1; i < size; i++) {
                    if(array[i].compareTo(array[min]) < 0)
                        min = i;
                }
                return min;
            }
        } else {
            int min = 0;
            for (int i = 1; i < size; i++) {
                if(array[i].compareTo(array[min]) < 0)
                    min = i;
            }
            return min;
        }
    }

    public boolean set(int index, T item) {
        if(index < 0 || index >= size)
            return false;
        T old = array[index];
        array[index] = item;
        if(isTopMin != null && item.compareTo(old) != 0) {
            if(isTopMin) {
                if(item.compareTo(old) > 0)
                    minHeapify(index);
                else
                    minGrow(index);
            } else {
                if(item.compareTo(old) < 0)
                    maxHeapify(index);
                else
                    maxGrow(index);
            }
        }
        return true;
    }

    @Override
    public String toString(){
        if(isEmpty())
            return "[]";
        StringBuilder sb = new StringBuilder("[");
        sb.append(array[0]);
        for (int i = 1; i < size; i++) {
            sb.append(',').append(' ').append(array[i]);
        }
        sb.append(']');
        return sb.toString();
    }

    public void show() {
//        for (int i = 0; i < size; i = i*2 + 1) {
//            for (int j = i; (j < (i*2 + 1) && j < size); j++) {
//                System.out.print(array[j] + "  ");
//            }
//            System.out.println();
//        }
        int height = height();
        Iterator<T> it = iterator();
        int length = 1;
        for (int i = (int) Math.pow(2, height - 1); i > 0; i >>= 1) {
            String sep = new String(new char[i]).replaceAll(".","\t");
            if(sep.length() > 1) {
                System.out.print(sep.substring(sep.length() >> 1));
            }

            int count = 0;
            while (it.hasNext() && count < length) {
                count++;
                System.out.print(it.next() + sep);
            }
            System.out.println();
            length <<= 1;
        }
    }

    public int height() {
        int result = 0;
        int count = 1;
        while (size >= count) {
            count <<= 1;            // count = count << 1;
            result++;
        }
        return result;
    }

}
