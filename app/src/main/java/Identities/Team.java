package Identities;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by dennis on 05-07-16.
 */
public class Team implements Serializable{

    private String name;
    private ArrayList<Player> players;

    public Team(ArrayList<Player> team, String name) {
        this.players = team;
        this.name = name;
    }

    public Player getPlayer(int i) {
        return players.get(i);
    }

    public String getName(){
        return this.name;
    }

    public void substitute(int indexIn, int indexOut) {
        Player temp = players.get(indexOut);
        players.set(indexOut, players.get(indexIn));
        players.set(indexIn, temp);
    }

    /**
     * Rotates the 6 players in the field
     */
    public void rotate() {
        Player temp = players.get(0);
        for(int i = 0; i < 5; i++){
            players.set(i, players.get(i+1));
        }
        players.set(5, temp);

    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public void addPlayer(Player player){
        this.players.add(player);
    }

    public boolean isEmpty(){
        return players.isEmpty();
    }

    public String toString() {
        String result = "";
        for(int i=0; i<this.players.size(); i++){
            result += this.players.get(i).getNumber() + "\n";
        }

        return result;
    }

    public void removePlayer(int index) {
        players.remove(index);
    }

    public boolean enoughPlayers(){
        return players.size() >= 6;
    }

    public boolean containsNumber(int n) {
        for(Player p : this.getPlayers()){
            if(p.getNumber() == n){
                return true;
            }
        }
        return false;
    }

}
