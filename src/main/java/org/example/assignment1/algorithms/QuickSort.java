package org.example.assignment1.algorithms;

import org.example.assignment1.metrics.MetricCollector;
import java.util.Random;

public class QuickSort {
    private MetricCollector mc = new MetricCollector();
    private Random rand = new Random();
    private static final int INSERTION_SORT_THRESHOLD = 16;

    public void sort(int[] arr) {
        if (arr == null || arr.length == 0) return;
        mc.startTimer();
        quickSort(arr, 0, arr.length - 1);
        try {
            mc.writeToCsv("quicksort_metrics.csv", arr.length, mc.stopTimer());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void quickSort(int[] arr, int low, int high) {
        while (low < high) {
            if (high - low <= INSERTION_SORT_THRESHOLD) {
                insertionSort(arr, low, high);
                break;
            }
            mc.enterRecursion();
            int pivotIdx = partition(arr, low, high);
            if (pivotIdx - low < high - pivotIdx) {
                quickSort(arr, low, pivotIdx - 1);
                low = pivotIdx + 1;
            } else {
                quickSort(arr, pivotIdx + 1, high);
                high = pivotIdx - 1;
            }
            mc.exitRecursion();
        }
    }

    private int partition(int[] arr, int low, int high) {
        int pivotIdx = low + rand.nextInt(high - low + 1);
        swap(arr, pivotIdx, high);
        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            mc.incrementComparisons();
            if (arr[j] <= pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return i + 1;
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private void insertionSort(int[] arr, int low, int high) {
        for (int i = low + 1; i <= high; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= low) {
                mc.incrementComparisons();
                if (arr[j] > key) {
                    arr[j + 1] = arr[j];
                    j--;
                } else {
                    break;
                }
            }
            arr[j + 1] = key;
        }
    }
}