package com.mihsathe.algokit.algos.sort;

import java.util.List;
import java.util.function.BiPredicate;

public interface Sorter<K> {

	List<K> sort(final List<K> input, final BiPredicate<K,K> isGreater);

    String getAlgorithmName();

}
