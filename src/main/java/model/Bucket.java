package model;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Bucket extends Button {

    private final String BUTTON_PRESSED_STYLE = "-fx-background-color: transparent; -fx-background-image: url('bucket.png')";

    public Bucket() {
        setPrefWidth(74);
        setPrefHeight(74);
        setStyle(BUTTON_PRESSED_STYLE);
    }

}
