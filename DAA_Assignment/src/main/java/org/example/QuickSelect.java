package org.example;

import java.util.Random;

public class QuickSelect {
    private static final Random RANDOM = new Random();

    public static int select(int[] a, int k, Metrics metrics) {
        if (a == null || a.length == 0) {
            throw new IllegalArgumentException("Array cannot be null or empty.");
        }
        if (k < 0 || k >= a.length) {
            throw new IllegalArgumentException("Index k is out of range: " + k);
        }

        return quickSelect(a, 0, a.length - 1, k, metrics);
    }

    private static int quickSelect(int[] a, int low, int high, int k, Metrics metrics) {
        metrics.enterRecursion();
        try {
            while (low <= high) {
                if (low == high) {
                    return a[low];
                }

                int pivotIndex = low + RANDOM.nextInt(high - low + 1);
                int splitIndex = partition(a, low, high, pivotIndex, metrics);

                if (splitIndex == k) {
                    return a[splitIndex];
                } else if (splitIndex > k) {
                    high = splitIndex - 1;
                } else {
                    low = splitIndex + 1;
                }
            }
            throw new IllegalStateException("Element not found");
        } finally {
            metrics.exitRecursion();
        }
    }

    private static int partition(int[] a, int low, int high, int pivotIndex, Metrics metrics) {
        int pivotValue = a[pivotIndex];
        swap(a, pivotIndex, high);

        int storeIndex = low;
        for (int i = low; i < high; i++) {
            metrics.incrementComparisons();
            if (a[i] < pivotValue) {
                swap(a, storeIndex, i);
                storeIndex++;
            }
        }
        swap(a, storeIndex, high);
        return storeIndex;
    }

    private static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}