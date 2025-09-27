package org.example.assignment1.metrics;

import java.io.PrintWriter;

public class MetricCollector {
    private int depth = 0;
    private long comparisons = 0;
    private long startTime = 0;

    public void enterRecursion() { depth++; }
    public void exitRecursion() { depth--; }
    public void incrementComparisons() { comparisons++; }
    public void startTimer() { startTime = System.nanoTime(); }
    public long stopTimer() { return System.nanoTime() - startTime; }

    public int getDepth() { return depth; }
    public long getComparisons() { return comparisons; }

    public void writeToCsv(String fileName, int n, long elapsedTime) throws Exception {
        try (PrintWriter writer = new PrintWriter(fileName)) {
            writer.println("n,time,depth,comparisons");
            writer.printf("%d,%d,%d,%d%n", n, elapsedTime, getDepth(), getComparisons());
        }
    }
}