package Identities;

/**
 * Created by dennis on 07-07-16.
 */
public class Game {

    private Team homeTeam;
    private Team awayTeam;
    private int homeTeamScore;
    private int awayTeamScore;

    public Game(Team homeTeam, Team awayTeam) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        homeTeamScore = 0;
        awayTeamScore = 0;
    }

    public void homeScored(){
        homeTeamScore++;
    }

    public void awayScored(){
        awayTeamScore++;
    }


}
