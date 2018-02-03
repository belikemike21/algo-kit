package com.mihsathe.algokit.algos.expression;

import java.util.LinkedList;
import java.util.List;

public class SimpleSolver {

    private final String expression;

    public SimpleSolver(final String expression) {
        this.expression = expression;
    }

    public double yVal(final int xVal) {
        final String[] parts = expression.split(" ");

        // Classify parts on left and right
        final List<Part> constParts = new LinkedList<>();
        final List<Part> yParts = new LinkedList<>();

        boolean stillLeft = true;

        int mult = 1;


        for (final String part : parts) {
            if (part.equals("=")) {
                stillLeft = false;
                continue;
            }

            if (part.equals("+") || part.equals("-")) {
                mult = part.equals("+") ? 1 : -1;
                continue;
            }

            int realMult = stillLeft ? mult : -1;

            Part temp = toPart(xVal, part, realMult);
            if (temp.isYPart) {
                yParts.add(temp);
            } else {
                constParts.add(temp);
            }
        }

        // Compact both sides
        int constantPart = constParts.stream()
                .map(x -> x.constant)
                .reduce((x,y)-> x+y)
                .get();

        int yCoeff = yParts.stream()
                .map(x -> x.yCoeff)
                .reduce((x,y)-> x+y)
                .get();

        System.out.println(constParts);
        System.out.println(yParts);

        return (double) constantPart / yCoeff;
    }

    private Part toPart(final int xVal, final String strPart, final int mult) {
        if(strPart.contains("x")) {
            return new Part(false, null, coeff(strPart) * xVal * mult);
        } else if(strPart.contains("y")) {
            return new Part(true, coeff(strPart) * -mult, null);
        }

        return new Part(false, null, Integer.parseInt(strPart) * mult);
    }

    private int coeff(final String strPart) {
        final String coeff = strPart.substring(0, strPart.length()-1);

        int intCoeff = 1;
        if(!coeff.equals("")) {
            intCoeff = Integer.parseInt(coeff);
        }

        return intCoeff;
    }

    /**
     * Can be yVal or a constant.
     */
    private static class Part {
        final boolean isYPart;
        final Integer yCoeff;
        final Integer constant;

        Part(boolean isYPart, Integer yCoeff, Integer constant) {
            this.isYPart = isYPart;
            this.yCoeff = yCoeff;
            this.constant = constant;
        }

        @Override
        public String toString() {
            return "Part{" +
                    "isYPart=" + isYPart +
                    ", yCoeff=" + yCoeff +
                    ", constant=" + constant +
                    '}';
        }
    }

}
