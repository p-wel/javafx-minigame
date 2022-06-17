package model;

import javafx.animation.TranslateTransition;
import javafx.scene.SubScene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.util.Duration;
import view.ViewManager;

public class SubMenu extends SubScene {

    private static final int FRAME_WIDTH = ViewManager.FRAME_WIDTH;
    private static final int FRAME_HEIGHT = ViewManager.FRAME_HEIGHT;
    private static final int SUB_MENU_WIDTH = 190;
    private static final int SUB_MENU_HEIGHT = 180;

    private final static String FONT_PATH = "src/main/resources/kenvector_future.ttf";
    private final static String BACKGROUND_IMAGE = "yellow_panel.png";

    private boolean isHidden;

    public SubMenu() {
        super(new AnchorPane(), SUB_MENU_WIDTH, SUB_MENU_HEIGHT);
        prefWidth(SUB_MENU_WIDTH);
        prefHeight(SUB_MENU_HEIGHT);

        BackgroundImage image = new BackgroundImage(new Image(BACKGROUND_IMAGE, SUB_MENU_WIDTH, SUB_MENU_HEIGHT, false, true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);
        AnchorPane root2 = (AnchorPane) this.getRoot();
        root2.setBackground(new Background(image));

        isHidden = true;

        setLayoutX(FRAME_WIDTH / 2 - SUB_MENU_WIDTH / 2);
        setLayoutY(FRAME_HEIGHT);
    }

    public SubMenu(int width) {
        super(new AnchorPane(), width, SUB_MENU_HEIGHT);
        prefWidth(width);
        prefHeight(SUB_MENU_HEIGHT);

        BackgroundImage image = new BackgroundImage(new Image(BACKGROUND_IMAGE, width, SUB_MENU_HEIGHT, false, true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);
        AnchorPane root2 = (AnchorPane) this.getRoot();
        root2.setBackground(new Background(image));

        isHidden = true;

        setLayoutX(FRAME_WIDTH / 2 - width / 2);
        setLayoutY(FRAME_HEIGHT);
    }


    public void moveSubMenu() {
        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.seconds(0.2));
        transition.setNode(this);

        if (isHidden) {
//        transition.setToY(-FRAME_HEIGHT/2 -90);
            transition.setToY(-180);
            isHidden = false;
        } else {
//            transition.setToY(FRAME_HEIGHT / 2 + 90);
            transition.setToY(180);
            isHidden = true;
        }
        transition.play();
    }

    public AnchorPane getPane(){
        return (AnchorPane) this.getRoot();
    }
}
