package com.mihsathe.clrs.ds;

import com.mihsathe.algokit.annotations.NotThreadSafe;

/**
 * Represents a MIN-HEAP for integers as described in chapter 6.
 * 
 * Terminology:
 * HeapI - 1 based position in heap
 * ArrayI - 0 based position in array
 *
 * @author mihirsathe
 */
@NotThreadSafe
public class MinHeap {

    /**
     * Array to represent the data of the heap.
     */
    private int [] heap;
    
    /**
     * Length to represent the portion of this array that's considered heap.
     */
    private int heapLength;

    /**
     * Constructor to make a heap out of the given array.
     * 
     * @param heap base array to construct a heap from.
     * @param heapLength part of the array that needs to be heapified. 
     */
    public MinHeap(final int[] heap, final int heapLength) {
        this.heap = heap;
        this.heapLength = heapLength;
        
        buildHeap(heapLength);
    }

    /**
     * Heapify to maintain the heap property from given heap index as root.
     */
    public void minHeapify(final int rootHeapI) {
        if(rootHeapI > heapLength) {
            // We're being asked to heapify something outside this heap
            throw new IllegalArgumentException();
        }

        if(isLeaf(rootHeapI)) {
            return;
        }

        final int left = rootHeapI * 2;
        final int right = rootHeapI * 2 + 1;

        int minPos = rootHeapI;

        if (left <= heapLength && heap[arrayI(left)] < heap[arrayI(rootHeapI)]) {
            minPos = left;
        }
        
        if (right <= heapLength && heap[arrayI(right)] < heap[arrayI(minPos)]) {
            minPos = right;
        }

        if (minPos != rootHeapI) {
            int temp = heap[arrayI(rootHeapI)];
            heap[arrayI(rootHeapI)] = heap[arrayI(minPos)];
            heap[arrayI(minPos)] = temp;
            
            // Recurse
            minHeapify(minPos);
        }
    }

    /**
     * Turn the current array into a heap.
     */
    public void buildHeap(final int newLength) {
        heapLength = newLength;
        int lastNonLeaf = heapLength / 2;
        
        for (int i = lastNonLeaf; i >= 1; i--) minHeapify(i);
    }

    public int[] getUnderlyingArray() {
        return heap;
    }

    /* Helpers */
    
    private boolean isLeaf(final int heapI) {
        return heapI > (heapLength / 2);
    }

    /* Heap calculations are much easier with 1 based index */
    
    private int arrayI(final int heapI) {
        return heapI - 1;
    }

}
