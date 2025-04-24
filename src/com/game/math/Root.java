package com.game.math;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class Root extends VBox {
    private GraphCanvas graphCanvas;

    public Root(double spacing) {
        super(spacing);
        setupUI();
    }

    public void setupUI() {
        // Title Label
        Label titleLabel = new Label("Math Graph Plotter");
        titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        // Input Field and Submit Button
        TextField eqnField = new TextField("Enter x expression (e.g., x * x)");
        eqnField.setPromptText("Enter a mathematical expression");
        HBox.setHgrow(eqnField, Priority.ALWAYS);
        Button eqnSubmitBtn = new Button("Plot Custom");

        // Layout for Input and Buttons
        HBox eqnLayout = new HBox(10, eqnField, eqnSubmitBtn);
        VBox.setMargin(eqnLayout, new Insets(10));

        // Graph Canvas
        graphCanvas = new GraphCanvas(800, 500);

        // Event Handlers
        eqnSubmitBtn.setOnAction(e -> {
            try {
                String expression = eqnField.getText();
                ExpressionEvaluator evaluator = new ExpressionEvaluator(expression);
                graphCanvas.setFunction(evaluator::evaluate);
                graphCanvas.drawGraph();
            } catch (Exception ex) {
                System.out.println("Invalid expression: " + ex.getMessage());
            }
        });

        // Add all components to the VBox
        this.getChildren().addAll(titleLabel, eqnLayout, graphCanvas.getCanvas());
        this.setPadding(new Insets(15));
        this.setSpacing(10);
    }
}
