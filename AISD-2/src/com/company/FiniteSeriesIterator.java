package com.company;

public class FiniteSeriesIterator<E> extends SeriesIterator<E>{

    private final int endPos;

    public FiniteSeriesIterator(SeriesGenerator<E> seriesGenerator, int endIndex) {
        super(seriesGenerator);
        if (endIndex < 0){
            throw new IllegalArgumentException();
        }
        endPos = endIndex;
    }

    @Override
    public boolean hasNext() {
        return pos < endPos;
    }

}
