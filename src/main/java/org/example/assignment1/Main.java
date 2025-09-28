package org.example.assignment1;

import org.example.assignment1.algorithms.ClosestPair;
import org.example.assignment1.algorithms.DeterministicSelect;
import org.example.assignment1.algorithms.MergeSort;
import org.example.assignment1.algorithms.QuickSort;
import org.example.assignment1.metrics.MetricCollector;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        for (int n = 100; n <= 2000; n += 100) {
            // Генерация данных
            int[] arr = new int[n];
            Random rand = new Random();
            for (int i = 0; i < n; i++) arr[i] = rand.nextInt(1000);

            ClosestPair.Point[] points = new ClosestPair.Point[n];
            for (int i = 0; i < n; i++) points[i] = new ClosestPair.Point(rand.nextInt(1000), rand.nextInt(1000));

            // Запуск алгоритмов и запись метрик
            MergeSort mergeSort = new MergeSort();
            mergeSort.sort(arr.clone());

            QuickSort quickSort = new QuickSort();
            quickSort.sort(arr.clone());

            MetricCollector mc = new MetricCollector();
            DeterministicSelect select = new DeterministicSelect(mc);
            select.select(arr.clone(), n / 2);

            ClosestPair closestPair = new ClosestPair();
            closestPair.findClosestPair(points);
        }

        System.out.println("metrics in data.");
    }
}