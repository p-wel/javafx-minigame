package view;

import javafx.animation.*;
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
    public static final int CHICKEN_WIDTH = 50;

    public static final int SCREEN_X_START = 260;
    public static final int SCREEN_X_END = 720;
    public static final int SCREEN_Y_START = 160;
    public static final int SCREEN_Y_END = 430;

    private static final int BUCKET_SPEED = 4;
    private static final int BUCKET_HEIGHT = Bucket.getHEIGHT()-30;
    private static final int BUCKET_WIDTH = Bucket.getWIDTH()-30;
    private static final int EGG_SIZE = 16;

    private static final int CHICKEN_1_X = SCREEN_X_START + CHICKEN_WIDTH;
    private static final int CHICKEN_1_Y = 240;
    private static final int CHICKEN_2_X = SCREEN_X_START + CHICKEN_WIDTH;
    private static final int CHICKEN_2_Y = 310;
    private static final int CHICKEN_3_X = SCREEN_X_END-30;
    private static final int CHICKEN_3_Y = 240;
    private static final int CHICKEN_4_X = SCREEN_X_END-30;
    private static final int CHICKEN_4_Y = 300;

    private Stage menuStage;
    private Bucket bucket;
    private int angle;
    private final int difficulty;
    private boolean isLeftKeyPressed;
    private boolean isRightKeyPressed;
    private boolean isUpKeyPressed;
    private boolean isDownKeyPressed;
    private AnimationTimer gameTimer;

    private final static String EGG_IMAGE = "egg.png";

    private GameLabel pointsLabel;
    private ImageView[] lifes;
    private ImageView[] eggs;
    private int playerLifes;
    private int playerPoints;

    private final static String HEART_IMAGE = "heart.png";

    private Random randomPosition = new Random();

    public GameViewManager(int difficulty) {
        this.difficulty = difficulty;
        initStage();
        createBackground();
        createBucket();
        createEggs();
        createGameInfo();
        createKeyListeners();
        randomPosition = new Random();
    }

    private void createKeyListeners() {
        gameScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.LEFT) {
//                    System.out.println("LEFT");
                    isLeftKeyPressed = true;
                }
                if (keyEvent.getCode() == KeyCode.RIGHT) {
//                    System.out.println("RIGHT");
                    isRightKeyPressed = true;
                }
                if (keyEvent.getCode() == KeyCode.UP) {
                    isUpKeyPressed = true;
//                    System.out.println("UP");
                }
                if (keyEvent.getCode() == KeyCode.DOWN) {
                    isDownKeyPressed = true;
//                    System.out.println("DOWN");
                }
            }
        });

        gameScene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.LEFT) {
//                    System.out.println("LEFT");
                    isLeftKeyPressed = false;
                }
                if (keyEvent.getCode() == KeyCode.RIGHT) {
//                    System.out.println("RIGHT");
                    isRightKeyPressed = false;
                }
                if (keyEvent.getCode() == KeyCode.UP) {
//                    System.out.println("UP");
                    isUpKeyPressed = false;
                }
                if (keyEvent.getCode() == KeyCode.DOWN) {
//                    System.out.println("DOWN");
                    isDownKeyPressed = false;
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

    public void createNewGame(Stage menuStage) {
        this.menuStage = menuStage;
        this.menuStage.hide();
        createGameLoop();
        gameStage.show();
    }

    public void createEggs() {
        eggs = new ImageView[this.difficulty];

        for (int i = 0; i < eggs.length; i++) {
            eggs[i] = new ImageView(EGG_IMAGE);
            setEggStartingPosition(eggs[i]);
            gamePane.getChildren().add(eggs[i]);
        }
    }

    private void createGameInfo() {
        playerPoints = 0;
        pointsLabel = new GameLabel(String.valueOf(playerPoints));
        pointsLabel.setLayoutX(SCREEN_X_END - pointsLabel.getPrefWidth() + 12);
        pointsLabel.setLayoutY(SCREEN_Y_END - 20);
        gamePane.getChildren().add(pointsLabel);

        playerLifes = 3;
        lifes = new ImageView[3];

        for (int i = 0; i < lifes.length; i++) {
            lifes[i] = new ImageView(HEART_IMAGE);
            lifes[i].setFitWidth(20);
            lifes[i].setFitHeight(20);
            lifes[i].setLayoutX(SCREEN_X_END - 50 + (i * 20));
            lifes[i].setLayoutY(SCREEN_Y_START + 10);
            gamePane.getChildren().add(lifes[i]);
        }

    }


    private void setEggStartingPosition(ImageView image) {
        int randomChicken = randomPosition.nextInt(4) + 1;
        switch (randomChicken) {
            case 1 -> {
                image.setLayoutX(CHICKEN_1_X);
                image.setLayoutY(CHICKEN_1_Y);
            }
            case 2 -> {
                image.setLayoutX(CHICKEN_2_X);
                image.setLayoutY(CHICKEN_2_Y);
            }
            case 3 -> {
                image.setLayoutX(CHICKEN_3_X);
                image.setLayoutY(CHICKEN_3_Y);
            }
            case 4 -> {
                image.setLayoutX(CHICKEN_4_X);
                image.setLayoutY(CHICKEN_4_Y);
            }
            default -> {
                System.out.println("Error in randomChicken number (out of 1-4 range)");
                image.setLayoutX(CHICKEN_1_X);
                image.setLayoutY(CHICKEN_1_Y);
            }
        }
    }

    private void moveEggs() {
        for (ImageView egg : eggs) {
            if (egg.getLayoutX() < GAME_WIDTH / 2) {
                moveLeftSideEggs(egg);
            } else {
                moveRightSideEggs(egg);

            }
        }
    }

    private void moveLeftSideEggs(ImageView egg) {
        if (egg.getLayoutX() > SCREEN_X_START + CHICKEN_WIDTH + 50) {
            // FALL ->
            egg.setLayoutX(egg.getLayoutX() + 0.3);
            egg.setLayoutY(egg.getLayoutY() + 0.45);
            egg.setRotate(egg.getRotate() + 2);
        } else if (egg.getLayoutX() > SCREEN_X_START + CHICKEN_WIDTH + 10) {
            // ROLL SLOPE ->
            egg.setLayoutX(egg.getLayoutX() + 0.5);
            egg.setLayoutY(egg.getLayoutY() + 0.4);
            egg.setRotate(egg.getRotate() + 3);
        } else {
            // ROLL FLAT ->
            egg.setLayoutX(egg.getLayoutX() + 0.2);
            egg.setRotate(egg.getRotate() + 1);
        }
    }

    private void moveRightSideEggs(ImageView egg) {
        if (egg.getLayoutX() < SCREEN_X_END - CHICKEN_WIDTH - 30) {
            // FALL <-
            egg.setLayoutX(egg.getLayoutX() - 0.3);
            egg.setLayoutY(egg.getLayoutY() + 0.45);
            egg.setRotate(egg.getRotate() - 2);
        } else if (egg.getLayoutX() < SCREEN_X_END - CHICKEN_WIDTH + 15) {
            // ROLL SLOPE <-
            egg.setLayoutX(egg.getLayoutX() - 0.5);
            egg.setLayoutY(egg.getLayoutY() + 0.4);
            egg.setRotate(egg.getRotate() - 3);
        } else {
            // ROLL FLAT <-
            egg.setLayoutX(egg.getLayoutX() - 0.2);
            egg.setRotate(egg.getRotate() - 1);
        }
    }

    private void checkEggIfBroken() {
        for (ImageView egg : eggs) {
            if (egg.getLayoutY() > SCREEN_Y_END) {
                loseLife();
                setEggStartingPosition(egg);
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
                checkEggIfBroken();
                checkIfEggCatched();
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

    private void checkIfEggCatched() {
        for (int i = 0; i < eggs.length; i++) {
            if (EGG_SIZE + BUCKET_HEIGHT > calculateDistance(
                    bucket.getLayoutX()+BUCKET_WIDTH/2,
                    eggs[i].getLayoutX(),
                    bucket.getLayoutY()+BUCKET_HEIGHT/2,
                    eggs[i].getLayoutY())
            ) {
                pointsLabel.setText(String.valueOf(++playerPoints));
                setEggStartingPosition(eggs[i]);
            }
        }
    }

    private void loseLife() {
        gamePane.getChildren().remove(lifes[playerLifes-1]);
        playerLifes--;
        System.out.println(playerLifes);
        if (playerLifes <= 0) {
            gameStage.close();
            gameTimer.stop();
            menuStage.show();
        }
    }

    private double calculateDistance(double x1, double x2, double y1, double y2) {
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }

}
