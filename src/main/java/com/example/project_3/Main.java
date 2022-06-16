package com.example.project_3;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
//        StackPane root = new StackPane();
//        Label label = new Label("Hello World!");
//        root.getChildren().add(label);
//        Scene scene = new Scene(root, 250, 50);
//        stage.setTitle("My Title");
//        stage.setScene(scene);
//        stage.show();

        GridPane gridPane = new GridPane();

        Label label = new Label("JavaFX");
        Button button = new Button("Button");
        TextArea textArea = new TextArea("TextArea");

        gridPane.add(label, 0, 0);
        gridPane.add(button, 0, 1);
        gridPane.add(textArea, 0, 2);

        Scene scene = new Scene(gridPane, 300, 300);
        scene.getStylesheets().add("style.css");

        stage.setTitle("Title");
        stage.setScene(scene);
        stage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
