package com.game.math;

public class Expression {
    private final String   rawExpression;
    private String       exprParts;
    private final String[] operators = {"+", "-", "*", "/", "^", "sin", "cos", "tan"};
    // private final String[] extractedExpression;
    private char           variable;

    public Expression(String textString) {
        rawExpression = textString;
        exprParts = rawExpression.toLowerCase().replaceAll(" ", "");
        exprParts = extractExpression(textString);
        System.out.println(exprParts);
    }
    
    private String extractExpression(String expression) {
        int exprLength = exprParts.length();

        if (exprParts.charAt(1) == '=') {
            variable = exprParts.charAt(0);
            return exprParts.substring(2);
        }
        else if (exprParts.charAt(exprLength - 2) == '=') {
            variable = exprParts.charAt(exprLength - 1);
            return exprParts.substring(0, exprLength - 2);
        }
        else variable = 'y';
        return exprParts;
    }

    public double evaluate(double x) {
        String evalExpression = rawExpression.replace("x", String.valueOf(x));

        if (evalExpression.contains("*")) {
            String[] parts = evalExpression.split("\\*");
            return Double.parseDouble(parts[0].trim()) * Double.parseDouble(parts[1].trim());
        } else if (evalExpression.contains("+")) {
            String[] parts = evalExpression.split("\\+");
            return Double.parseDouble(parts[0].trim()) + Double.parseDouble(parts[1].trim());
        } else if (evalExpression.contains("-")) {
            String[] parts = evalExpression.split("-");
            return Double.parseDouble(parts[0].trim()) - Double.parseDouble(parts[1].trim());
        } else if (evalExpression.contains("/")) {
            String[] parts = evalExpression.split("/");
            return Double.parseDouble(parts[0].trim()) / Double.parseDouble(parts[1].trim());
        } else if (evalExpression.contains("^")) {
            String[] parts = evalExpression.split("\\^");
            return Math.pow(Double.parseDouble(parts[0].trim()), Double.parseDouble(parts[1].trim()));
        } else if (evalExpression.contains("sin")) {
            String angle = evalExpression.replace("sin", "").trim();
            return Math.sin(Math.toRadians(Double.parseDouble(angle)));
        } else if (evalExpression.contains("cos")) {
            String angle = evalExpression.replace("cos", "").trim();
            return Math.cos(Math.toRadians(Double.parseDouble(angle)));
        } else if (evalExpression.contains("tan")) {
            String angle = evalExpression.replace("tan", "").trim();
            return Math.tan(Math.toRadians(Double.parseDouble(angle)));
        }
        return Double.parseDouble(evalExpression);
    }
}