package com.game.math;

public class ExpressionEvaluator {
    private final String expression;

    public ExpressionEvaluator(String expression) {
        this.expression = expression.toLowerCase();
    }

    public double evaluate(double x) {
        String evalExpression = expression.replace("x", String.valueOf(x));

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