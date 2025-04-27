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
    private int                      graphXGrid = 5;
    private int                      graphYGrid = 5;
    private double                   graphXDividend;
    private double                   graphYDividend;
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

    public void drawGraph() {
        // Clear canvas
        graphicsContext.setFill(Color.WHITE);
        graphicsContext.fillRect(0, 0, this.getWidth(), this.getHeight());

        // Draw axes
        graphicsContext.setStroke(Color.BLACK);
        graphicsContext.setLineWidth(1);
        graphicsContext.strokeLine(graphXCenter, 0, graphXCenter, this.getHeight()); // Y-axis
        graphicsContext.strokeLine(0, graphYCenter, this.getWidth(), graphYCenter); // X-axis

        // Draw grids
        graphicsContext.setStroke(Color.GREY);
        graphicsContext.setLineWidth(0.5);

        for (int lineNo = 0; lineNo < graphXGrid; lineNo++) {
            graphicsContext.strokeLine(graphXCenter + (graphXDividend * lineNo), 0, graphXCenter + (graphXDividend * lineNo), this.getHeight());
        }
    }

    public void drawFunction(Function<Double, Double> function) {
        this.currentFunction = function;

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
}