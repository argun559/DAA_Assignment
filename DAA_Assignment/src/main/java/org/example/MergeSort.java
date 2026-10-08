package org.example;

public class MergeSort {

    private static final int INSERTION_SORT_THRESHOLD = 15;

    public static void sort(int[] a, Metrics metrics) {
        if (a == null || a.length <= 1) {
            return;
        }
       
        int[] buffer = new int[a.length];
        sort(a, 0, a.length - 1, buffer, metrics);
    }

    private static void sort(int[] a, int left, int right, int[] buffer, Metrics metrics) {
     
        if (right - left <= INSERTION_SORT_THRESHOLD) {
            insertionSort(a, left, right, metrics);
            return;
        }

        metrics.enterRecursion();
        try {
            int mid = left + (right - left) / 2;
            sort(a, left, mid, buffer, metrics);
            sort(a, mid + 1, right, buffer, metrics);
            merge(a, left, mid, right, buffer, metrics);
        } finally {
            metrics.exitRecursion();
        }
    }

    private static void merge(int[] a, int left, int mid, int right, int[] buffer, Metrics metrics) {
 
        for (int i = left; i <= right; i++) {
            buffer[i] = a[i];
        }

        int i = left;
        int j = mid + 1;
        for (int k = left; k <= right; k++) {
            if (i > mid) {
                a[k] = buffer[j++];
            } else if (j > right) {
                a[k] = buffer[i++];
            } else {
                metrics.incrementComparisons();
                if (buffer[j] < buffer[i]) {
                    a[k] = buffer[j++];
                } else {
                    a[k] = buffer[i++];
                }
            }
        }
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
