package view;

import javafx.animation.Animation;
import javafx.animation.Transition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.MenuButton;
import model.MenuLabel;
import model.SubMenu;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class ViewManager {

    public static final int FRAME_WIDTH = 1000;
    public static final int FRAME_HEIGHT = 600;
    private AnchorPane mainPane;
    private Scene mainScene;
    private Stage mainStage;

    private final static int MENU_BUTTONS_START_X = 405;
    private final static int MENU_BUTTONS_START_Y = 225;
    private final String FONT_PATH = "src/main/resources/kenvector_future.ttf";

    private SubMenu playSubMenu;
    private SubMenu highScoresSubMenu;
    private SubMenu exitSubMenu;

    private SubMenu subMenuToHide = new SubMenu();

    List<MenuButton> menuButtons;

    public ViewManager() {
        menuButtons = new ArrayList<>();
        mainPane = new AnchorPane();
        mainScene = new Scene(mainPane, FRAME_WIDTH, FRAME_HEIGHT);
        mainStage = new Stage();
        mainStage.setScene(mainScene);

        createSubMenus();
        createButtons();
        createBackground();
        createLogo();

    }

    private void showSubMenu(SubMenu subMenu) {
        if (subMenu != null) {
            subMenuToHide.moveSubMenu();
            subMenu.moveSubMenu();
            subMenuToHide = subMenu;
        }
    }

    private void createSubMenus() {
        highScoresSubMenu = new SubMenu();
        exitSubMenu = new SubMenu();

        mainPane.getChildren().add(highScoresSubMenu);
        mainPane.getChildren().add(exitSubMenu);

        createPlaySubMenu();

    }

    private void createPlaySubMenu() {
        playSubMenu = new SubMenu(300);
        mainPane.getChildren().add(playSubMenu);

        MenuLabel labelDifficulty = new MenuLabel("Choose difficulty");
        labelDifficulty.setLayoutX(playSubMenu.getWidth() / 2 - labelDifficulty.getPrefWidth()/2 +15);
        labelDifficulty.setLayoutY(5);
        playSubMenu.getPane().getChildren().add(labelDifficulty);
        playSubMenu.getPane().getChildren().add(createEasyButton());
        playSubMenu.getPane().getChildren().add(createMediumButton());
        playSubMenu.getPane().getChildren().add(createHardButton());

    }


    public Stage getMainStage() {
        return mainStage;
    }

    private void addMenuButton(MenuButton button) {
        button.setLayoutX(MENU_BUTTONS_START_X);
        button.setLayoutY(MENU_BUTTONS_START_Y + menuButtons.size() * 50);
        menuButtons.add(button);
        mainPane.getChildren().add(button);
    }

    private void createButtons() {
        createPlayButton();
        createHighScoresButton();
        createExitButton();
    }

    private void createPlayButton() {
        MenuButton playButton = new MenuButton("PLAY");
        addMenuButton(playButton);

        playButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                showSubMenu(playSubMenu);
            }
        });
    }

    private void createHighScoresButton() {
        MenuButton highScoresButton = new MenuButton("HIGH SCORES");
        addMenuButton(highScoresButton);

        highScoresButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                showSubMenu(highScoresSubMenu);
            }
        });
    }

    private void createExitButton() {
        MenuButton exitButton = new MenuButton("EXIT");
        addMenuButton(exitButton);

        exitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
//                showSubMenu(exitSubMenu);
                mainStage.close();
            }
        });
    }

    private MenuButton createEasyButton() {
        MenuButton easyButton = new MenuButton("EASY");
        easyButton.setLayoutX(60);
        easyButton.setLayoutY(25);

        easyButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                GameViewManager gameManager = new GameViewManager();
                gameManager.createNewGame(mainStage, 1);
            }
        });

        return easyButton;
    }

    private MenuButton createMediumButton() {
        MenuButton mediumButton = new MenuButton("MEDIUM");
        mediumButton.setLayoutX(60);
        mediumButton.setLayoutY(25+mediumButton.getPrefHeight());

        mediumButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                GameViewManager gameManager = new GameViewManager();
                gameManager.createNewGame(mainStage, 2);
            }
        });

        return mediumButton;
    }

    private MenuButton createHardButton() {
        MenuButton hardButton = new MenuButton("HARD");
        hardButton.setLayoutX(60);
        hardButton.setLayoutY(25+hardButton.getPrefHeight()*2);

        hardButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                GameViewManager gameManager = new GameViewManager();
                gameManager.createNewGame(mainStage, 3);
            }
        });

        return hardButton;
    }

    private void createBackground() {
        Image background = new Image("background.png", FRAME_WIDTH, FRAME_HEIGHT, false, true);
        BackgroundImage backgroundImage = new BackgroundImage(background, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, null);
        mainPane.setBackground(new Background(backgroundImage));
    }

    private void createLogo() {
        String content = "Mickey Mouse\nGAME & WATCH";
        Text text = new Text(325, 100, "");
        try {
            text.setFont((Font.loadFont(new FileInputStream(FONT_PATH), 40)));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
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
        mainPane.getChildren().add(text);
    }

}
