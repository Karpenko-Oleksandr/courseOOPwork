package org.javaee.courseoopwork;

import java.util.Arrays;

public class PolynomialCalculator {
    private int degree;
    private double[] coefficients;

    public PolynomialCalculator(int degree, double[] coefficients) {
        this.degree = degree;
        this.coefficients = Arrays.copyOf(coefficients, coefficients.length);
    }

    public double evaluate(double x) {
        double result = 0;
        for (int i = 0; i <= degree; i++) {
            result += coefficients[i] * Math.pow(x, i);
        }
        return result;
    }

    public PolynomialCalculator add(PolynomialCalculator other) {
        int maxDegree = Math.max(this.degree, other.degree);
        double[] newCoefficients = new double[maxDegree + 1];

        for (int i = 0; i <= maxDegree; i++) {
            double a = i <= this.degree ? this.coefficients[i] : 0;
            double b = i <= other.degree ? other.coefficients[i] : 0;
            newCoefficients[i] = a + b;
        }

        return new PolynomialCalculator(maxDegree, newCoefficients);
    }

    public PolynomialCalculator subtract(PolynomialCalculator other) {
        int maxDegree = Math.max(this.degree, other.degree);
        double[] newCoefficients = new double[maxDegree + 1];

        for (int i = 0; i <= maxDegree; i++) {
            double a = i <= this.degree ? this.coefficients[i] : 0;
            double b = i <= other.degree ? other.coefficients[i] : 0;
            newCoefficients[i] = a - b;
        }

        return new PolynomialCalculator(maxDegree, newCoefficients);
    }

    public PolynomialCalculator multiply(PolynomialCalculator other) {
        int newDegree = this.degree + other.degree;
        double[] newCoefficients = new double[newDegree + 1];

        for (int i = 0; i <= this.degree; i++) {
            for (int j = 0; j <= other.degree; j++) {
                newCoefficients[i + j] += this.coefficients[i] * other.coefficients[j];
            }
        }

        return new PolynomialCalculator(newDegree, newCoefficients);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int i = degree; i >= 0; i--) {
            double coeff = coefficients[i];
            if (coeff == 0) continue;

            if (sb.length() > 0) {
                sb.append(coeff > 0 ? " + " : " - ");
            } else if (coeff < 0) {
                sb.append("-");
            }

            double absCoeff = Math.abs(coeff);
            if (i == 0) {
                sb.append(absCoeff);
            } else if (i == 1) {
                if (absCoeff != 1) sb.append(absCoeff);
                sb.append("x");
            } else {
                if (absCoeff != 1) sb.append(absCoeff);
                sb.append("x^").append(i);
            }
        }

        return sb.length() == 0 ? "0" : sb.toString();
    }

    // Optional: getters if needed
    public int getDegree() {
        return degree;
    }

    public double[] getCoefficients() {
        return Arrays.copyOf(coefficients, coefficients.length);
    }
}
