package Identities;

/**
 * Created by dennis on 07-07-16.
 */
public class Score {
    private int playerNumber;

    //True if we scored, false if they scored.
    private boolean homeScore;
    private ScoreMethod scoreMethod;

    public Score(int playerNumber, boolean homeScore, ScoreMethod scoreMethod) {
        this.playerNumber = playerNumber;
        this.homeScore = homeScore;
        this.scoreMethod = scoreMethod;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public boolean isHomeScore() {
        return homeScore;
    }

    public ScoreMethod getScoreMethod() {
        return scoreMethod;
    }
}
