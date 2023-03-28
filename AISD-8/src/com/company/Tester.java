package com.company;

import java.time.Duration;
import java.time.Instant;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class Tester {

    public static <T> RunResult runOnce(PriorityQueueSorter<T> queueSorter, List<T> values, Comparator<T> comparator) {

        Instant start = Instant.now();
        queueSorter.sort(values);
        Instant end = Instant.now();

        return new RunResult(Duration.between(start, end).toMillis(), isSortedLowToHigh(values, comparator));
    }

    public static <T> Result runNTimes(PriorityQueueSorter<T> queueSorter, Generator<T> generator, int repetitions, Comparator<T> comparator) {

        boolean isSorted = true;

        double averageTime = 0.0;
        double averageTimeSquared = 0.0;

        for (int n = 1; n <= repetitions; ++n) {
            RunResult result = runOnce(queueSorter, generator.generate(), comparator);

            averageTime = updatedAverage(averageTime, result.timeInMilliseconds(), n);
            averageTimeSquared = updatedAverage(averageTimeSquared, (double) result.timeInMilliseconds() * (double) result.timeInMilliseconds(), n);

            if (isSorted){
                isSorted = result.isSorted();
            }
        }

        return new Result(averageTime, calculateStdDev(averageTime, averageTimeSquared), isSorted);
    }

    private static double updatedAverage(double average, double value, int n) {
        return average + (value - average) / n;
    }

    private static double calculateStdDev(double average, double averagedSquares) {
        return Math.sqrt(averagedSquares - (average * average));
    }


    private static <T> boolean isSortedLowToHigh(List<T> list, Comparator<T> comparator){
        boolean isSorted = true;

        Iterator<T> iterator = list.iterator();

        T value1 = iterator.next();

        while (iterator.hasNext()) {
            T value2 = iterator.next();

            if (comparator.compare(value1, value2) > 0) {
                isSorted = false;
            }

            value1 = value2;
        }
        return isSorted;
    }
}