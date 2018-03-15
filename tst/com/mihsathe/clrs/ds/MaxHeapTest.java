package com.mihsathe.clrs.ds;

import org.junit.Test;

import static org.junit.Assert.*;

public class MaxHeapTest {

    @Test
    public void testHeapify() {
        final MaxHeap<Integer> maxHeap = new MaxHeap<>(
                new Integer[] { 101, 55, 68, 6, 59, 66, 66, 3, 3, 56, 56 }, 11);
        maxHeap.maxHeapify(2);

        assertArrayEquals(maxHeap.getUnderlyingArray(),
                new Integer[] { 101, 59, 68, 6, 56, 66, 66, 3, 3, 55, 56 });
    }

    @Test
    public void testBuildHeap() {
        final MaxHeap<Integer> maxHeap = new MaxHeap<>(new Integer[] { 1, 3, 5, 8 }, 0);
        maxHeap.buildHeap(4);

        assertArrayEquals(maxHeap.getUnderlyingArray(), new Integer[] { 8, 3, 5, 1 });
    }

    @Test
    public void testHeapSort() {
        final MaxHeap<Integer> maxHeap = new MaxHeap<>(
                new Integer[] { 101, 55, 68, 6, 59, 66 }, 6);
        maxHeap.heapSort();
        
        assertArrayEquals(maxHeap.getUnderlyingArray(), new Integer[] { 6, 55, 59, 66, 68, 101 });
    }

}
