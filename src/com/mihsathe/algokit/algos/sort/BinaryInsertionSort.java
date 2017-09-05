package com.mihsathe.algokit.algos.sort;

import java.util.List;
import java.util.function.BiPredicate;

import com.mihsathe.algokit.algos.search.BinarySearch;


/**
 * @author mihirsathe
 *
 * Uses binary search to find the place to put the current selected element. This doesn't
 * really bring the worst case down but will make dramatic difference in runtimes
 * when not too many replacements are required like for pre-sorted arrays or near-sorted
 * arrays.
 */
public class BinaryInsertionSort<K extends Comparable<K>> implements Sorter<K> {

    public String getAlgorithmName() {
        return "BinaryInsertionSort";
    }

    public List<K> sort(final List<K> input, final BiPredicate<K, K> isGreater) {
        BinarySearch<K> searcher = new BinarySearch<>();

            for(int i=0; i<input.size(); i++) {
                final int position = searcher.search(input, 0, i-1, input.get(i), isGreater);
                input.add(position, input.get(i));
            input.remove(i+1);
        }

        return input;
    }

}
