package org.example.assignment1.util;

import java.util.Random;

public class Util {
    private static final Random rand = new Random();

    // Guards:
    public static void checkArray(int[] arr) {
        if (arr == null) throw new IllegalArgumentException("Array cannot be null");
        if (arr.length == 0) throw new IllegalArgumentException("Array cannot be empty");
    }

    public static void checkArray(Point[] arr) {
        if (arr == null) throw new IllegalArgumentException("Array cannot be null");
        if (arr.length < 2) throw new IllegalArgumentException("Array must have at least 2 points");
    }

    // Swap:
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // Shuffle:
    public static void shuffle(int[] arr) {
        for (int i = arr.length - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            swap(arr, i, j);
        }
    }

    // Partition:
    public static int partition(int[] arr, int low, int high) {
        int pivotIdx = low + rand.nextInt(high - low + 1);
        swap(arr, pivotIdx, high);
        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return i + 1;
    }

    // Closest Pair
    public static class Point {
        int x, y;
        Point(int x, int y) { this.x = x; this.y = y; }
    }
}