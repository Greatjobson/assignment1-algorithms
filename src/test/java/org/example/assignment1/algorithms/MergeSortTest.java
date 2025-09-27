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
        int[] sizes = {1000, 2000, 4000, 8000, 10000, 16000};
        try (PrintWriter writer = new PrintWriter("mergesort_metrics.csv")) {
            writer.println("n,time,maxDepth,comparisons");
            for (int n : sizes) {
                MergeSort ms = new MergeSort();
                int[] arr = new int[n];
                Random rand = new Random(42);
                for (int i = 0; i < n; i++) arr[i] = rand.nextInt(1000000);
                ms.sort(arr);
            }
        }
    }

}