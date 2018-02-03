package com.mihsathe.algokit.algos.expression;

public class Driver {

    public static void main(final String[] args) {
        SimpleSolver solver = new SimpleSolver("2x + 5 - y + 2y = 2y + 1 + y - 20x");

        System.out.println(solver.yVal(2));
    }

}
