package org.example.assignment1.metrics;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MetricCollectorTest {
    @Test
    void testDepthTracking() {
        MetricCollector mc = new MetricCollector();
        mc.enterRecursion();
        assertEquals(1, mc.getDepth());
        mc.exitRecursion();
        assertEquals(0, mc.getDepth());
    }

    @Test
    void testComparisonsTracking() {
        MetricCollector mc = new MetricCollector();
        mc.incrementComparisons();
        assertEquals(1, mc.getComparisons());
    }
}