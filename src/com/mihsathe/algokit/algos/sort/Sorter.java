package com.mihsathe.algokit.algos.sort;

import java.util.List;
import java.util.function.BiPredicate;

public interface Sorter<K> {

    List<K> sort(final List<K> input, final BiPredicate<K,K> isGreater);

    String getAlgorithmName();

    default int isSorted(final List<K> input, final BiPredicate<K,K> isGreater) {
        boolean sorted = true;
        boolean antiSorted = true;

        for(int i=1; i<input.size(); i++) {
            if(isGreater.test(input.get(i), input.get(i-1))) {
                antiSorted = false;
            } else {
                sorted = false;
            }
        }

        if(sorted) {
            return 1;
        } else if(antiSorted) {
            return -1;
        }

        return 0;
    }

}
