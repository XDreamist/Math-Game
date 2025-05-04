package com.game.math;

public class Evaluator {
    private final String   rawExpr;
    public String          mainVar;
    public String          mainExpr;
    private final String[] operators = {"+", "-", "*", "/", "^", "sin", "cos", "tan"};
    // private final String[] extractedExpression;

    public Evaluator(String expression) {
        this.rawExpr = expression;
        String[] extractedExpr = extractExpression(rawExpr);
        mainVar = extractedExpr[0];
        mainExpr = extractedExpr[1];
        System.out.println("Eqn of " + mainVar + " is: " + mainExpr);
    }
    
    private String[] extractExpression(String expression) {
        String exprToExtract = expression.toLowerCase().replaceAll(" ", "");
        int exprLength = exprToExtract.length();
        String[] extractedParts = new String[2];

        if (exprToExtract.charAt(1) == '=') {
            extractedParts[0] = exprToExtract.substring(0, 1);
            extractedParts[1] = exprToExtract.substring(2);
        }
        else if (exprToExtract.charAt(exprLength - 2) == '=') {
            extractedParts[0] = exprToExtract.substring(exprLength - 1);
            extractedParts[1] = exprToExtract.substring(0, exprLength - 2);
        }
        else {
            extractedParts[0] = "y";
            extractedParts[1] = exprToExtract;
        }

        return extractedParts;
    }

    public double evaluate(double variable) {
        String evalExpression = mainExpr.replace("y".equals(mainVar) ? "x" : "y", String.valueOf(variable));

        Expression expr = new Expression(mainExpr);

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