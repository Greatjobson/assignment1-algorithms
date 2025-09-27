package org.example.assignment1.metrics;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

public class MetricCollector {
    private int depth = 0;
    private int maxDepth = 0;
    private long comparisons = 0;
    private long startTime = 0;

    public void enterRecursion() {
        depth++;
        if (depth > maxDepth) maxDepth = depth;
    }

    public void exitRecursion() { depth--; }

    public void incrementComparisons() { comparisons++; }

    public void startTimer() { startTime = System.nanoTime(); }

    public long stopTimer() { return System.nanoTime() - startTime; }

    public int getDepth() { return depth; }

    public int getMaxDepth() { return maxDepth; }

    public long getComparisons() { return comparisons; }

    public void writeToCsv(String fileName, int n, long elapsedTime) throws Exception {
        File file = new File(fileName);
        boolean append = file.exists();
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName, append))) {
            if (!append) {
                writer.println("n,time,maxDepth,comparisons"); // Заголовок только для нового файла
            }
            writer.printf("%d,%d,%d,%d%n", n, elapsedTime, getMaxDepth(), getComparisons());
        }
    }
}