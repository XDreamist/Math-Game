package com.game.math;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class Root extends VBox {
    private GraphCanvas graphCanvas;

    public Root(double spacing) {
        super(spacing);

        setupUI(); // Replace or find good implementation design
    }

    public void setupUI() {
        graphCanvas = new GraphCanvas(800, 500);

        Button sinButton = new Button("Sine");
        Button cosButton = new Button("Cosine");
        Button tanButton = new Button("Tangent");
        TextField customField = new TextField("Enter x expression (e.g., x * x)");
        Button customButton = new Button("Plot Custom");

        // Event handlers
        sinButton.setOnAction(e -> {
            graphCanvas.setFunction(Math::sin);
            graphCanvas.drawGraph();
        });

        cosButton.setOnAction(e -> {
            graphCanvas.setFunction(Math::cos);
            graphCanvas.drawGraph();
        });

        tanButton.setOnAction(e -> {
            graphCanvas.setFunction(Math::tan);
            graphCanvas.drawGraph();
        });

        customButton.setOnAction(e -> {
            try {
                String expression = customField.getText();
                ExpressionEvaluator evaluator = new ExpressionEvaluator(expression);
                graphCanvas.setFunction(evaluator::evaluate);
                graphCanvas.drawGraph();
            } catch (Exception ex) {
                System.out.println("Invalid expression: " + ex.getMessage());
            }
        });

        this.getChildren().addAll(graphCanvas.getCanvas(), sinButton, cosButton, tanButton, customField, customButton);
    }
}
