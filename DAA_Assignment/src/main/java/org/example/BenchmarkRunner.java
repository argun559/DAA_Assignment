package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class BenchmarkRunner {

    public static void main(String[] args) {
        int[] sizes = {100, 500, 1000, 5000, 10000, 50000};
        String csvFile = "results.csv";

        try (PrintWriter pw = new PrintWriter(new FileWriter(csvFile))) {
            // Заголовок CSV файла
            pw.println("Algorithm,Size,DataType,TimeMs,Comparisons,MaxRecursionDepth");

            Random random = new Random();

            for (int size : sizes) {
                int[] randomArray = random.ints(size, -100000, 100000).toArray();
                int[] sortedArray = randomArray.clone();
                java.util.Arrays.sort(sortedArray);

                // --- 1. MergeSort на случайных данных ---
                runMergeSortBenchmark(pw, randomArray, size, "Random");
                // --- 2. MergeSort на отсортированных данных ---
                runMergeSortBenchmark(pw, sortedArray, size, "Sorted");

                // --- 3. QuickSort на случайных данных ---
                runQuickSortBenchmark(pw, randomArray, size, "Random");
                // --- 4. QuickSort на отсортированных данных ---
                runQuickSortBenchmark(pw, sortedArray, size, "Sorted");

                // --- 5. QuickSelect на случайных данных (ищем медиану size / 2) ---
                runQuickSelectBenchmark(pw, randomArray, size, "Random");
            }

            System.out.println("Benchmark successfully completed! Results saved to " + csvFile);

        } catch (IOException e) {
            System.err.println("Error writing CSV file: " + e.getMessage());
        }
    }

    private static void runMergeSortBenchmark(PrintWriter pw, int[] original, int size, String dataType) {
        int[] data = original.clone();
        Metrics metrics = new Metrics();

        long startTime = System.nanoTime();
        MergeSort.sort(data, metrics);
        long endTime = System.nanoTime();

        double timeMs = (endTime - startTime) / 1_000_000.0;
        pw.printf("MergeSort,%d,%s,%.4f,%d,%d\n", size, dataType, timeMs, metrics.getComparisons(), metrics.getMaxRecursionDepth());
    }

    private static void runQuickSortBenchmark(PrintWriter pw, int[] original, int size, String dataType) {
        int[] data = original.clone();
        Metrics metrics = new Metrics();

        long startTime = System.nanoTime();
        QuickSort.sort(data, metrics);
        long endTime = System.nanoTime();

        double timeMs = (endTime - startTime) / 1_000_000.0;
        pw.printf("QuickSort,%d,%s,%.4f,%d,%d\n", size, dataType, timeMs, metrics.getComparisons(), metrics.getMaxRecursionDepth());
    }

    private static void runQuickSelectBenchmark(PrintWriter pw, int[] original, int size, String dataType) {
        int[] data = original.clone();
        Metrics metrics = new Metrics();
        int k = size / 2; // ищем средний элемент

        long startTime = System.nanoTime();
        QuickSelect.select(data, k, metrics);
        long endTime = System.nanoTime();

        double timeMs = (endTime - startTime) / 1_000_000.0;
        pw.printf("QuickSelect,%d,%s,%.4f,%d,%d\n", size, dataType, timeMs, metrics.getComparisons(), metrics.getMaxRecursionDepth());
    }
}