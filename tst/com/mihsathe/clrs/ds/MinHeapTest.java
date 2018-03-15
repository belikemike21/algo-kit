package com.mihsathe.clrs.ds;

import org.junit.Test;

import static org.junit.Assert.*;

public class MinHeapTest {

    @Test
    public void testHeapify() {
        final MinHeap minHeap = new MinHeap(new int[] {1, 3, 5, 2}, 4);
        minHeap.minHeapify(2);

        assertArrayEquals(minHeap.getUnderlyingArray(), new int[] {1, 2, 5, 3});
    }

    @Test
    public void testBuildHeap() {
        final MinHeap minHeap = new MinHeap(new int[] {1, 3, 5, 8, 0}, 0);
        minHeap.buildHeap(5);

        assertArrayEquals(minHeap.getUnderlyingArray(), new int[] {0, 1, 5, 8, 3});
    }

}
