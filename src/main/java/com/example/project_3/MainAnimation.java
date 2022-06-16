package com.example.project_3;

import javafx.animation.*;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MainAnimation extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        Group group = new Group();

        Rectangle rect = new Rectangle(50, 100, 200, 100);

        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1), rect);
        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0.2);
        fadeTransition.setAutoReverse(true);

        FillTransition fillTransition =new FillTransition(Duration.seconds(1), rect, Color.YELLOW, Color.RED);
        fillTransition.setAutoReverse(true);

        ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(1), rect);
        scaleTransition.setToX(0.1);
        scaleTransition.setToY(0.2);
        scaleTransition.setAutoReverse(true);

        RotateTransition rotateTransition = new RotateTransition(Duration.seconds(1), rect);
        rotateTransition.setFromAngle(0);
        rotateTransition.setToAngle(170);
        rotateTransition.setAutoReverse(true);

        PathTransition pathTransition = new PathTransition(Duration.seconds(2), rect);

        Path path = new Path();
        path.getElements().add(new MoveTo(0,0));
        path.getElements().add(new LineTo(300, 300));

        pathTransition.setNode(rect);
        pathTransition.setPath(path);

        pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        pathTransition.setAutoReverse(true);

//        SequentialTransition sequentialTransition = new SequentialTransition();
//        sequentialTransition.getChildren().addAll(fadeTransition, fillTransition, scaleTransition, rotateTransition, pathTransition);
//        sequentialTransition.setCycleCount(Animation.INDEFINITE);
//        sequentialTransition.setAutoReverse(true);
//        sequentialTransition.play();

        ParallelTransition parallelTransition = new ParallelTransition();
        parallelTransition.getChildren().addAll(fadeTransition, fillTransition, scaleTransition, rotateTransition, pathTransition);
        parallelTransition.setCycleCount(Animation.INDEFINITE);
        parallelTransition.setAutoReverse(true);
        parallelTransition.play();

        group.getChildren().add(rect);
        Scene scene = new Scene(group, 300, 300);
        stage.setScene(scene);
        stage.show();


    }

    public static void main(String[] args) {
        launch(args);
    }
}
