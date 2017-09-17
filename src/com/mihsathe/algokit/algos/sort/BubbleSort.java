package com.mihsathe.algokit.algos.sort;

import java.util.Collections;
import java.util.List;
import java.util.function.BiPredicate;

public class BubbleSort<K extends Comparable<K>> implements Sorter<K> {

    public String getAlgorithmName() {
        return "BubbleSort";
    }

    public List<K> sort(final List<K> input, final BiPredicate<K, K> isGreater) {
        int sortedResult = isSorted(input, isGreater);

        if (sortedResult == 1) return input;
        if (sortedResult == -1) {
            Collections.reverse(input);
            return input;
        }

        final int size = input.size();

        // Pick one number at a time
        for (int i=0; i<size; i++) {

            // Compare with every other number
            for(int j=i+1; j<size; j++) {

                // Replace if we find a smaller number in later place
                if(isGreater.test(input.get(i), input.get(j))) {
                    final K temp = input.get(i);
                    input.set(i, input.get(j));
                    input.set(j, temp);
                }
            }
        }

        return input;
    }

}