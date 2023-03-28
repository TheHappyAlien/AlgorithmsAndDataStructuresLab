package com.company;

import java.util.Comparator;

public class OpenAddressingHashTable<T> extends HashTable<T> {

    private final IncrementalFunction<T> incrementalF;
    private T[] hashTable;
    private int capacity;
    private int size = 0;
    private int trial = 0;
    private int collisions = 0;
    private int insertComparisons = 0;
    private int lookUpComparisons = 0;
    private int hashFunctionEvaluations = 0;

    @SuppressWarnings("unchecked")
    protected OpenAddressingHashTable(double maxLoadFactor, Comparator<? super T> comparator, IncrementalFunction<T> incrementalF, int initialCapacity) {
        super(maxLoadFactor, comparator);
        this.incrementalF = incrementalF;
        hashTable = (T[]) new Object[initialCapacity];
        capacity = initialCapacity;
    }

    private void insertionAlgorithm(T[] tab, T object) {
        int index = getIndex(object);

        while (tab[index] != null) {

            insertComparisons++;
            if (object.equals(tab[index])) {
                trial = 0;
                return;
            }

            index = getIndex(object);

            collisions++;
        }

        tab[index] = object;
        size++;

        trial = 0;
    }

    private int getIndex(T object) {
        hashFunctionEvaluations++;
        return incrementalF.shift(object, trial++) % capacity;
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
        try{
            if (object != null) {
                if (loadFactor() < maxLoadFactor) {
                    insertionAlgorithm(hashTable, object);
                } else {
                    capacity = capacity * 2;
                    size = 0;

                    @SuppressWarnings("unchecked")
                    T[] newHashTable = (T[]) new Object[capacity];

                    for (T objectToMove : hashTable) {
                        if (objectToMove != null) {
                            insertionAlgorithm(newHashTable, objectToMove);
                        }
                    }

                    insertionAlgorithm(newHashTable, object);

                    hashTable = newHashTable;
                }
            }else {
                throw new IllegalArgumentException("Null objects not allowed");
            }
        } catch (IllegalArgumentException e){
            e.printStackTrace();
        }
    }

    @Override
    public boolean lookUp(T object) {
        try {
            if (object != null) {
                int index = getIndex(object);

                while (hashTable[index] != null) {

                    lookUpComparisons++;
                    if (object.equals(hashTable[index])) {
                        trial = 0;
                        return true;
                    }

                    index = getIndex(object);

                }
                return false;
            }
            throw new IllegalArgumentException("Can't look up a null object.");
        }catch (IllegalArgumentException e){
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
        String representation = "[ ";

        for (int i = 0; i < capacity - 1; i++) {
            representation = representation.concat(hashTable[i] + ", ");
        }
        representation = representation.concat(hashTable[capacity - 1] + "");
        representation = representation.concat(" ]");

        return representation;
    }
}
