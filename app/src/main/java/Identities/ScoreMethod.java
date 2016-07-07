package Identities;

/**
 * Created by dennis on 07-07-16.
 */
public enum ScoreMethod {

    BLOCK("Block"),
    ATTACK("Attack"),
    SERVE("Serve"),
    UNFORCED("Unforced error"),
    OTHER("Other");

    private String scoreMethodString;

    private ScoreMethod(String positionString){
        this.scoreMethodString = positionString;
    }

    @Override public String toString(){
        return scoreMethodString;
    };
}
