package org.example.assignment1.algorithms;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

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
    public void testDepthBound() {
        QuickSort qs = new QuickSort();
        int n = 1000;
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = i;
        int[] arrCopy = arr.clone();
        qs.sort(arrCopy);
        int[] expected = arr.clone();
        Arrays.sort(expected);
        assertArrayEquals(expected, arrCopy, "Failed for large sorted array");
    }
}