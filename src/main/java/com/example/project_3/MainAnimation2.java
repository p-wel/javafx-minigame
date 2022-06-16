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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MainAnimation2 extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        Group group = new Group();

        String content = "Wpisuję tekst";
        Text text = new Text(100, 100, "");

        Animation animation = new Transition() {
            {
                setCycleDuration(Duration.seconds(3));
            }

            @Override
            protected void interpolate(double v) {
                text.setText(content.substring(0, Math.round(content.length() * (float) v)));
            }
        };

        animation.play();
        group.getChildren().add(text);
        Scene scene = new Scene(group, 300, 300);
        stage.setScene(scene);
        stage.show();


    }

    public static void main(String[] args) {
        launch(args);
    }
}
