package com.mihsathe.algokit.algos.search;

import java.util.List;
import java.util.function.BiPredicate;

public interface Searcher<K extends Comparable<K>> {

    int search(final List<K> list, final int start, final int end, final K searchable, final BiPredicate<K,K> isGreater);

}
