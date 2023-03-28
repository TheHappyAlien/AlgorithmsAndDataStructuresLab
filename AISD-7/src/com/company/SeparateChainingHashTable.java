package com.company;

import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

public class SeparateChainingHashTable<T> extends HashTable<T> {

    private final HashFunction<T> hashF;
    private LinkedList<T>[] hashTable;
    private int capacity;
    private int size = 0;
    private int collisions = 0;
    private int insertComparisons = 0;
    private int lookUpComparisons = 0;
    private int hashFunctionEvaluations = 0;

    @SuppressWarnings("unchecked")
    protected SeparateChainingHashTable(double maxLoadFactor, Comparator<? super T> comparator, HashFunction<T> hashF, int initialCapacity) {
        super(maxLoadFactor, comparator);
        this.hashF = hashF;
        hashTable = (LinkedList<T>[]) new LinkedList[initialCapacity];
        capacity = initialCapacity;
    }

    private void insertionAlgorithm(LinkedList<T>[] tab, T object) {
        int index = getIndex(object);

        if (tab[index] != null) {
            ListIterator<T> iterator = tab[index].listIterator();

            while (iterator.hasNext()) {

                insertComparisons++;
                if (object.equals(iterator.next())) {
                    return;
                }

                collisions++;
            }

            iterator.add(object);

        } else {
            tab[index] = new LinkedList<T>();
            tab[index].add(object);
        }

        size++;


    }

    private int getIndex(T object) {
        hashFunctionEvaluations++;
        return hashF.hashCode(object) % capacity;
    }

    @Override
    public int capacity() {
        return capacity;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void insert(T object) {
        try {
            if (object != null) {
                if (loadFactor() < maxLoadFactor) {
                    insertionAlgorithm(hashTable, object);
                } else {
                    capacity = capacity * 2;
                    size = 0;

                    @SuppressWarnings("unchecked")
                    LinkedList<T>[] newHashTable = (LinkedList<T>[]) new LinkedList[capacity];

                    for (LinkedList<T> listToMove : hashTable) {
                        if (listToMove != null) {
                            for (T t : listToMove) insertionAlgorithm(newHashTable, t);
                        }
                    }

                    insertionAlgorithm(newHashTable, object);

                    hashTable = newHashTable;
                }
            } else {
                throw new IllegalArgumentException("Null objects not allowed");
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean lookUp(T object) {
        try {
            if (object != null) {
                int index = getIndex(object);

                if (hashTable[index] != null) {
                    for (T t : hashTable[index]) {
                        lookUpComparisons++;
                        if (object.equals(t)) return true;
                    }
                }
                return false;
            }
            throw new IllegalArgumentException("Can't look up a null object.");
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public int collisions() {
        return collisions;
    }

    @Override
    public int insertComparisons() {
        return insertComparisons;
    }

    @Override
    public int lookUpComparisons() {
        return lookUpComparisons;
    }

    @Override
    public int hashFunctionEvaluations() {
        return hashFunctionEvaluations;
    }

    @Override
    public String toString() {
        String representation = "[";

        for (int i = 0; i < capacity; i++) {
            if (hashTable[i] != null) {
                for (T object : hashTable[i]) {
                    representation = representation.concat(" " + object + ",");
                }
                representation = representation.substring(0, representation.length() - 1);
            }
            representation = representation.concat(" |");
        }

        representation = representation.substring(0, representation.length() - 2);
        representation = representation.concat(" ]");

        return representation;
    }
}
