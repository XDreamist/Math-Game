package com.game.math;

public class ExpressionEvaluator {
    private final String expression;

    public ExpressionEvaluator(String expression) {
        this.expression = expression.toLowerCase();
    }

    public double evaluate(double x) {
        String evalExpression = expression.replace("x", String.valueOf(x));
        // Basic expression evaluator
        if (evalExpression.contains("*")) {
            String[] parts = evalExpression.split("\\*");
            return Double.parseDouble(parts[0].trim()) * Double.parseDouble(parts[1].trim());
        } else if (evalExpression.contains("+")) {
            String[] parts = evalExpression.split("\\+");
            return Double.parseDouble(parts[0].trim()) + Double.parseDouble(parts[1].trim());
        } else if (evalExpression.contains("-")) {
            String[] parts = evalExpression.split("-");
            return Double.parseDouble(parts[0].trim()) - Double.parseDouble(parts[1].trim());
        }
        return Double.parseDouble(evalExpression);
    }
}