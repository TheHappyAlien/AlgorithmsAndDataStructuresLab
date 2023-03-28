package com.company;

public class SquareGenerator implements SeriesGenerator<Integer>{
    @Override
    public Integer generate(int n) {
        return n*n;
    }
}
