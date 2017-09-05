package com.mihsathe.algokit.algos.sort;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.BiPredicate;

public class ConcurrentMergeSort<K> implements Sorter<K> {

    private final ExecutorService threadPool;

    public ConcurrentMergeSort() {
        threadPool = Executors.newFixedThreadPool(130);
    }

    @Override
    public String getAlgorithmName() {
        return "ConcurrentMergeSort";
    }

    @Override
    public List<K> sort(final List<K> input, final BiPredicate<K, K> isGreater) {
        final Future<List<K>> future = threadPool.submit(new SorterThread<>(input, isGreater, threadPool, 1));
        try {
            return future.get();
        } catch(final Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    private static class SorterThread<K> implements Callable<List<K>> {

        private final List<K> input;
        private final BiPredicate<K, K> isGreater;
        private final ExecutorService threadPool;
        private final long level;

        public SorterThread(final List<K> input, final BiPredicate<K, K> isGreater, final ExecutorService threadPool, final long level) {
            this.input = input;
            this.isGreater = isGreater;
            this.threadPool = threadPool;
            this.level = level;
        }

        @Override
        public List<K> call() {
            try {
        	        return sort(input, isGreater);
            } catch(final Exception ex) {
                throw new RuntimeException(ex);
            }
        }

        private List<K> sort(final List<K> input, final BiPredicate<K,K> isGreater) throws Exception {
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

            List<K> partition1 = null, partition2 = null;

            /**
             * Be careful here because we are spinning new threads from one thread recursively.
             * TODO: Use common forkJoin pool for this?
             */
            if (this.level <= 7) {
                final Future<List<K>> p1 = threadPool.submit(new SorterThread<>(partition(input, 0, mid), isGreater, threadPool, level+1));
                final Future<List<K>> p2 = threadPool.submit(new SorterThread<>(partition(input, mid+1, input.size() - 1), isGreater, threadPool, level+1));

                partition1 = p1.get();
                partition2 = p2.get();
            } else {
                // Do small calculation in the same thread
                partition1 = sort(partition(input, 0, mid), isGreater);
                partition2 = sort(partition(input, mid+1, input.size() - 1), isGreater);
            }

            try {
                return merge(partition1, partition2, isGreater);
            } catch(final Exception ex) {
                throw new RuntimeException(ex);
            }
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

}
