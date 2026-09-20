package org.example;

public class Metrics {
    private long comparisons = 0;
    private int currentRecursionDepth = 0;
    private int maxRecursionDepth = 0;

    public void incrementComparisons() {
        comparisons++;
    }

    public void enterRecursion() {
        currentRecursionDepth++;
        if (currentRecursionDepth > maxRecursionDepth) {
            maxRecursionDepth = currentRecursionDepth;
        }
    }

    public void exitRecursion() {
        if (currentRecursionDepth > 0) {
            currentRecursionDepth--;
        }
    }

    public long getComparisons() {
        return comparisons;
    }

    public int getMaxRecursionDepth() {
        return maxRecursionDepth;
    }

    public void reset() {
        comparisons = 0;
        currentRecursionDepth = 0;
        maxRecursionDepth = 0;
    }
}