package com.company;

import java.util.Iterator;

public class FiniteSeries<E> extends Series<E>{

    private final int endIndex;

    public FiniteSeries(SeriesGenerator<E> seriesGenerator, int endIndex){
        super(seriesGenerator);
        if (endIndex < 0) {
            throw new IllegalArgumentException();
        }
        this.endIndex = endIndex;
    }

    @Override
    public Iterator<E> iterator() {
        return new FiniteSeriesIterator<>(seriesGenerator, endIndex);
    }

}
