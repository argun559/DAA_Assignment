package org.example;

import java.util.Random;

public class QuickSort {
    private static final Random RANDOM = new Random();

    public static void sort(int[] a, Metrics metrics) {
        if (a == null || a.length <= 1) {
            return;
        }
        quickSort(a, 0, a.length - 1, metrics);
    }

    private static void quickSort(int[] a, int low, int high, Metrics metrics) {
        metrics.enterRecursion();
        try {
            while (low < high) {
                if (high - low <= 10) {
                    insertionSort(a, low, high, metrics);
                    break;
                }

                int randomIndex = low + RANDOM.nextInt(high - low + 1);
                swap(a, low, randomIndex);

                int[] pivotIndices = partition3Way(a, low, high, metrics);
                int lt = pivotIndices[0];
                int gt = pivotIndices[1];

                if (lt - low < high - gt) {
                    quickSort(a, low, lt - 1, metrics);
                    low = gt + 1;
                } else {
                    quickSort(a, gt + 1, high, metrics);
                    high = lt - 1;
                }
            }
        } finally {
            metrics.exitRecursion();
        }
    }

    private static int[] partition3Way(int[] a, int low, int high, Metrics metrics) {
        int lt = low;
        int gt = high;
        int i = low + 1;
        int pivot = a[low];

        while (i <= gt) {
            metrics.incrementComparisons();
            if (a[i] < pivot) {
                swap(a, lt++, i++);
            } else {
                metrics.incrementComparisons();
                if (a[i] > pivot) {
                    swap(a, i, gt--);
                } else {
                    i++;
                }
            }
        }
        return new int[]{lt, gt};
    }

    private static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    private static void insertionSort(int[] a, int left, int right, Metrics metrics) {
        for (int i = left + 1; i <= right; i++) {
            int key = a[i]; 
            int j = i - 1;
            while (j >= left) {
                metrics.incrementComparisons();
                if (a[j] > key) {
                    a[j + 1] = a[j];
                    j--;
                } else {
                    break;
                }
            }
            a[j + 1] = key;
        }
    }
}
