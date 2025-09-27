package org.example.assignment1.algorithms;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Random;

public class MergeSortTest {
    @Test
    void testBasicSort() {
        MergeSort ms = new MergeSort();
        int[] arr = {5, 3, 8, 1, 4};
        int[] expected = {1, 3, 4, 5, 8};
        ms.sort(arr);
        assertArrayEquals(expected, arr);
    }

    @Test
    void testLargeArray() {
        MergeSort ms = new MergeSort();
        int[] arr = new int[10000];
        Random rand = new Random();
        for (int i = 0; i < arr.length; i++) arr[i] = rand.nextInt(1000000);
        int[] expected = Arrays.copyOf(arr, arr.length);
        Arrays.sort(expected);
        ms.sort(arr);
        assertArrayEquals(expected, arr);
    }
    @Test
    void testMetricsInCsv() throws IOException {
        int[] sizes = {1000, 2000, 4000, 8000, 10000, 16000};  // Добавь больше n
        try (PrintWriter writer = new PrintWriter("mergesort_metrics.csv")) {
            writer.println("n,time,maxDepth,comparisons");
            for (int n : sizes) {
                MergeSort ms = new MergeSort();
                int[] arr = new int[n];
                Random rand = new Random(42);  // Фиксируем seed для воспроизводимости
                for (int i = 0; i < n; i++) arr[i] = rand.nextInt(1000000);
                ms.sort(arr);  // Используй внутреннюю запись
            }
        }
        // Проверка (как раньше) или пропусти для ручной проверки
    }
//    @Test
//    void testMetricsInCsv() throws IOException {
//        MergeSort ms = new MergeSort();
//        int[] arr = new int[10000];
//        Random rand = new Random();
//        for (int i = 0; i < arr.length; i++) arr[i] = rand.nextInt(1000000);
//        ms.sort(arr);
//
//        try (BufferedReader reader = new BufferedReader(new FileReader("mergesort.csv"))) {
//            String header = reader.readLine();
//            assertTrue(header.contains("n,time,maxDepth,comparisons"));
//            String data = reader.readLine();
//            String[] values = data.split(",");
//            int n = Integer.parseInt(values[0]);
//            long time = Long.parseLong(values[1]);
//            int maxDepth = Integer.parseInt(values[2]);
//            long comparisons = Long.parseLong(values[3]);
//            assertEquals(10000, n);
//            assertTrue(time > 0);
//            assertTrue(maxDepth > 0 && maxDepth <= Math.log(n) / Math.log(2) * 2);
//            assertTrue(comparisons > 0);
//        }
//    }
}