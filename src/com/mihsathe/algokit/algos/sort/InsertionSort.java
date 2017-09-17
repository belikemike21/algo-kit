package com.mihsathe.algokit.algos.sort;

import java.util.Collections;
import java.util.List;
import java.util.function.BiPredicate;

public class InsertionSort<K extends Comparable<K>> implements Sorter<K> {

    public String getAlgorithmName() {
        return "InsertionSort";
    }

    public List<K> sort(final List<K> input, final BiPredicate<K, K> isGreater) {
        int sortedResult = isSorted(input, isGreater);

        if (sortedResult == 1) return input;
        if (sortedResult == -1) {
            Collections.reverse(input);
            return input;
        }

        for(int i=1; i<input.size(); i++) {
            for(int j=0; j<i; j++) {
                if(isGreater.test(input.get(j), input.get(i))) {
                    input.add(j, input.get(i));
                    input.remove(i+1);
                }
            }
        }

        return input;
    }

}
