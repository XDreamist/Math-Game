package com.game.math;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GraphVisualizerApp extends Application {
    private static final int            APP_WIDTH  = 800;
    private static final int            APP_HEIGHT = 700;
    private static final String         APP_TITLE  = "Graph Visualizer";


    @Override
    public void start(Stage primaryStage) {
        final Root root = new Root();
        
        Scene scene = new Scene(root, APP_WIDTH, APP_HEIGHT);
        primaryStage.setTitle(APP_TITLE);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}