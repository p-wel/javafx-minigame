package model;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;
import view.MenuView;

public class GameOver {

    public GameOver(Stage gameStage, int points) {
        TextInputDialog inputDialog = new TextInputDialog();
        inputDialog.setTitle("Game Over!");
        inputDialog.setHeaderText("Enter you nickname");
        inputDialog.setResizable(false);
        inputDialog.show();
        gameStage.close();

        inputDialog.getEditor().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String playerName = inputDialog.getEditor().getText();
                new HighScore(playerName, points);
                MenuView.mainStage.show();
                inputDialog.close();
            }
        });
    }
}
