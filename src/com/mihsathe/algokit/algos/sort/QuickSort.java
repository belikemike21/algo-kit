package com.mihsathe.algokit.algos.sort;

import java.util.List;
import java.util.function.BiPredicate;

public class QuickSort<K> implements Sorter<K> {

	@Override
    public String getAlgorithmName() {
        return "QuickSort";
    }

    @Override
    public List<K> sort(final List<K> input, final BiPredicate<K, K> isGreater) {
        _sort(input, 0, input.size() - 1, isGreater);

        return input;
    }

    // Nothing to return since this is an in-place algorithm.
    private void _sort(final List<K> input, final int start, final int end, final BiPredicate<K,K> isGreater) {
        if (start >= end) return;

        final int pivotLocation = partition(input, start, end, isGreater);

        // Since we didn't have to change anything to the pivot location,
        _sort(input, start, pivotLocation - 1, isGreater);
        _sort(input, pivotLocation + 1, end, isGreater);
    }

    private int partition(final List<K> input, final int start, final int end, final BiPredicate<K,K> isGreater) {
        pullPivotFirst(input, start, end);

        // Since we moved our pivot at the beginning.
        int pivotTracker = start+1;

        for (int i=start+1; i<=end; i++) {
            // Find smaller than pivot element and place them *before* pivotTracker
            if(isGreater.test(input.get(start), input.get(i))) {
                final K temp = input.get(pivotTracker);
                input.set(pivotTracker, input.get(i));
                input.set(i, temp);
                pivotTracker++;
            }
        }

        /*
         * At this point, all the elements smaller than pivot are residing before pivotTracker.
         * pivotTracker however is larger than pivot. So we'll exchange pivotTracker - 1 with
         * start.
         */
        final K temp = input.get(pivotTracker - 1);
        input.set(pivotTracker - 1, input.get(start));
        input.set(start, temp);

        return pivotTracker - 1;
    }

    /**
     * A chance to use a pivot selection algorithm. In the end, put your pivot element
     * as first. The algorithm will take it from there.
     */
    private void pullPivotFirst(final List<K> input, final int start, final int end) {
        final int pivot = (end + start) / 2;
        final K temp = input.get(pivot);
        input.set(pivot, input.get(start));
        input.set(start, temp);
    }

}
