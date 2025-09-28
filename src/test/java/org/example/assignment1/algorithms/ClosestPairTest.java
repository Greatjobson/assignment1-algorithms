package org.example.assignment1.algorithms;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClosestPairTest {

    @Test
    public void testSmallArrays() {
        ClosestPair cp = new ClosestPair();
        ClosestPair.Point[] points = {
                new ClosestPair.Point(1, 1),
                new ClosestPair.Point(2, 2),
                new ClosestPair.Point(3, 3)
        };
        double expected = 1.4142135623730951; // Расстояние между (1,1) и (2,2)
        double actual = cp.findClosestPair(points);
        assertEquals(expected, actual, 0.0001, "Failed for small array");
    }
    @Test
    public void testStripClosest() {
        ClosestPair cp = new ClosestPair();
        ClosestPair.Point[] points = {
                new ClosestPair.Point(5, 1),
                new ClosestPair.Point(5, 2),
                new ClosestPair.Point(5, 3),
                new ClosestPair.Point(10, 2)
        };
        double expected = 1.0;
        double actual = cp.findClosestPair(points);
        assertEquals(expected, actual, 0.0001, "stripClosest не работает корректно");
    }

    @Test
    public void testRandomArrays() {
        ClosestPair cp = new ClosestPair();
        Random rand = new Random();
        for (int trial = 0; trial < 20; trial++) {
            int n = rand.nextInt(2000) + 1;
            ClosestPair.Point[] points = new ClosestPair.Point[n];
            for (int i = 0; i < n; i++) points[i] = new ClosestPair.Point(rand.nextInt(1000), rand.nextInt(1000));
            double actual = cp.findClosestPair(points);
            assertTrue(actual >= 0, "Distance cannot be negative");
        }
    }

    private void assertTrue(boolean condition, String message) {
        if (!condition) throw new AssertionError(message);
    }
}