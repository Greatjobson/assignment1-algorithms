package org.example.assignment1.algorithms;
import org.example.assignment1.metrics.MetricCollector;
import org.example.assignment1.util.Util;

public class QuickSort {
    private static final int INSERTION_SORT_THRESHOLD = 16;
    MetricCollector mc = new MetricCollector();
    public void sort(int[] arr) {
        if (arr == null || arr.length == 0) return;

        mc.startTimer();
        quickSort(arr, 0, arr.length - 1);
        try {
            mc.writeToCsv("data/quicksort_metrics.csv", arr.length, mc.stopTimer());
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
            int pivotIdx = Util.partition(arr, low, high);
            if (pivotIdx - low < high - pivotIdx) {
                quickSort(arr, low, pivotIdx - 1);
                low = pivotIdx + 1;
            } else {
                quickSort(arr, pivotIdx + 1, high);
                high = pivotIdx - 1;
            }
        }
    }

    private void insertionSort(int[] arr, int low, int high) {
        for (int i = low + 1; i <= high; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= low && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }
}