package com.company;

public class RunResult {
    private long timeMillis;
    private boolean isSorted;

    public RunResult(long timeMillis, boolean isSorted) {
        this.timeMillis = timeMillis;
        this.isSorted = isSorted;
    }

    public long timeInMilliseconds() {
        return timeMillis;
    }

    public boolean isSorted() {
        return isSorted;
    }
}
