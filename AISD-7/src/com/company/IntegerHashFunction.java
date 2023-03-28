package com.company;

public class IntegerHashFunction implements HashFunction<Integer>{

    @Override
    public int hashCode(Integer number) {
        return Math.abs(number);
    }
}
