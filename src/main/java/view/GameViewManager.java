package view;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
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
    private static final int bucketSpeed = 4;

    private Stage menuStage;
    private int difficulty;
    private Bucket bucket;
    private int angle;
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
        image.setLayoutX(randomPosition.nextInt(SCREEN_WIDTH));
        image.setLayoutY(GAME_HEIGHT-SCREEN_HEIGHT);
    }

    private void moveGameElements() {
        for (int i = 0; i < eggs.length; i++) {
            eggs[i].setLayoutY(eggs[i].getLayoutY() + 1);
            eggs[i].setRotate(eggs[i].getRotate() + 7);
        }
    }

    private void checkIfElementAreBehindAndRelocate() {
        for (int i = 0; i < eggs.length; i++) {
            if (eggs[i].getLayoutY() > 500) {
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
                moveGameElements();
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
                bucket.setLayoutX(bucket.getLayoutX() - bucketSpeed);
            }
        }
        // RIGHT
        if (isRightKeyPressed && !isLeftKeyPressed) {
            if (angle < 30) {
                angle += 5;
            }
            bucket.setRotate(angle);
            if (bucket.getLayoutX() < SCREEN_WIDTH + PLAYGROUND_WIDTH - bucket.getPrefWidth() * 1.8) {
                bucket.setLayoutX(bucket.getLayoutX() + bucketSpeed);
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
                bucket.setLayoutX(bucket.getLayoutX() + bucketSpeed);
            }
        }

        // UP
        if (isUpKeyPressed && !isDownKeyPressed) {
            if (bucket.getLayoutY() > SCREEN_HEIGHT - PLAYGROUND_HEIGHT + bucket.getPrefHeight() * 3.7) {
                bucket.setLayoutY(bucket.getLayoutY() - bucketSpeed);
            }
        }
        // DOWN
        if (isDownKeyPressed && !isUpKeyPressed) {
            if (bucket.getLayoutY() < SCREEN_HEIGHT + PLAYGROUND_HEIGHT - bucket.getPrefHeight() * 2.3) {
                bucket.setLayoutY(bucket.getLayoutY() + bucketSpeed);
            }
        }
        // BOTH (UP)
        if (isUpKeyPressed && isDownKeyPressed) {
            if (bucket.getLayoutY() > SCREEN_HEIGHT - PLAYGROUND_HEIGHT + bucket.getPrefHeight() * 3.7) {
                bucket.setLayoutY(bucket.getLayoutY() - bucketSpeed);
            }
        }
    }

    private void createBackground() {
        Image background = new Image("gameBackground.jpg", GAME_WIDTH, GAME_HEIGHT, false, true);
        BackgroundImage backgroundImage = new BackgroundImage(background, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, null);
        gamePane.setBackground(new Background(backgroundImage));
    }

}
