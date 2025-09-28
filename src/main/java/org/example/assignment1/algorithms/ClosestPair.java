package org.example.assignment1.algorithms;

public class ClosestPair {
    static class Point {
        int x, y;
        Point(int x, int y) { this.x = x; this.y = y; }
    }

    public double findClosestPair(Point[] points) {
        if (points == null || points.length < 2) return Double.POSITIVE_INFINITY;
        Point[] pointsByX = points.clone();
        java.util.Arrays.sort(pointsByX, (a, b) -> Integer.compare(a.x, b.x));
        return closestPairRec(pointsByX, 0, pointsByX.length - 1);
    }

    private double closestPairRec(Point[] pointsByX, int low, int high) {
        if (high - low <= 3) return bruteForce(pointsByX, low, high);
        int mid = (low + high) / 2;
        double dl = closestPairRec(pointsByX, low, mid);
        double dr = closestPairRec(pointsByX, mid + 1, high);
        double d = Math.min(dl, dr);
        // Простая реализация strip check (нужна доработка)
        return stripClosest(pointsByX, mid, d);
    }

    private double stripClosest(Point[] pointsByX, int mid, double d) {
        java.util.List<Point> strip = new java.util.ArrayList<>();
        int midX = pointsByX[mid].x;
        for (Point p : pointsByX) {
            if (Math.abs(p.x - midX) < d) strip.add(p);
        }
        strip.sort(java.util.Comparator.comparingInt(p -> p.y));
        double minDist = d;
        for (int i = 0; i < strip.size(); i++) {
            for (int j = i + 1; j < strip.size() && (strip.get(j).y - strip.get(i).y) < minDist; j++) {
                minDist = Math.min(minDist, distance(strip.get(i), strip.get(j)));
            }
        }
        return minDist;
    }


    private double bruteForce(Point[] points, int low, int high) {
        double minDist = Double.POSITIVE_INFINITY;
        for (int i = low; i <= high; i++)
            for (int j = i + 1; j <= high; j++)
                minDist = Math.min(minDist, distance(points[i], points[j]));
        return minDist;
    }

    private double distance(Point p1, Point p2) {
        return Math.sqrt(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2));
    }
}