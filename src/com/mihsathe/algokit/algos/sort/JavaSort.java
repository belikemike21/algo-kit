package com.mihsathe.algokit.algos.sort;

import java.util.Comparator;
import java.util.List;
import java.util.function.BiPredicate;

/**
 * @author mihirsathe
 *
 * I don't take credit for this one. Purely for comparison.
 */
public class JavaSort<K> implements Sorter<K> {

	@Override
    public String getAlgorithmName() {
        return "JavaSort";
    }

    @Override
    public List<K> sort(final List<K> input, final BiPredicate<K, K> isGreater) {
        input.sort(new Comp<K>(isGreater));
        return input;
    }

    private static class Comp<K> implements Comparator<K> {

    	    private final BiPredicate<K, K> isGreater;

    	    public Comp(final BiPredicate<K, K> isGreater) {
    	        this.isGreater = isGreater;
    	    }

		@Override
		public int compare(K a, K b) {
			return isGreater.test(a, b) ? 1 : a.equals(b) ? 0 : -1;
		}
        
    }

}
