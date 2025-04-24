package com.game.math;

import java.util.function.Function;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class GraphCanvas {
    private final Canvas canvas;
    private Function<Double, Double> currentFunction;
    private final double width;
    private final double height;

    public GraphCanvas(double width, double height) {
        this.width = width;
        this.height = height;
        this.canvas = new Canvas(width, height);
        this.currentFunction = Math::sin;

        drawGraph();
    }

    public void setFunction(Function<Double, Double> function) {
        this.currentFunction = function;
    }

    public void drawGraph() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        
        // Clear canvas
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, width, height);

        // Draw axes
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(1);
        double xCenter = width / 2;
        double yCenter = height / 2;
        gc.strokeLine(xCenter, 0, xCenter, height); // Y-axis
        gc.strokeLine(0, yCenter, width, yCenter);   // X-axis

        // Draw function
        gc.setStroke(Color.BLUE);
        gc.setLineWidth(2);
        
        double scaleX = 50;  // Pixels per unit
        double scaleY = 50;  // Pixels per unit
        
        double prevX = -xCenter / scaleX;
        double prevY = currentFunction.apply(prevX);
        
        for (double x = -xCenter / scaleX; x <= xCenter / scaleX; x += 0.1) {
            double y = currentFunction.apply(x);
            // Check for discontinuities
            if (Math.abs(y) > 100) continue;
            
            gc.strokeLine(
                xCenter + prevX * scaleX,
                yCenter - prevY * scaleY,
                xCenter + x * scaleX,
                yCenter - y * scaleY
            );
            prevX = x;
            prevY = y;
        }
    }

    public Canvas getCanvas() {
        return canvas;
    }
}