package com.company;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class IntegerGenerator implements Generator<Integer>{

    private final int bound;
    private final int size;
    Random random = new Random();

    public IntegerGenerator(int size, int bound){
        this.bound = bound;
        this.size = size;
    }

    @Override
    public List<Integer> generate() {

        List<Integer> list = new LinkedList<>();

        for (int i = 0; i < size; i++){
            list.add(random.nextInt(bound));
        }

        return list;
    }
}
