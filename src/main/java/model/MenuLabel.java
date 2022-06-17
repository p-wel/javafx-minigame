package model;

import javafx.scene.control.Label;
import javafx.scene.text.Font;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class MenuLabel extends Label {

    private final String FONT_PATH = "src/main/resources/kenvector_future.ttf";

    public MenuLabel(String text) {
        setText(text);
        setLabelFont();
        setPrefWidth(200);
        setPrefHeight(25);
    }

    private void setLabelFont() {
        try {
            setFont(Font.loadFont(new FileInputStream(FONT_PATH), 14));
        } catch (FileNotFoundException e) {
            setFont(Font.font("Verdana", 14));
        }
    }
}
