package com.company;

import java.util.Iterator;
import java.util.NoSuchElementException;


public class SeriesIterator<E> implements Iterator<E>{

    protected int pos;
    protected SeriesGenerator<E> seriesGenerator;

    public SeriesIterator(SeriesGenerator<E> seriesGenerator) {
        this.seriesGenerator = seriesGenerator;
        pos = 1;
    }

    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public E next() {
        if (hasNext()) {
            return seriesGenerator.generate(pos++);
        }else throw new NoSuchElementException();
    }

    public void returnToFirst(){
        pos = 0;
    }

//    public boolean hasPrevious(){
//        return pos > 0;
//    }
//
//    public E previous() throws NoSuchElementException{
//        if (hasPrevious()){
//            return seriesGenerator.generate(--pos);
//        }
//        throw new NoSuchElementException();
//    }


}

