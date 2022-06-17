package view;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Bucket;

public class GameViewManager {

    private AnchorPane gamePane;
    private Scene gameScene;
    private Stage gameStage;

    public static final int GAME_WIDTH = 1000;
    public static final int GAME_HEIGHT = 600;
    private static final int bucketSpeed = 4;

    private Stage menuStage;
    private int difficulty;
    private Bucket bucket;
    private boolean isLeftKeyPressed;
    private boolean isRightKeyPressed;
    private boolean isUpKeyPressed;
    private boolean isDownKeyPressed;
    private int angle;
    private AnimationTimer gameTimer;

    public GameViewManager() {
        initStage();
        createKeyListeners();
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
        createBucket();
        createGameLoop();
        gameStage.show();
    }

    private void createBucket() {
        bucket = new Bucket();
        bucket.setLayoutX(100);
        bucket.setLayoutY(100);
        gamePane.getChildren().add(bucket);
    }

    private void createGameLoop() {
        gameTimer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                moveBucket();
            }
        };
        gameTimer.start();
    }

    private void moveBucket() {
        // RIGHT / LEFT
        if (isLeftKeyPressed && !isRightKeyPressed) {
            if (angle > -30) {
                angle -= 5;
            }
            bucket.setRotate(angle);
            if (bucket.getLayoutX() > 10) {
                bucket.setLayoutX(bucket.getLayoutX() - bucketSpeed);
            }
        }
        if (isRightKeyPressed && !isLeftKeyPressed) {
            if (angle < 30) {
                angle += 5;
            }
            bucket.setRotate(angle);
            if (bucket.getLayoutX() < 920) {
                bucket.setLayoutX(bucket.getLayoutX() + bucketSpeed);
            }
        }
        if (!isLeftKeyPressed && !isRightKeyPressed) {
            if (angle < 0) {
                angle += 5;
            } else if (angle > 0) {
                angle -= 5;
            }
            bucket.setRotate(angle);
        }
        if (isLeftKeyPressed && isRightKeyPressed) {
            if (angle < 0) {
                angle += 5;
            } else if (angle > 0) {
                angle -= 5;
            }
            bucket.setRotate(angle);
            if (bucket.getLayoutX() < 920) {
                bucket.setLayoutX(bucket.getLayoutX() + bucketSpeed);
            }
        }

        // UP / DOWN
        if (isUpKeyPressed && !isDownKeyPressed) {
            if (bucket.getLayoutY() > 3) {
                bucket.setLayoutY(bucket.getLayoutY() - bucketSpeed);
            }
        }
        if (isDownKeyPressed && !isUpKeyPressed) {
            if (bucket.getLayoutY() < 520) {
                bucket.setLayoutY(bucket.getLayoutY() + bucketSpeed);
            }
        }
        if (isUpKeyPressed && isDownKeyPressed) {
            if (bucket.getLayoutY() > 3) {
                bucket.setLayoutY(bucket.getLayoutY() - bucketSpeed);
            }
        }
    }

}
