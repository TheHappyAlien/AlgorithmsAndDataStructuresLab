package com.company;

import java.util.Comparator;

public abstract class HashTable<T> {

    protected final double maxLoadFactor;
    protected final Comparator<? super T> comparator;

    protected HashTable(double maxLoadFactor, Comparator<? super T> comparator) {
        this.maxLoadFactor = maxLoadFactor;
        this.comparator = comparator;
    }

    public final double loadFactor() {
        return size() / ((double) capacity());
    }


    public abstract int capacity();
    public abstract int size();

    public abstract void insert(T object);
    public abstract boolean lookUp(T object);

    public abstract int collisions();
    public abstract int insertComparisons();
    public abstract int lookUpComparisons();
    public abstract int hashFunctionEvaluations();
}
