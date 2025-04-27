package com.game.math;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class Root extends VBox {
    private Label                     titleLabel;
    private Button                    eqnSubmitBtn;
    private TextField                 eqnField;
    private HBox                      eqnLayout;

    private GraphCanvas               graphCanvas;
    private String                    expression;
    private ExpressionEvaluator       expressionEvaluator;

    public Root() {
        super();
        setupUI();
        setupConnections();
    }

    public void setupUI() {
        // Title Label
        titleLabel = new Label("Graph Plotter");
        titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        // Input Field and Submit Button
        eqnField = new TextField("Enter x expression (e.g., x * x)");
        eqnField.setPromptText("Enter a mathematical expression");
        HBox.setHgrow(eqnField, Priority.ALWAYS);
        eqnSubmitBtn = new Button("Plot");

        // Layout for Input and Buttons
        eqnLayout = new HBox(10, eqnField, eqnSubmitBtn);
        VBox.setMargin(eqnLayout, new Insets(10));

        // Graph Canvas
        graphCanvas = new GraphCanvas(800, 500);

        // Add all components to the VBox
        this.getChildren().addAll(titleLabel, eqnLayout, graphCanvas);
        this.setPadding(new Insets(15));
        this.setSpacing(10);
    }

    public void setupConnections() {
        eqnSubmitBtn.setOnAction(e -> {
            try {
                expression = eqnField.getText();
                expressionEvaluator = new ExpressionEvaluator(expression);
                graphCanvas.drawFunction(expressionEvaluator::evaluate);
            } catch (Exception exception) {
                System.out.println("Invalid expression: " + exception.getMessage());
            }
        });
    }
}
