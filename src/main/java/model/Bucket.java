package model;

import javafx.scene.control.Button;

public class Bucket extends Button {

    private static final int HEIGHT = 50;
    private static final int WIDTH = 50;

    public Bucket() {
        this.getStylesheets().add("style.css");
        setPrefHeight(HEIGHT);
        setPrefWidth(WIDTH);
    }

    public static int getHEIGHT() {
        return HEIGHT;
    }

    public static int getWIDTH() {
        return WIDTH;
    }
}
