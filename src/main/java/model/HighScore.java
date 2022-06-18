package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class HighScore implements Serializable {

    private String player;
    private int points;
    private static List<HighScore> highScoreList = new ArrayList<>();

    public HighScore(String player, int points) {
        this.player = player;
        this.points = points;
        highScoreList.add(this);
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public static List<HighScore> getHighScoreList() {
        return highScoreList;
    }

    @Override
    public String toString() {
        return "HighScore{" +
                "player='" + player + '\'' +
                ", points=" + points +
                '}';
    }
}
