package app;

import javafx.application.Application;
import javafx.stage.Stage;
import view.MenuView;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        try {
            MenuView menuView = new MenuView();
            stage = menuView.getMainStage();
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
