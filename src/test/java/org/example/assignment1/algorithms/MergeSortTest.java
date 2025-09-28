package org.example.assignment1.algorithms;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
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
    public void testRandomArrays() {
        MergeSort ms = new MergeSort();
        Random rand = new Random();
        for (int trial = 0; trial < 50; trial++) {
            int n = rand.nextInt(1000) + 1;
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) arr[i] = rand.nextInt(10000);
            int[] arrCopy = arr.clone();
            ms.sort(arrCopy);
            Arrays.sort(arr); // Ожидаемый результат
            assertArrayEquals(arr, arrCopy, "Failed for random array, trial " + trial + ", n=" + n);
        }
    }
}