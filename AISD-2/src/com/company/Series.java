package com.company;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public class Series<E> implements Iterable<E>{

    SeriesGenerator<E> seriesGenerator;

    public Series(SeriesGenerator<E> seriesGenerator){
        this.seriesGenerator = seriesGenerator;
    }

    @Override
    public Iterator<E> iterator() {
        return new SeriesIterator<E>(seriesGenerator);
    }
}
