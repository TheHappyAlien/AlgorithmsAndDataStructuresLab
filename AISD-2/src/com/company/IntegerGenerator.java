package com.company;

public class IntegerGenerator implements  SeriesGenerator<Integer>{

    private int multiplier;

    public IntegerGenerator(int multiplier){
        this.multiplier = multiplier;
    }

    @Override
    public Integer generate(int n) {
        return n*multiplier;

    }
}
