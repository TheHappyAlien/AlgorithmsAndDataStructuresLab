package com.company;

import java.util.List;
import java.util.ListIterator;

public class PriorityQueueSorter<T> {

    private final MinHeap<T> minHeap;

    public PriorityQueueSorter(MinHeap<T> minHeap){
        this.minHeap = minHeap;
    }

    public List<T> sort(List<T> list){
        minHeap.clear();

        for (T element : list){
            minHeap.add(element);
        }

        ListIterator<T> iterator = list.listIterator();

        while (iterator.hasNext()){
            iterator.next();
            iterator.set(minHeap.minimum());
        }

        return list;
    }
}
