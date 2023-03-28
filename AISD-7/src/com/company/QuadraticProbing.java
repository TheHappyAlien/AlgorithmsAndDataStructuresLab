package com.company;

public class QuadraticProbing<T> implements IncrementalFunction<T>{

    HashFunction<T> hashFunction;

    public QuadraticProbing(HashFunction<T> hashFunction){
        this.hashFunction = hashFunction;
    }

    @Override
    public int shift(T object, int trial) {
        return Math.abs(hashFunction.hashCode(object) + trial * trial);
    }
}
