package org.example.assignment1.algorithms;

import org.example.assignment1.metrics.MetricCollector;
import java.util.Arrays;

public class ClosestPair {
    private MetricCollector mc = new MetricCollector();

    public static class Point {
        int x, y;
        public Point(int x, int y) { this.x = x; this.y = y; }
    }

    public double findClosestPair(Point[] points) {
        if (points == null || points.length < 2) return Double.POSITIVE_INFINITY;
        mc.startTimer();
        mc.enterRecursion();
        Point[] pointsByX = points.clone();
        Arrays.sort(pointsByX, (a, b) -> Integer.compare(a.x, b.x));
        double result = closestPairRec(pointsByX, 0, pointsByX.length - 1);
        mc.exitRecursion();
        try {
            mc.writeToCsv("data/closestpair_metrics.csv", points.length, mc.stopTimer());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    private double closestPairRec(Point[] pointsByX, int low, int high) {
        mc.enterRecursion();
        double result;
        if (high - low <= 3) {
            result = bruteForce(pointsByX, low, high);
        } else {
            int mid = (low + high) / 2;
            double dl = closestPairRec(pointsByX, low, mid);
            double dr = closestPairRec(pointsByX, mid + 1, high);
            double d = Math.min(dl, dr);
            result = stripClosest(pointsByX, mid, d, low, high);
        }
        mc.exitRecursion();
        return result;
    }

    private double stripClosest(Point[] pointsByX, int mid, double d, int low, int high) {
        Point[] strip = Arrays.stream(pointsByX)
                .filter(p -> Math.abs(p.x - pointsByX[mid].x) < d)
                .toArray(Point[]::new);
        Arrays.sort(strip, (a, b) -> Integer.compare(a.y, b.y));
        int stripSize = strip.length;
        double minStripDist = d;
        for (int i = 0; i < stripSize; i++)
            for (int j = i + 1; j < stripSize && (strip[j].y - strip[i].y) < d; j++) {
                mc.incrementComparisons();
                minStripDist = Math.min(minStripDist, distance(strip[i], strip[j]));
            }
        return minStripDist;
    }

    private double bruteForce(Point[] points, int low, int high) {
        double minDist = Double.POSITIVE_INFINITY;
        for (int i = low; i <= high; i++)
            for (int j = i + 1; j <= high; j++) {
                mc.incrementComparisons();
                minDist = Math.min(minDist, distance(points[i], points[j]));
            }
        return minDist;
    }

    private double distance(Point p1, Point p2) {
        return Math.sqrt(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2));
    }
}