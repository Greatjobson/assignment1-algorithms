package org.example.assignment1.algorithms;

import org.example.assignment1.metrics.MetricCollector;

public class DeterministicSelect {
    private final MetricCollector mc;

    public DeterministicSelect(MetricCollector mc) {
        this.mc = mc;
    }

    public int select(int[] array, int k) {
        if (array == null || k < 0 || k >= array.length) {
            throw new IllegalArgumentException("Invalid input");
        }
        mc.startTimer();
        mc.enterRecursion();
        int index = selectIndex(array, 0, array.length - 1, k);
        mc.exitRecursion();
        long elapsedTime = mc.stopTimer();
        try {
            mc.writeToCsv("data/select_metrics.csv", array.length, elapsedTime);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return array[index];
    }

    private int selectIndex(int[] array, int low, int high, int k) {
        mc.enterRecursion();
        int result;
        while (true) {
            if (low == high) {
                result = low;
                break;
            }
            int n = high - low + 1;
            if (n <= 5) {
                insertionSort(array, low, high);
                result = low + k;
                break;
            }
            int pivotIndex = choosePivot(array, low, high);
            pivotIndex = partition(array, low, high, pivotIndex);
            int rank = pivotIndex - low;
            if (k == rank) {
                result = pivotIndex;
                break;
            } else if (k < rank) {
                int leftSize = rank;
                int rightSize = high - pivotIndex;
                if (leftSize <= rightSize) {
                    result = selectIndex(array, low, pivotIndex - 1, k);
                    break;
                } else {
                    high = pivotIndex - 1;
                }
            } else {
                int leftSize = rank;
                int rightSize = high - pivotIndex;
                int newK = k - (rank + 1);
                if (rightSize <= leftSize) {
                    result = selectIndex(array, pivotIndex + 1, high, newK);
                    break;
                } else {
                    low = pivotIndex + 1;
                    k = newK;
                }
            }
        }
        mc.exitRecursion();
        return result;
    }

    private int choosePivot(int[] array, int low, int high) {
        int n = high - low + 1;
        int numGroups = (n + 4) / 5;
        int medHigh = low + numGroups - 1;
        for (int i = 0; i < numGroups; i++) {
            int gLow = low + i * 5;
            int gHigh = Math.min(gLow + 4, high);
            insertionSort(array, gLow, gHigh);
            int medPos = (gLow + gHigh) / 2;
            swap(array, low + i, medPos);
        }
        int medK = numGroups / 2;
        return selectIndex(array, low, medHigh, medK);
    }

    private int partition(int[] array, int low, int high, int pivotIndex) {
        int pivotValue = array[pivotIndex];
        swap(array, pivotIndex, high);
        int storeIndex = low;
        for (int i = low; i < high; i++) {
            mc.incrementComparisons();
            if (array[i] < pivotValue) {
                swap(array, i, storeIndex);
                storeIndex++;
            }
        }
        swap(array, storeIndex, high);
        return storeIndex;
    }

    private void insertionSort(int[] array, int low, int high) {
        for (int i = low + 1; i <= high; i++) {
            int key = array[i];
            int j = i - 1;
            while (j >= low) {
                mc.incrementComparisons();
                if (array[j] > key) {
                    array[j + 1] = array[j];
                    j--;
                } else {
                    break;
                }
            }
            array[j + 1] = key;
        }
    }

    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}