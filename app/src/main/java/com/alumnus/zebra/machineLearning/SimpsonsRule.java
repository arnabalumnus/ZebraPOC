package com.alumnus.zebra.machineLearning;

import java.util.ArrayList;

public class SimpsonsRule {

    /**********************************************************************
     * Standard normal distribution density function.
     * Replace with any sufficiently smooth function.
     **********************************************************************/
    public static double f(double x) {
        return Math.exp(-x * x / 2) / Math.sqrt(2 * Math.PI);
    }

    /**********************************************************************
     * Integrate f from a to b using Simpson's rule.
     * Increase N for more precision.
     **********************************************************************/
    public static double integrate(ArrayList<Double> tsvDataset, int impactStart, int impactEnd, int step_size) {
        int N = impactEnd - impactStart;                    // precision parameter

        // 1/3 terms
        double sum = 1.0 / 3.0 * (tsvDataset.get(impactStart) + tsvDataset.get(impactEnd));

        // 4/3 terms
        for (int i = 1; i < N - 1; i += 2) {
            int x = impactStart + step_size * i;
            sum += 4.0 / 3.0 * tsvDataset.get(x);
        }

        // 2/3 terms
        for (int i = 2; i < N - 1; i += 2) {
            int x = impactStart + step_size * i;
            sum += 2.0 / 3.0 * tsvDataset.get(x);
        }

        return sum * step_size;
    }
}