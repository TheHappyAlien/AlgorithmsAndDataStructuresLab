package com.company.BST;

import java.util.Comparator;
import java.util.LinkedList;

public class BST<T> implements BinarySearchTree<T>{

    private final Comparator<T> comparator;
    private node root = null;

    private class node{
        private T value;
        private node lChild;
        private node rChild;

        node(T value){
            this.value = value;
        }
    }

    public BST(Comparator<T> comparator){
        this.comparator = comparator;
    }

    @Override
    public void clear() {
        root = null;
    }

    private node moveOneStep(node node, T value){
        int comparisonValue;
        if ((comparisonValue = comparator.compare(value, node.value)) < 0) return node.lChild;
        else if (comparisonValue == 0) return node;
        return node.rChild;
    }

    @Override
    public boolean add(T value) {
        if (root == null){
            root = new node(value);
            return true;
        }

        if (lookUp(value)) return false;

        node parentNode = root;
        node potentialPlace;

        while((potentialPlace = moveOneStep(parentNode, value)) != null){
            parentNode = potentialPlace;
        }

        if (comparator.compare(value, parentNode.value) < 0) parentNode.lChild = new node(value);
        else parentNode.rChild = new node(value);

        return true;
    }

    @Override
    public boolean remove(T value) {
        if (!lookUp(value)) return false;

        node parentNode = root;
        node nodeToRemove;

        while((nodeToRemove = moveOneStep(parentNode, value)) != null){
            if (comparator.compare(value, nodeToRemove.value) == 0) break;
            parentNode = nodeToRemove;
        }

        removeNode(nodeToRemove, parentNode);
        return true;
    }

    private void removeNodeWithUpToOneChild(node nodeToRemove, node parentNode){
            if (nodeToRemove.lChild != null) {
                if (comparator.compare(nodeToRemove.value, parentNode.value) < 0) parentNode.lChild = nodeToRemove.lChild;
                else parentNode.rChild = nodeToRemove.lChild;
            }else{
                if (comparator.compare(nodeToRemove.value, parentNode.value) < 0) parentNode.lChild = nodeToRemove.rChild;
                else parentNode.rChild = nodeToRemove.rChild;
            }
    }

    private void removeNode(node nodeToRemove, node parentNode){
        T valueToSwap = null;
        boolean hasToBeFixed = false;
        while (nodeToRemove.lChild != null && nodeToRemove.rChild != null){
            hasToBeFixed = true;
            parentNode.value = nodeToRemove.value;

            node forSwapping = nodeToRemove;
            nodeToRemove = nodeToRemove.lChild;
            parentNode = forSwapping;

            valueToSwap = nodeToRemove.value;
        }
        removeNodeWithUpToOneChild(nodeToRemove, parentNode);
        if (hasToBeFixed) parentNode.value = valueToSwap;
    }

    @Override
    public boolean lookUp(T value) {
        node currentNode = root;
        node nextNode;

        while((nextNode = moveOneStep(currentNode, value)) != null){
            if (comparator.compare(value, nextNode.value) == 0) return true;
            currentNode = nextNode;
        }

        return  false;
    }

    @Override
    public String toString() {

        LinkedList<node> nodeQueue1 = new LinkedList<>();
        LinkedList<node> nodeQueue2 = new LinkedList<>();
        String representation = "";

        if (root == null) {
            return representation;
        }

        nodeQueue1.addLast(root);
        boolean queueSwap = false;

        while (nodeQueue1.size() + nodeQueue2.size() > 0) {
            if (!queueSwap) {
                for (node node : nodeQueue1) {
                    representation = representation.concat(node.value.toString());
                    if (node.lChild != null) {
                        nodeQueue2.add(node.lChild);
                    }
                    if (node.rChild != null) {
                        nodeQueue2.add(node.rChild);
                    }
                    representation = representation.concat(", ");
                }
                nodeQueue1.clear();
                representation = representation.substring(0, representation.length() - 2);
                representation = representation.concat("\n");
                queueSwap = true;
            } else {
                for (node node : nodeQueue2) {
                    representation = representation.concat(node.value.toString());
                    if (node.lChild != null) {
                        nodeQueue1.add(node.lChild);
                    }
                    if (node.rChild != null) {
                        nodeQueue1.add(node.rChild);
                    }
                    representation = representation.concat(", ");
                }
                nodeQueue2.clear();
                representation = representation.substring(0, representation.length() - 2);
                representation = representation.concat("\n");
                queueSwap = false;
            }
        }

        return representation;
    }
}
