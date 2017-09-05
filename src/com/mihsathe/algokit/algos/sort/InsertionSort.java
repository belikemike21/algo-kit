package com.mihsathe.algokit.algos.sort;

import java.util.List;
import java.util.function.BiPredicate;

public class InsertionSort<K extends Comparable<K>> implements Sorter<K> {

    public String getAlgorithmName() {
        return "InsertionSort";
    }

    public List<K> sort(final List<K> input, final BiPredicate<K, K> isGreater) {
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
