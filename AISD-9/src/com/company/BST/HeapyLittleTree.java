package com.company.BST;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Random;

public class HeapyLittleTree<T> implements BinarySearchTree<T>{

    private final Comparator<T> comparator;
    private node root = null;
    private final Random random = new Random();
    private final int priorityBound;

    private class node{
        private T value;
        private int priority;
        private node lChild;
        private node rChild;

        node(T value){
            this.value = value;
            priority = random.nextInt(priorityBound);
        }

        @Override
        public String toString() {
            return "[" + value + "," + priority + "]";
        }
    }

    public HeapyLittleTree(Comparator<T> comparator, int priorityBound){
        if (priorityBound > 0) {
            this.comparator = comparator;
            this.priorityBound = priorityBound;
        }else throw new IllegalArgumentException("priorityBound can't be less than 1");
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
        node currentNode;

        while((currentNode = moveOneStep(parentNode, value)) != null){
            parentNode = currentNode;
        }

        currentNode = new node(value);

        if (comparator.compare(value, parentNode.value) < 0) parentNode.lChild = currentNode;
        else parentNode.rChild = currentNode;

        while (parentNode.priority > currentNode.priority){
            if (comparator.compare(value, parentNode.value) < 0) rotateRight(parentNode);
            else rotateLeft(parentNode);

            parentNode = root;

            while((currentNode = moveOneStep(parentNode, value)) != null){
                if (comparator.compare(value, currentNode.value) == 0) break;
                else parentNode = currentNode;
            }
        }

        return true;
    }

    private void rotateRight(node rotationNode){
        node rChild = rotationNode.rChild;

        //Copying the rotationNode and its left child over as its own left child
        rotationNode.rChild = new node(rotationNode.value);
        rotationNode.rChild.priority = rotationNode.priority; //rotationNode.rChild will from now on be the "rotated" rotationNode
        rotationNode.rChild.rChild = rChild;

        //Setting the right child of left child, as the left child of the newly "roted" node
        rotationNode.rChild.lChild = rotationNode.lChild.rChild;
        rotationNode.lChild.rChild = null;

        //Copying left child values onto the rotation node
        rotationNode.value = rotationNode.lChild.value;
        rotationNode.priority = rotationNode.lChild.priority;

        //Removing the left child
        rotationNode.lChild = rotationNode.lChild.lChild;
    }

    private void rotateLeft(node rotationNode){
        node lChild = rotationNode.lChild;

        //Copying the rotationNode and its left child over as its own left child
        rotationNode.lChild = new node(rotationNode.value);
        rotationNode.lChild.priority = rotationNode.priority; //rotationNode.lChild will from now on be the "rotated" rotationNode
        rotationNode.lChild.lChild = lChild;

        //Setting the left child of right child, as the right child of the newly "roted" (for now created) node
        rotationNode.lChild.rChild = rotationNode.rChild.lChild;
        rotationNode.rChild.lChild = null;

        //Copying right child values onto the rotation node
        rotationNode.value = rotationNode.rChild.value;
        rotationNode.priority = rotationNode.rChild.priority;

        //Removing the right child
        rotationNode.rChild = rotationNode.rChild.rChild;
    }

    @Override
    public boolean remove(T value) {
        if (!lookUp(value)) return false;

        node parentNode = root;
        node nodeToRemove;

        while((nodeToRemove = moveOneStep(parentNode, value)) != null){
            if (comparator.compare(value, nodeToRemove.value) == 0) break;
            else parentNode = nodeToRemove;
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
        while (nodeToRemove.lChild != null && nodeToRemove.rChild != null){

            node forSwapping = nodeToRemove;
            if (nodeToRemove.lChild.priority < nodeToRemove.rChild.priority){
                rotateRight(nodeToRemove);
                nodeToRemove = nodeToRemove.rChild;
            }
            else{
                rotateLeft(nodeToRemove);
                nodeToRemove = nodeToRemove.lChild;
            }
            parentNode = forSwapping;

        }
        removeNodeWithUpToOneChild(nodeToRemove, parentNode);
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
                    representation = representation.concat(node.toString());
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
                    representation = representation.concat(node.toString());
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

        return representation.substring(0, representation.length() - 1);
    }
}
