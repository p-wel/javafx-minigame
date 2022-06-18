package view;

import javafx.animation.*;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Bucket;

import java.util.Random;

public class GameViewManager {

    private AnchorPane gamePane;
    private Scene gameScene;
    private Stage gameStage;

    public static final int GAME_WIDTH = 1000;
    public static final int GAME_HEIGHT = 600;
    public static final int SCREEN_WIDTH = 470;
    public static final int SCREEN_HEIGHT = 290;
    public static final int PLAYGROUND_WIDTH = 190;
    public static final int PLAYGROUND_HEIGHT = 210;
    public static final int CHICKEN_WIDTH = 40;

    public static final int SCREEN_X_START = 260;
    public static final int SCREEN_X_END = 700;
    public static final int SCREEN_Y_START = 160;
    public static final int SCREEN_Y_END = 430;

    private static final int BUCKET_SPEED = 4;

    private static final int CHICKEN_1_X = SCREEN_X_START + CHICKEN_WIDTH;
    private static final int CHICKEN_1_Y = 240;
    private static final int CHICKEN_2_X = SCREEN_X_END - CHICKEN_WIDTH;
    private static final int CHICKEN_2_Y = 240;
    private static final int CHICKEN_3_X = SCREEN_X_START + CHICKEN_WIDTH;
    private static final int CHICKEN_3_Y = 300;
    private static final int CHICKEN_4_X = SCREEN_X_END - CHICKEN_WIDTH;
    private static final int CHICKEN_4_Y = 300;


    private Stage menuStage;
    private Bucket bucket;
    private int angle;
    private int difficulty;
    private boolean isLeftKeyPressed;
    private boolean isRightKeyPressed;
    private boolean isUpKeyPressed;
    private boolean isDownKeyPressed;
    private AnimationTimer gameTimer;

    private final static String EGG_IMAGE = "egg16px.png";

    private ImageView[] eggs;
    private Random randomPosition = new Random();

    public GameViewManager() {
        initStage();
        createBackground();
        createBucket();
        createGameElements();
        createKeyListeners();
        randomPosition = new Random();
    }

