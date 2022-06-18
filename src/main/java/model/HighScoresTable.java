package model;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import view.MenuView;

public class HighScoresTable extends Application {

    private TableView table = new TableView();

    @Override
    public void start(Stage stage) {
        Scene scene = new Scene(new Group());
        stage.setTitle("High Scores Table");

        Label label = new Label("High Scores");
        label.setFont(new Font("Tahoma", 20));

        table.setEditable(false);

        TableColumn playerCol = new TableColumn("PLAYER");
        TableColumn scoreCol = new TableColumn("SCORE");
        table.getColumns().addAll(playerCol, scoreCol);

        playerCol.setPrefWidth(200);
        scoreCol.setPrefWidth(100);

        VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, table);

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(vbox);
        ((Group) scene.getRoot()).getChildren().add(scrollPane);

        double columnsWidth = playerCol.getWidth() + scoreCol.getWidth();
        stage.setWidth(columnsWidth + 40);
        stage.setHeight(490);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();

        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                MenuView.mainStage.show();
            }
        });
    }

}
