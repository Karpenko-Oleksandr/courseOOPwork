package org.javaee.courseoopwork;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class HelloController {

    @FXML
    private TextField inputPoly1;

    @FXML
    private TextField inputPoly2;

    @FXML
    private TextField inputX;

    @FXML
    private TextArea resultArea;

    @FXML
    protected void onCalculateClick() {
        try {
            // Парсимо коефіцієнти
            double[] coeffs1 = parseCoefficients(inputPoly1.getText());
            double[] coeffs2 = parseCoefficients(inputPoly2.getText());
            double xValue = Double.parseDouble(inputX.getText());

            PolynomialCalculator p1 = new PolynomialCalculator(coeffs1.length - 1, coeffs1);
            PolynomialCalculator p2 = new PolynomialCalculator(coeffs2.length - 1, coeffs2);

            PolynomialCalculator sum = p1.add(p2);
            PolynomialCalculator diff = p1.subtract(p2);
            PolynomialCalculator product = p1.multiply(p2);
            double valueAtX = p1.evaluate(xValue);

            String result = "P1: " + p1 + "\n" +
                    "P2: " + p2 + "\n\n" +
                    "P1 + P2: " + sum + "\n" +
                    "P1 - P2: " + diff + "\n" +
                    "P1 * P2: " + product + "\n\n" +
                    "P1(" + xValue + ") = " + valueAtX;

            resultArea.setText(result);

        } catch (Exception e) {
            resultArea.setText("Error: " + e.getMessage());
        }
    }

    @FXML
    protected void onClearClick() {
        inputPoly1.clear();
        inputPoly2.clear();
        inputX.clear();
        resultArea.clear();
    }

    private double[] parseCoefficients(String text) throws NumberFormatException {
        String[] parts = text.trim().split(",");
        double[] result = new double[parts.length];
        for (int i = 0; i < parts.length; i++) {
            result[i] = Double.parseDouble(parts[i].trim());
        }
        return result;
    }
}
