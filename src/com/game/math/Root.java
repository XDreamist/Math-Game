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
    private Button                    exprSubmitBtn;
    private TextField                 exprField;
    private HBox                      exprLayout;

    private GraphCanvas               graphCanvas;

    public Root() {
        super();
        setupUI();
        setupConnections();
    }

    private void setupUI() {
        // Title Label
        titleLabel = new Label("Graph Plotter");
        titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        // Input Field and Submit Button
        exprField = new TextField("Enter x expression (e.g., x * x)");
        exprField.setPromptText("Enter a mathematical expression");
        HBox.setHgrow(exprField, Priority.ALWAYS);
        exprSubmitBtn = new Button("Plot");

        // Layout for Input and Buttons
        exprLayout = new HBox(10, exprField, exprSubmitBtn);
        VBox.setMargin(exprLayout, new Insets(10));

        // Graph Canvas
        graphCanvas = new GraphCanvas(500, 500);

        // Add all components to the VBox
        this.getChildren().addAll(titleLabel, exprLayout, graphCanvas);
        this.setPadding(new Insets(15));
        this.setAlignment(javafx.geometry.Pos.CENTER);
        this.setSpacing(10);
    }

    private void setupConnections() {
        // Make the graph canvas responsive
        // graphCanvas.widthProperty().addListener((obs, oldVal, newVal) -> {
        //     graphCanvas.setWidth(newVal.doubleValue());
        //     graphCanvas.updateGraph();
        // });

        exprField.setOnAction(event -> {
            try {
                graphCanvas.drawFunction(exprField.getText());
            } catch (Exception exception) {
                System.out.println("Invalid expression: " + exception.getMessage());
            }
        });

        exprSubmitBtn.setOnAction(event -> {
            try {
                graphCanvas.drawFunction(exprField.getText());
            } catch (Exception exception) {
                System.out.println("Invalid expression: " + exception.getMessage());
            }
        });
    }
}
