package view;

import javafx.scene.control.Label;
import javafx.scene.text.Font;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class GameLabel extends Label {

    public GameLabel(String text) {
        this.getStylesheets().add("style.css");
        setText(text);
        setPrefWidth(80);
        setPrefHeight(30);
    }

}
