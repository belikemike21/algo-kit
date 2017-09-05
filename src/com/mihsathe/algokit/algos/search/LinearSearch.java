package com.mihsathe.algokit.algos.search;

import java.util.List;
import java.util.function.BiPredicate;

public class LinearSearch<K extends Comparable<K>> implements Searcher<K> {

    @Override
    public int search(final List<K> list, final int start, final int end, final K searchable, final BiPredicate<K,K> isGreater) {
        for(int i=start; i<=end; i++) {
                if(list.get(i).equals(searchable)) return i;
        }

        return -1;
    }

}
