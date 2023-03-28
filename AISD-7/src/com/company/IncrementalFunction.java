package com.company;

public interface IncrementalFunction<T> {
    int shift(T object, int trial);
}

