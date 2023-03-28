package com.company;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class OneWayLinkedSentinelList<E> implements IList<E>{

    private class Element{
        private E value;
        private Element nextElement;

        public E getValue() {
            return value;
        }

        public void SetValue(E value) {
            this.value = value;
        }

        public Element getNext() {
            return nextElement;
        }

        public void setNext(Element e) {
            nextElement = e;
        }

        Element(E value) {
            this.value = value;
        }

        Element(){};
    }

    private final Element head;

    public OneWayLinkedSentinelList(){
        head = new Element();
    }

    @Override
    public boolean add(E e) {
        Element tail = head;
        while (tail.getNext() != null){
            tail = tail.getNext();
        }
        tail.setNext(new Element(e));
        return true;
    }

    @Override
    public void add(int index, E element) {

        Element currentElement = head;

        Element newElement = new Element(element);

        if (index < 0 || index > this.size()){
            throw new IndexOutOfBoundsException();
        }

        int pos = -1;

        while (pos != index - 1){
            pos++;
            currentElement = currentElement.getNext();
        }

        Element prevElement = currentElement;

        currentElement = currentElement.getNext();

        prevElement.setNext(newElement);

        newElement.setNext(currentElement);
    }

    @Override
    public void clear() {
        head.setNext(null);
    }

    @Override
    public boolean contains(E element) {

        Element currentElement = head;

        while (currentElement.getNext() != null){

            currentElement = currentElement.getNext();

            if (currentElement.getValue().equals(element)){
                return true;
            }
        }

        return false;
    }

    @Override
    public E get(int index) {

        Element currentElement = head;

        if (index < 0 || index >= this.size()){
            throw new IndexOutOfBoundsException();
        }

        int pos = -1;

        while (pos != index){
            pos++;
            currentElement = currentElement.getNext();
        }

        return currentElement.getValue();
    }

    @Override
    public E set(int index, E element) {

        Element currentElement = head;

        if (index < 0 || index >= this.size()){
            throw new IndexOutOfBoundsException();
        }

        int pos = -1;

        while (pos != index){
            pos++;
            currentElement = currentElement.getNext();
        }

        currentElement.SetValue(element);

        return element;
    }

    @Override
    public int indexOf(E element) {

        Element currentElement = head;

        int pos = -1;

        while (currentElement.getNext() != null){
            currentElement = currentElement.getNext();
            pos++;
            if (currentElement.getValue().equals(element)){
                return pos;
            }
        }

        throw new NoSuchElementException();
    }

    @Override
    public boolean isEmpty() {
        return head.getNext() == null;
    }

    @Override
    public ListIterator<E> listIterator() {
        return null;
    }

    @Override
    public E remove(int index) {

        Element currentElement = head;

        if (index < 0 || index >= this.size()){
            throw new IndexOutOfBoundsException();
        }

        int pos = -1;

        while (pos != index - 1){
            pos++;
            currentElement = currentElement.getNext();
        }

        Element prevElement = currentElement;

        currentElement = currentElement.getNext();

        Element nextElement = currentElement.getNext();

        prevElement.setNext(nextElement);

        return currentElement.getValue();

    }

    @Override
    public boolean remove(E element) {

        Element currentElement = head;

        while (currentElement.getNext() != null){

            if (currentElement.getNext().getValue().equals(element)){

                Element prevElement = currentElement;

                currentElement = currentElement.getNext();

                Element nextElement = currentElement.getNext();

                prevElement.setNext(nextElement);

                return true;
            }

            currentElement = currentElement.getNext();

        }

        throw new NoSuchElementException();

    }

    @Override
    public int size() {

        Element currentElement = head;

        int size = 0;
        while (currentElement.getNext() != null){
            currentElement = currentElement.getNext();
            size++;
        }

        return size;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    public void reverse(){

        Element prevElement;
        Element currentElement;
        Element nextElement;

        boolean first = true;

        prevElement = head;

        if (prevElement.getNext() != null) {

            currentElement = prevElement.getNext();

            if (currentElement.getNext() != null) {

                nextElement = currentElement.getNext();

                while (nextElement.getNext() != null) {

                    prevElement = currentElement;
                    currentElement = nextElement;
                    nextElement = nextElement.getNext();

                    if (first){
                        prevElement.setNext(null);
                        first = false;
                    }else {
                        prevElement.setNext(prevElement);
                    }


                }

                if (first){
                    currentElement.setNext(null);
                }else {
                    currentElement.setNext(prevElement);
                }

                nextElement.setNext(currentElement);
                head.setNext(nextElement);
            }

        }

    }
}
