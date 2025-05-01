package com.game.math;

import java.util.function.Function;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class GraphCanvas extends Canvas {
    private GraphicsContext          graphicsContext;
    private double                   graphXTotal;
    private double                   graphYTotal;
    private double                   graphXCenter;
    private double                   graphYCenter;
    private final float              graphGridWidth = 0.4f;
    private final float              graphSubGridWidth = 0.1f;
    private final int                graphXGrid = 20;
    private final int                graphYGrid = 20;
    private double                   graphXDividend;
    private double                   graphYDividend;

    private Expression               expressionEvaluator;
    private String                   currentExpression;
    private Function<Double, Double> currentFunction;

    public GraphCanvas(double width, double height) {
        super(width, height);

        updateParams();
        drawGraph();
    }

    private void updateParams() {
        graphicsContext = this.getGraphicsContext2D();
        graphXTotal = this.getWidth();
        graphYTotal = this.getHeight();
        graphXCenter = graphXTotal / 2;
        graphYCenter = graphYTotal / 2;
        graphXDividend = graphXCenter / graphXGrid;
        graphYDividend = graphYCenter / graphYGrid;
    }

    private void drawGraph() {
        // Clear canvas
        graphicsContext.setFill(Color.WHITE);
        graphicsContext.fillRect(0, 0, graphXTotal, graphYTotal);

        // Draw axes
        graphicsContext.setStroke(Color.BLACK);
        graphicsContext.setLineWidth(1);
        graphicsContext.strokeLine(graphXCenter, 0, graphXCenter, graphYTotal);
        graphicsContext.strokeLine(0, graphYCenter, graphXTotal, graphYCenter);

        // Draw grids
        graphicsContext.setStroke(Color.GREY);

        for (int lineNo = 1; lineNo <= graphXGrid; lineNo++) {
            graphicsContext.setLineWidth(lineNo % 10 == 0 ? graphGridWidth : graphSubGridWidth);
            graphicsContext.strokeLine(graphXCenter + (graphXDividend * lineNo), 0, graphXCenter + (graphXDividend * lineNo), graphYTotal);
            graphicsContext.strokeLine(graphXCenter - (graphXDividend * lineNo), 0, graphXCenter - (graphXDividend * lineNo), graphYTotal);
        }
        for (int lineNo = 1; lineNo <= graphYGrid; lineNo++) {
            graphicsContext.setLineWidth(lineNo % 10 == 0 ? graphGridWidth : graphSubGridWidth);
            graphicsContext.strokeLine(0, graphYCenter + (graphYDividend * lineNo), graphXTotal, graphYCenter + (graphYDividend * lineNo));
            graphicsContext.strokeLine(0, graphYCenter - (graphYDividend * lineNo), graphXTotal, graphYCenter - (graphYDividend * lineNo));
        }
    }

    public void drawFunction(String expression) {
        if (expression == null) return;
        
        drawGraph();
        currentExpression = expression;
        expressionEvaluator = new Expression(expression);
        currentFunction = expressionEvaluator::evaluate;

        // Draw function
        graphicsContext.setStroke(Color.BLUE);
        graphicsContext.setLineWidth(2);

        double scaleX = 50; // Pixels per unit
        double scaleY = 50; // Pixels per unit

        double prevX = -graphXCenter / scaleX;
        double prevY = currentFunction.apply(prevX);

        for (double x = -graphXCenter / scaleX; x <= graphXCenter / scaleX; x += 0.1) {
            double y = currentFunction.apply(x);
            // Check for discontinuities
            if (Math.abs(y) > 100)
                continue;

            graphicsContext.strokeLine(
                graphXCenter + prevX * scaleX,
                graphYCenter - prevY * scaleY,
                graphXCenter + x * scaleX,
                graphYCenter - y * scaleY
            );
            prevX = x;
            prevY = y;
        }
    }

    public void updateGraph() {
        updateParams();
        if (currentExpression != null) drawFunction(currentExpression);
        else drawGraph();
    }
}