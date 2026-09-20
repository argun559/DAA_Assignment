package org.example;

import java.util.Arrays;

public class TestRunner {
    public static void main(String[] args) {
        System.out.println("Запуск проверки алгоритмов...");

        // 1. Тест MergeSort
        int[] mInput = {38, 27, 43, 3, 9, 82, 10};
        int[] mExpected = {3, 9, 10, 27, 38, 43, 82};
        Metrics mMetrics = new Metrics();
        MergeSort.sort(mInput, mMetrics);
        assert Arrays.equals(mInput, mExpected) : "Ошибка в MergeSort!";
        System.out.println("-> MergeSort пройден успешно! Сравнений: " + mMetrics.getComparisons());

        // 2. Тест QuickSort
        int[] qInput = {10, 7, 8, 9, 1, 5};
        int[] qExpected = {1, 5, 7, 8, 9, 10};
        Metrics qMetrics = new Metrics();
        QuickSort.sort(qInput, qMetrics);
        assert Arrays.equals(qInput, qExpected) : "Ошибка в QuickSort!";
        System.out.println("-> QuickSort пройден успешно! Сравнений: " + qMetrics.getComparisons());

        // 3. Тест QuickSelect
        int[] sInput = {7, 10, 4, 3, 20, 15};
        Metrics sMetrics1 = new Metrics();
        int val0 = QuickSelect.select(sInput.clone(), 0, sMetrics1);
        assert val0 == 3 : "Ошибка в QuickSelect (min)! Ожидалось 3, получено " + val0;

        Metrics sMetrics2 = new Metrics();
        int val3 = QuickSelect.select(sInput.clone(), 3, sMetrics2);
        assert val3 == 10 : "Ошибка в QuickSelect (k=3)! Ожидалось 10, получено " + val3;
        System.out.println("-> QuickSelect пройден успешно!");

        System.out.println("\nВСЕ АЛГОРИТМЫ РАБОТАЮТ БЕЗУПРЕЧНО!");
    }
}