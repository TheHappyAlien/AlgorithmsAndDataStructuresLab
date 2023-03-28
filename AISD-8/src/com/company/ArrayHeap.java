package com.company;

import java.util.Comparator;
import java.util.NoSuchElementException;

public class ArrayHeap<T> implements MinHeap<T> {


    private T[] heapArray;
    private final Comparator<T> comparator;

    int size = 0;
    int capacity = 0;
    int initialCapacity;

    @SuppressWarnings("unchecked")
    public ArrayHeap(Comparator<T> comparator, int initialCapacity) {
        this.comparator = comparator;
        heapArray = (T[]) new Object[initialCapacity];
        capacity = initialCapacity;
        this.initialCapacity = initialCapacity;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void clear() {
        heapArray = (T[]) new Object[initialCapacity];
        capacity = initialCapacity;
        size = 0;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void add(T element) {
        if (size >= capacity) {
            capacity = capacity * 2;
            T[] temp = (T[]) new Object[capacity];
            if (size >= 0) System.arraycopy(heapArray, 0, temp, 0, size);
            heapArray = temp;
        }

        heapArray[size] = element;
        int idx = size++;

        // -Swim-
        while (idx > 0) {
            int idxOfSmaller = (idx - 1) / 2;

            if (comparator.compare(heapArray[idxOfSmaller], heapArray[idx]) < 0) {
                return;
            }

            swap(idxOfSmaller, idx);
            idx = idxOfSmaller;
        }
    }

    private void swap(int index1, int index2) {
        T temp = heapArray[index1];
        heapArray[index1] = heapArray[index2];
        heapArray[index2] = temp;
    }

    @Override
    public T minimum() {
        if (size > 0) {
            swap(0, --size);
            sink(0);
            // The object is no longer "inside" the heap, as such, the index = size is correct.
            T minElement = heapArray[size];
            heapArray[size] = null;
            return minElement;
        } else throw new NoSuchElementException();
    }

    private void sink(int index) {
        int idx = index;
        int idxOfChild;
        while ((idxOfChild = idx * 2 + 1) < size) {

            if (idxOfChild < size - 1 && comparator.compare(heapArray[idxOfChild + 1], heapArray[idxOfChild]) < 0) {
                idxOfChild++;
            }

            if (comparator.compare(heapArray[idxOfChild], heapArray[idx]) < 0) {
                swap(idxOfChild, idx);
                idx = idxOfChild;
            } else break;
        }
    }

    @Override
    public String toString() {
        String representation = "";
        int idx = 0;
        int pass = 0;
        while (idx < size) {
            for (int i = 0; i < Math.pow(2, pass); i++) {
                if (idx < size) {
                    representation = representation.concat(heapArray[idx++].toString() + " , ");
                } else break;
            }
            representation = representation.substring(0, representation.length() - 3);
            representation = representation.concat("\n");
            pass++;
        }
        return representation;
    }
}
