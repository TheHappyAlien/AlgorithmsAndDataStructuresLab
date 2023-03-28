package com.company;

import java.util.*;

public class TreeHeap<T> implements MinHeap<T> {

    private class node {

        private T value;

        private node childL;
        private node childR;

        private node(T value) {
            this.value = value;
        }


        public T getValue() {
            return value;
        }

        public node getChildL() {
            return childL;
        }

        public node getChildR() {
            return childR;
        }


        public void setValue(T value) {
            this.value = value;
        }

        public void setChildL(node childL) {
            this.childL = childL;
        }

        public void setChildR(node childR) {
            this.childR = childR;
        }

        @Override
        public String toString() {
            return value.toString();
        }
    }

    private node root;
    private final Comparator<T> comparator;
    int size = 0;

    public TreeHeap(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    private void swap(node node1, node node2) {
        T temp = node1.getValue();
        node1.setValue(node2.getValue());
        node2.setValue(temp);
    }

    private void sink(node node) {

        node currentNode = node;
        while (currentNode.childL != null) {
            node childL = currentNode.childL;
            node childR = currentNode.childR;
            node childToCompare = childL;
            if (childR != null && comparator.compare(childR.getValue(), childL.getValue()) < 0) {
                childToCompare = childR;
            }
            if (comparator.compare(childToCompare.getValue(), currentNode.getValue()) < 0) {
                swap(childToCompare, currentNode);
                currentNode = childToCompare;
            } else break;
        }
    }

    private node getNode(int index) {

        char[] binaryRep = intToByteStr(index + 1).substring(1).toCharArray();
        node node = root;
        for (char bit : binaryRep) {
            if (bit == '1') node = node.childR;
            else node = node.childL;
        }

        return node;
    }

    private node getParentNode(int index) {
        node parentNode = root;

        String binaryString = intToByteStr(index + 1);
        char[] binaryRep = binaryString.substring(1, binaryString.length() - 1).toCharArray();
        for (char bit : binaryRep) {
            if (bit == '1') parentNode = parentNode.childR;
            else parentNode = parentNode.childL;

        }

        return parentNode;
    }

    private String intToByteStr(int number) {
        return Integer.toBinaryString(number);
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public void add(T element) {
        node newNode = new node(element);
        int idx = size++;
        if (root == null) root = newNode;
         else {
            node parentNode = getParentNode(idx);
            if (parentNode.getChildL() == null) parentNode.setChildL(newNode);
            else parentNode.setChildR(newNode);

            // -Swim-
            while (idx > 0) {

                parentNode = getParentNode(idx);
                String binaryRep = intToByteStr(idx + 1);

                if (binaryRep.endsWith("0")) {
                    if (comparator.compare(parentNode.getValue(), parentNode.getChildL().getValue()) < 0) return;
                    swap(parentNode, parentNode.childL);
                } else {
                    if (comparator.compare(parentNode.getValue(), parentNode.getChildR().getValue()) < 0) return;
                    swap(parentNode, parentNode.childR);
                }

                idx = (idx - 1) / 2;
            }
        }


    }

    @Override
    public T minimum() {
        if (size > 0) {
            T minValue = root.getValue();

            if (size == 1) {
                root = null;
                return minValue;
            }

            String byteRep = intToByteStr(size);

            node lastNode = getNode(--size);
            node parentToLast = getParentNode(size);

            swap(root, lastNode);

            if (byteRep.endsWith("0")) parentToLast.setChildL(null);
            else parentToLast.setChildR(null);

            sink(root);

            return minValue;
        } else throw new NoSuchElementException();
    }

    @Override
    public String toString() {

        LinkedList<node> nodeQueue1 = new LinkedList<>();
        LinkedList<node> nodeQueue2 = new LinkedList<>();
        String representation = "[";

        if (root == null) {
            return representation;
        }

        nodeQueue1.addLast(root);
        boolean queueSwap = false;

        while (nodeQueue1.size() + nodeQueue2.size() > 0) {
            if (!queueSwap) {
                for (node node : nodeQueue1) {
                    representation = representation.concat(node.getValue().toString());
                    if (node.getChildL() != null) {
                        nodeQueue2.add(node.getChildL());
                    }
                    if (node.getChildR() != null) {
                        nodeQueue2.add(node.getChildR());
                    }
                    representation = representation.concat(", ");
                }
                nodeQueue1.clear();
//                representation = representation.concat("\n");
                queueSwap = true;
            } else {
                for (node node : nodeQueue2) {
                    representation = representation.concat(node.getValue().toString());
                    if (node.getChildL() != null) {
                        nodeQueue1.add(node.getChildL());
                    }
                    if (node.getChildR() != null) {
                        nodeQueue1.add(node.getChildR());
                    }
                    representation = representation.concat(", ");
                }
                nodeQueue2.clear();
//                representation = representation.concat("\n");
                queueSwap = false;
            }
        }

        return representation.substring(0, representation.length() - 2).concat("]");
    }

}
