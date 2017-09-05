package com.mihsathe.algokit.main;

import java.util.Arrays;

import com.mihsathe.algokit.algos.sort.BinaryInsertionSort;
import com.mihsathe.algokit.algos.sort.BubbleSort;
import com.mihsathe.algokit.algos.sort.ConcurrentMergeSort;
import com.mihsathe.algokit.algos.sort.InsertionSort;
import com.mihsathe.algokit.algos.sort.JavaSort;
import com.mihsathe.algokit.algos.sort.MergeSort;
import com.mihsathe.algokit.algos.sort.QuickSort;
import com.mihsathe.algokit.algos.sort.SelectionSort;
import com.mihsathe.algokit.algos.sort.Sorter;
import com.mihsathe.algokit.algos.test.SortingTests;

public class Main {

    public static void main(final String[] args) {
        final Sorter<Double> bubbleSorter = new BubbleSort<>();
        final Sorter<Double> selectionSorter = new SelectionSort<>();
        final Sorter<Double> insertionSorter = new InsertionSort<>();
        final Sorter<Double> binaryInsertionSorter = new BinaryInsertionSort<>();
        final Sorter<Double> mergeSorter = new MergeSort<>();
        final Sorter<Double> concurrentMergeSorter = new ConcurrentMergeSort<>();
        final Sorter<Double> quickSorter = new QuickSort<>();
        final Sorter<Double> javaSorter = new JavaSort<>();

        final SortingTests smallTests = new SortingTests(Arrays.asList(bubbleSorter,
                selectionSorter,
                insertionSorter,
                binaryInsertionSorter,
                mergeSorter,
                concurrentMergeSorter,
                quickSorter,
                javaSorter), 20000);

        smallTests.runTests();

        final SortingTests mediumTests = new SortingTests(Arrays.asList(binaryInsertionSorter,
        		    mergeSorter,
        		    concurrentMergeSorter,
        		    quickSorter,
        		    javaSorter), 200000);

        mediumTests.runTests();

        final SortingTests bigTests = new SortingTests(Arrays.asList(mergeSorter,
        	        concurrentMergeSorter,
        	        quickSorter,
        	        javaSorter), 10000000);

        bigTests.runTests();

    }

}
