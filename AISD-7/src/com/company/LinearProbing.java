package com.company;

public class LinearProbing<T> implements IncrementalFunction<T>{

    HashFunction<T> hashFunction;

    public LinearProbing(HashFunction<T> hashFunction){
        this.hashFunction = hashFunction;
    }

    @Override
    public int shift(T object, int trial) {
        return hashFunction.hashCode(object) + trial;
    }

}
