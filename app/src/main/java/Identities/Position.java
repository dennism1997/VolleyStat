package Identities;

/**
 * Created by dennis on 06-07-16.
 */
public enum Position {

    MIDDLE("Middle"),
    OUTSIDE("Outside"),
    SETTER("Setter"),
    OPPOSITE("Opposite"),
    LIBERO("Libero");

    private String positionString;

    private Position(String positionString){
        this.positionString = positionString;
    }

    @Override public String toString(){
        return positionString;
    };
}
