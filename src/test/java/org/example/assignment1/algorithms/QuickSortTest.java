package org.example.assignment1.algorithms;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class QuickSortTest {
    @Test
    void testSmallArray() {
        int[] arr = {5, 3, 8, 4, 2};
        int[] expected = arr.clone();
        Arrays.sort(expected);

        QuickSort qs = new QuickSort();
        qs.sort(arr);

        assertArrayEquals(expected, arr);
    }

    @Test
    void testRandomArray() {
        Random rnd = new Random(42);
        int[] arr = rnd.ints(1000, -5000, 5000).toArray();
        int[] expected = arr.clone();
        Arrays.sort(expected);

        QuickSort qs = new QuickSort();
        qs.sort(arr);

        assertArrayEquals(expected, arr);
    }

    @Test
    void testRecursionDepthBound() {
        int n = 1 << 16; // 65536 элементов
        int[] arr = new Random(42).ints(n, -100000, 100000).toArray();


        QuickSort qs = new QuickSort();
        qs.sort(arr);

        long logN = (long) (Math.log(n) / Math.log(2));
        try (BufferedReader reader = new BufferedReader(new FileReader("quicksort_metrics.csv"))) {
            String header = reader.readLine();
            assertTrue(header != null && header.contains("n,time,maxDepth,comparisons"), "Header missing or incorrect");
            String data = reader.readLine();
            assertTrue(data != null && !data.isEmpty(), "Data line missing");
            String[] values = data.split(",");
            assertEquals(n, Integer.parseInt(values[0]), "n mismatch: expected " + n + ", got " + values[0]);
            assertTrue(Long.parseLong(values[1]) > 0, "Time should be positive"); // time
            long maxDepth = Integer.parseInt(values[2]);
            assertTrue(maxDepth > 0 && maxDepth <= logN * 3, "Recursion depth too high: " + maxDepth);
            assertTrue(Long.parseLong(values[3]) > 0, "Comparisons should be positive"); // comparisons
        } catch (IOException e) {
            throw new RuntimeException("Failed to read CSV", e);
        }
    }
}