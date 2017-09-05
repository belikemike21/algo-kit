package com.mihsathe.algokit.algos.search;

import java.util.List;
import java.util.function.BiPredicate;

/**
 * @author mihirsathe
 *
 * Keep in mind the input for binary search has to be sorted.
 * This class doesn't care for that.
 *
 * If we cannot find the thing we are looking for, we will return
 * the place where it should be so worth checking if the location
 * returned has the right searchable. While this is not the most
 * standard implementation of a search algorithm though it makes it
 * so much useful to be used in other algorithms.
 */
public class BinarySearch<K extends Comparable<K>> implements Searcher<K> {

    @Override
    public int search(final List<K> list, final int start, final int end, final K searchable, final BiPredicate<K,K> isGreater) {
            return _search(list, start, end, isGreater, searchable);
    }

    private int _search(final List<K> list, final int start, final int end, final BiPredicate<K,K> isGreater, final K searchable) {
        final int mid = (start + end) / 2;
        if(list.get(mid) == searchable) return mid;

            if(start == end) {
                    if (isGreater.test(list.get(mid), searchable)) {
                        return mid;
                    } else {
                        return mid + 1;
                    }
            }

            if(isGreater.test(list.get(mid), searchable)) {
                return _search(list, start, mid, isGreater, searchable);
            } else {
                return _search(list, mid+1, end, isGreater, searchable);
            }
    }

}
