package com.mihsathe.algokit.algos.sort;

import java.util.List;
import java.util.function.BiPredicate;

public class SelectionSort<K extends Comparable<K>> implements Sorter<K> {

    public String getAlgorithmName() {
        return "SelectionSort";
    }

    public List<K> sort(final List<K> input, final BiPredicate<K, K> isGreater) {
        final int size = input.size();

        // Pick one number at a time
        for (int i=0; i<size; i++) {

            K min = input.get(i);
            int minIndex = i;
            // Find min in rest of the array
            for(int j=i+1; j<size; j++) {
                if(isGreater.test(min, input.get(j))) {
                    min = input.get(j);
                    minIndex = j;
                }
            }
            
            // Now assign minIndex to i
            K temp = input.get(minIndex);
            input.set(minIndex, input.get(i));
            input.set(i, temp);
        }

        return input;
    }

}