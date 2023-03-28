package com.company;

public class Result {
    private double avgTime;
    private double timeStdDev;

    private boolean isSorted;

    public Result(double avgTime, double timeStdDev, boolean isSorted){
        this.avgTime = avgTime;
        this.timeStdDev = timeStdDev;
        this.isSorted = isSorted;
    }

    public double averageTimeInMilliseconds() {
        return avgTime;
    }

    public double timeStandardDeviation() {
        return timeStdDev;
    }

    public boolean isSorted() {
        return isSorted;
    }
}
