package com.company;

import java.util.LinkedList;
import java.util.List;

public class OrderedIntGenerator implements Generator<Integer>{

    private final int size;

    public OrderedIntGenerator(int size){
        this.size = size;
    }

    @Override
    public List<Integer> generate() {
        List<Integer> list = new LinkedList<>();

        for (int i = 0; i < size; i++){
            list.add(i);
        }

        return list;
    }
}