    private void createKeyListeners() {
        gameScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.LEFT) {
                    System.out.println("LEFT");
                    isLeftKeyPressed = true;
                }
                if (keyEvent.getCode() == KeyCode.RIGHT) {
                    System.out.println("RIGHT");
                    isRightKeyPressed = true;
                }
                if (keyEvent.getCode() == KeyCode.UP) {
                    isUpKeyPressed = true;
                    System.out.println("UP");
                }
                if (keyEvent.getCode() == KeyCode.DOWN) {
                    isDownKeyPressed = true;
                    System.out.println("DOWN");
                }
            }
        });

        gameScene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.LEFT) {
                    System.out.println("LEFT");
                    isLeftKeyPressed = false;
                }
                if (keyEvent.getCode() == KeyCode.RIGHT) {
                    System.out.println("RIGHT");
                    isRightKeyPressed = false;
                }
                if (keyEvent.getCode() == KeyCode.UP) {
                    isUpKeyPressed = false;
                    System.out.println("UP");
                }
                if (keyEvent.getCode() == KeyCode.DOWN) {
                    isDownKeyPressed = false;
                    System.out.println("DOWN");
                }
            }
        });
    }

    private void initStage() {
        gamePane = new AnchorPane();
        gameScene = new Scene(gamePane, GAME_WIDTH, GAME_HEIGHT);
        gameStage = new Stage();
        gameStage.setScene(gameScene);
    }

    public void createNewGame(Stage menuStage, int difficulty) {
        this.menuStage = menuStage;
        this.difficulty = difficulty;
        this.menuStage.hide();
        createGameLoop();
        gameStage.show();
    }

    public void createGameElements() {
        eggs = new ImageView[3];
        for (int i = 0; i < eggs.length; i++) {
            eggs[i] = new ImageView(EGG_IMAGE);
            setNewElementPosition(eggs[i]);
            gamePane.getChildren().add(eggs[i]);
        }
    }

    private void setNewElementPosition(ImageView image) {
        int randomChicken = randomPosition.nextInt(4) + 1;
        System.out.println(randomChicken);

        switch (randomChicken) {
            case 1:
                image.setLayoutX(CHICKEN_1_X);
                image.setLayoutY(CHICKEN_1_Y);
                break;
            case 2:
                image.setLayoutX(CHICKEN_2_X);
                image.setLayoutY(CHICKEN_2_Y);
                break;
            case 3:
                image.setLayoutX(CHICKEN_3_X);
                image.setLayoutY(CHICKEN_3_Y);
                break;
            case 4:
                image.setLayoutX(CHICKEN_4_X);
                image.setLayoutY(CHICKEN_4_Y);
                break;
            default:
                System.out.println("Error in setNewElementPosition() randomChicken number (out of 1-4 range)");
                image.setLayoutX(CHICKEN_1_X);
                image.setLayoutY(CHICKEN_1_Y);
                break;
        }
    }

    private void moveEggs() {
        for (int i = 0; i < eggs.length; i++) {
            eggs[i].setLayoutY(eggs[i].getLayoutY() + 1);
            eggs[i].setRotate(eggs[i].getRotate() + 7);
        }

//        Ellipse egg = new Ellipse(10, 20);
//        egg.getStyleClass().add("style.css");
//
//        PathTransition pathTransition = new PathTransition(Duration.seconds(5), egg);
//        Path path = new Path();
//        path.getElements().add(new MoveTo(100, 100));
//        path.getElements().add(new LineTo(200, 200));
//        pathTransition.setNode(egg);
//        pathTransition.setPath(path);
//
//        RotateTransition rotateTransition = new RotateTransition(Duration.seconds(1), egg);
//        rotateTransition.setFromAngle(0);
//        rotateTransition.setToAngle(359);
//
//        ParallelTransition parallelTransition = new ParallelTransition();
//        parallelTransition.getChildren().addAll(rotateTransition, pathTransition);
//        parallelTransition.setCycleCount(Animation.INDEFINITE);
//        parallelTransition.play();
    }

    private void checkIfElementAreBehindAndRelocate() {
        for (int i = 0; i < eggs.length; i++) {
            if (eggs[i].getLayoutY() > SCREEN_Y_END) {
                setNewElementPosition(eggs[i]);
            }
        }
    }

    private void createBucket() {
        bucket = new Bucket();
        bucket.setLayoutX(GAME_WIDTH / 2);
        bucket.setLayoutY(GAME_HEIGHT / 2);
        gamePane.getChildren().add(bucket);
    }

    private void createGameLoop() {
        gameTimer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                moveEggs();
                checkIfElementAreBehindAndRelocate();
                moveBucket();
            }
        };
        gameTimer.start();
    }

    private void moveBucket() {
        // LEFT
        if (isLeftKeyPressed && !isRightKeyPressed) {
            if (angle > -30) {
                angle -= 5;
            }
            bucket.setRotate(angle);
            if (bucket.getLayoutX() > SCREEN_WIDTH - PLAYGROUND_WIDTH + bucket.getPrefWidth() * 2) {
                bucket.setLayoutX(bucket.getLayoutX() - BUCKET_SPEED);
            }
        }
        // RIGHT
        if (isRightKeyPressed && !isLeftKeyPressed) {
            if (angle < 30) {
                angle += 5;
            }
            bucket.setRotate(angle);
            if (bucket.getLayoutX() < SCREEN_WIDTH + PLAYGROUND_WIDTH - bucket.getPrefWidth() * 1.8) {
                bucket.setLayoutX(bucket.getLayoutX() + BUCKET_SPEED);
            }
        }
        // NONE
        if (!isLeftKeyPressed && !isRightKeyPressed) {
            if (angle < 0) {
                angle += 5;
            } else if (angle > 0) {
                angle -= 5;
            }
            bucket.setRotate(angle);
        }
//         BOTH (RIGHT)
        if (isLeftKeyPressed && isRightKeyPressed) {
            if (angle < 30) {
                angle += 5;
            }
            bucket.setRotate(angle);
            if (bucket.getLayoutX() < SCREEN_WIDTH + PLAYGROUND_WIDTH - bucket.getPrefWidth() * 1.8) {
                bucket.setLayoutX(bucket.getLayoutX() + BUCKET_SPEED);
            }
        }

        // UP
        if (isUpKeyPressed && !isDownKeyPressed) {
            if (bucket.getLayoutY() > SCREEN_HEIGHT - PLAYGROUND_HEIGHT + bucket.getPrefHeight() * 3.7) {
                bucket.setLayoutY(bucket.getLayoutY() - BUCKET_SPEED);
            }
        }
        // DOWN
        if (isDownKeyPressed && !isUpKeyPressed) {
            if (bucket.getLayoutY() < SCREEN_HEIGHT + PLAYGROUND_HEIGHT - bucket.getPrefHeight() * 2.3) {
                bucket.setLayoutY(bucket.getLayoutY() + BUCKET_SPEED);
            }
        }
        // BOTH (UP)
        if (isUpKeyPressed && isDownKeyPressed) {
            if (bucket.getLayoutY() > SCREEN_HEIGHT - PLAYGROUND_HEIGHT + bucket.getPrefHeight() * 3.7) {
                bucket.setLayoutY(bucket.getLayoutY() - BUCKET_SPEED);
            }
        }
    }

    private void createBackground() {
        Image background = new Image("gameBackground.jpg", GAME_WIDTH, GAME_HEIGHT, false, true);
        BackgroundImage backgroundImage = new BackgroundImage(background, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, null);
        gamePane.setBackground(new Background(backgroundImage));
    }

}
