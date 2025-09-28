package org.example.assignment1.algorithms;

import org.example.assignment1.metrics.MetricCollector;

public class MergeSort {
    private MetricCollector mc = new MetricCollector();
    private static final int INSERTION_SORT_THRESHOLD = 16;

    public void sort(int[] arr) {
        mc.startTimer();
        int[] buffer = new int[arr.length];
        mergeSort(arr, buffer, 0, arr.length - 1);
        try {
            mc.writeToCsv("data/mergesort_metrics.csv", arr.length, mc.stopTimer());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void mergeSort(int[] arr, int[] buffer, int left, int right) {
        if (right - left + 1 <= INSERTION_SORT_THRESHOLD) {
            insertionSort(arr, left, right);
            return;
        }
        if (left < right) {
            mc.enterRecursion();
            int mid = (left + right) / 2;
            mergeSort(arr, buffer, left, mid);
            mergeSort(arr, buffer, mid + 1, right);
            merge(arr, buffer, left, mid, right);
            mc.exitRecursion();
        }
    }

    private void merge(int[] arr, int[] buffer, int left, int mid, int right) {
        System.arraycopy(arr, left, buffer, left, right - left + 1);
        int i = left, j = mid + 1, k = left;
        while (i <= mid && j <= right) {
            mc.incrementComparisons();
            if (buffer[i] <= buffer[j]) {
                arr[k++] = buffer[i++];
            } else {
                arr[k++] = buffer[j++];
            }
        }
        while (i <= mid) arr[k++] = buffer[i++];
        while (j <= right) arr[k++] = buffer[j++];
    }

    private void insertionSort(int[] arr, int left, int right) {
        for (int i = left + 1; i <= right; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= left) {
                mc.incrementComparisons();  // Добавлено
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
