package com.game.math;

import java.util.ArrayList;
import java.util.List;

public class Expression {
    private final String mainExpression;
    private List<Expression> subExpressions = new ArrayList<>();
    private List<String>     operations = new ArrayList<>();

    public Expression(String expressionString) {
        mainExpression = expressionString;

        seperateExpressions(mainExpression);
    }

    private void seperateExpressions(String expression) {
        // Before moving onto +,-,sin,cos,tan,*,/ we need to do (,),[,],{,}, etc..

        String[] exprParts;
        if (expression.contains("+")) {
            exprParts = expression.split("\\+");
            System.out.print("Addition: ");
            for (String exprPart : exprParts) {
                subExpressions.addLast(new Expression(exprPart));
                System.out.print(exprPart + ", ");
            }
            System.out.println();
            operations.addLast("+");
        }
    }
}
