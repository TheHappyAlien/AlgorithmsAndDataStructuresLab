package com.company;

public class Main {

    public static void main(String[] args) {

        ArrayHeap<Integer> heap = new ArrayHeap<>(new IntegerComparator(), 8);
        TreeHeap<Integer> heap1 = new TreeHeap<>(new IntegerComparator());

        PriorityQueueSorter<Integer> queueSorter = new PriorityQueueSorter<>(heap);
        PriorityQueueSorter<Integer> queueSorter1 = new PriorityQueueSorter<>(heap1);

        Generator<Integer> generator = new IntegerGenerator(20, 50);

        for (Integer number : generator.generate()) {
            heap1.add(number);
        }

        System.out.println(heap1);

//        Result result = Tester.runNTimes(queueSorter, generator, 10, new IntegerComparator());
//        Result result1 = Tester.runNTimes(queueSorter1, generator, 10, new IntegerComparator());
//
//        System.out.println("Array: " + result.averageTimeInMilliseconds() + " +- " + result.timeStandardDeviation());
//        System.out.println("Tree: " + result1.averageTimeInMilliseconds() + " +- " + result1.timeStandardDeviation());
//
//        System.out.println(result.isSorted());
//        System.out.println(result1.isSorted());
    }

}
