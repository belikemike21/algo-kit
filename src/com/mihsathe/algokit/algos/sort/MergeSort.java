package com.mihsathe.algokit.algos.sort;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;

public class MergeSort<K> implements Sorter<K> {

    @Override
    public String getAlgorithmName() {
        return "MergeSort";
    }

    @Override
    public List<K> sort(final List<K> input, final BiPredicate<K, K> isGreater) {
        if (input.size() == 1) {
            return input;
        }

        if (input.size() == 2) {
            if (isGreater.test(input.get(0), input.get(1))) {
                K temp = input.get(0);
                input.set(0, input.get(1));
                input.set(1, temp);
            }

            return input;
        }

        final int mid = input.size() / 2;

        final List<K> partition1 = sort(partition(input, 0, mid), isGreater);
        final List<K> partition2 = sort(partition(input, mid+1, input.size() - 1), isGreater);

        return merge(partition1, partition2, isGreater);
    }

    private List<K> partition(final List<K> input, final int start, final int end) {
        return input.subList(start, end+1);
    }

    private List<K> merge(final List<K> partition1, final List<K> partition2, final BiPredicate<K, K> isGreater) {
        final List<K> output = new ArrayList<>();

        int ptr1 = 0, ptr2 = 0;
        while(output.size() < partition1.size() + partition2.size() && ptr1 < partition1.size() && ptr2 < partition2.size()) {
            if(isGreater.test(partition1.get(ptr1), partition2.get(ptr2))) {
                output.add(partition2.get(ptr2++));
            } else {
                output.add(partition1.get(ptr1++));
            }
        }

        while(ptr1 < partition1.size()) output.add(partition1.get(ptr1++));
        while(ptr2 < partition2.size()) output.add(partition2.get(ptr2++));

        return output;
    }

}
