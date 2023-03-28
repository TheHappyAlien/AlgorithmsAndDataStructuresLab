package com.company.BST;

public interface BinarySearchTree<T> {
    void clear();
    boolean add(T value);
    boolean remove(T value);
    boolean lookUp(T value);
}
