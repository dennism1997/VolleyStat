package Identities;

import java.io.Serializable;

/**
 * Created by dennis on 05-07-16.
 */
public class Player implements Serializable{


    private int number;
    private String name;
    private Position position;
    private boolean playing;

    /**
     * Makes a new player object
     * @param number shirt number
     * @param name player name
     * @param position player position
     * @param playing in game or substitute
     */
    public Player(int number, String name, Position position, boolean playing) {
        this.number = number;
        this.name = name;
        this.position = position;
        this.playing = playing;
    }

    /**
     * Makes a new substitute player object
     * @param number shirt number
     * @param name player name
     * @param position player position
     */
    public Player(int number, String name, Position position) {
        this.number = number;
        this.name = name;
        this.position = position;
        playing = false;
    }

    public boolean isPlaying() {
        return playing;
    }

    public void setPlaying(boolean playing) {
        this.playing = playing;
    }

    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public Position getPosition() {
        return position;
    }
}
