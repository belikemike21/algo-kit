package com.mihsathe.algokit.algos.test;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import com.mihsathe.algokit.algos.sort.Sorter;

public class SortingTests {

    private final Integer SAMPLE_SIZE;

    private final List<Sorter<Double>> sorters;
    private List<Double> inputRandom;
    private List<Double> inputPre;
    private List<Double> inputAnti;

    public SortingTests(final List<Sorter<Double>> sorters, final Integer sampleSize) {
        this.sorters = sorters;
        this.SAMPLE_SIZE = sampleSize;
    }

    private void refreshInput() {
        inputRandom = new ArrayList<>();
        inputPre = new ArrayList<>();
        inputAnti = new ArrayList<>();
        for(int i=0; i<SAMPLE_SIZE; i++) inputRandom.add(new Double(Math.random() * 100));
        for(int i=0; i<SAMPLE_SIZE; i++) inputPre.add(new Double(i));
        for(int i=SAMPLE_SIZE-1; i>=0; i--) inputAnti.add(new Double(i));
    }

    public void runTests() {
        for(final Sorter<Double> sorter: this.sorters) {
            System.out.println(String.format("Testing algorithm %s", sorter.getAlgorithmName()));

            testRandomNumbers(sorter);
            testPreSortedNumbers(sorter);
            testAntiSortedNumbers(sorter);
        }
    }

    private void verify(final List<Double> list) {
        if(list.size() != SAMPLE_SIZE) throw new IllegalArgumentException();

        for(int i=1; i<list.size(); i++) {
            if(list.get(i) < list.get(i-1)) throw new IllegalArgumentException();
        }
    }

    private void testRandomNumbers(final Sorter<Double> sorter) {
        refreshInput();

        final long startTime = Instant.now().toEpochMilli();
        final List<Double> output = sorter.sort(inputRandom, (a, b) -> a > b);
        final long endTime = Instant.now().toEpochMilli();

        verify(output);
        System.out.println(String.format("Random Time: %ss", (double)(endTime - startTime) / 1000));
    }

    private void testPreSortedNumbers(final Sorter<Double> sorter) {
        refreshInput();

    	    final long startTime = Instant.now().toEpochMilli();
        final List<Double> output = sorter.sort(inputPre, (a, b) -> a > b);
        final long endTime = Instant.now().toEpochMilli();

        verify(output);
        System.out.println(String.format("Pre-sorted Time: %ss", (double)(endTime - startTime) / 1000));
    }

    private void testAntiSortedNumbers(final Sorter<Double> sorter) {
        refreshInput();

        final long startTime = Instant.now().toEpochMilli();
        final List<Double> output = sorter.sort(inputAnti, (a, b) -> a > b);
        final long endTime = Instant.now().toEpochMilli();

        verify(output);
        System.out.println(String.format("Anti-sorted Time: %ss", (double)(endTime - startTime) / 1000));
    }

}