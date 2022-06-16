package com.example.project_3;

import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class MyController {

    Button myButton;

    public void myButtonClick(MouseEvent mouseEvent) {
        myButton.setText("Hello!");
    }

}