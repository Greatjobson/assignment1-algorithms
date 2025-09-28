package org.example.assignment1.algorithms;

import org.example.assignment1.metrics.MetricCollector;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class DeterministicSelectTest {

    @Test
    public void testRandomTrials() {
        MetricCollector mc = new MetricCollector();
        DeterministicSelect ds = new DeterministicSelect(mc);
        Random rand = new Random();
        for (int trial = 0; trial < 100; trial++) {
            int n = rand.nextInt(10000) + 1;
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = rand.nextInt(1000000);
            }
            int[] sorted = Arrays.copyOf(arr, n);
            Arrays.sort(sorted);
            int k = rand.nextInt(n);
            int[] arrCopy = Arrays.copyOf(arr, n);
            int actual = ds.select(arrCopy, k);
            assertEquals(sorted[k], actual, "Failed for trial " + trial + ", n=" + n + ", k=" + k);
        }
    }

    @Test
    public void testEdgeCases() {
        MetricCollector mc = new MetricCollector();
        DeterministicSelect ds = new DeterministicSelect(mc);

        int[] arr1 = {42};
        assertEquals(42, ds.select(Arrays.copyOf(arr1, arr1.length), 0));

        int[] arr2 = {3, 1};
        assertEquals(1, ds.select(Arrays.copyOf(arr2, arr2.length), 0));
        assertEquals(3, ds.select(Arrays.copyOf(arr2, arr2.length), 1));

        int[] arrDup = {1, 3, 2, 2, 4};
        assertEquals(2, ds.select(Arrays.copyOf(arrDup, arrDup.length), 1));
        assertEquals(2, ds.select(Arrays.copyOf(arrDup, arrDup.length), 2));

        int[] arrSorted = {1, 2, 3, 4, 5};
        assertEquals(3, ds.select(Arrays.copyOf(arrSorted, arrSorted.length), 2));

        int[] arrReverse = {5, 4, 3, 2, 1};
        assertEquals(3, ds.select(Arrays.copyOf(arrReverse, arrReverse.length), 2));

        int[] arrSame = {7, 7, 7, 7};
        assertEquals(7, ds.select(Arrays.copyOf(arrSame, arrSame.length), 0));
        assertEquals(7, ds.select(Arrays.copyOf(arrSame, arrSame.length), 3));
    }

}