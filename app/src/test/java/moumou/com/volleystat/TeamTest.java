package moumou.com.volleystat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import Identities.Player;
import Identities.Position;
import Identities.Team;

import static org.junit.Assert.*;
/**
 * Created by dennis on 05-07-16.
 */
public class TeamTest {

    ArrayList<Player> playerArrayList = new ArrayList<Player>(13);
    Team team;
    @Before
    public void setUp(){
        playerArrayList.add(new Player(1,"dennis", Position.MIDDLE, true));
        playerArrayList.add(new Player(2,"dennis", Position.MIDDLE, true));
        playerArrayList.add(new Player(3,"dennis", Position.MIDDLE, true));
        playerArrayList.add(new Player(4,"dennis", Position.MIDDLE, true));
        playerArrayList.add(new Player(5,"dennis", Position.MIDDLE, true));
        playerArrayList.add(new Player(6,"dennis", Position.MIDDLE, true));
        playerArrayList.add(new Player(7,"dennis", Position.MIDDLE, true));
        playerArrayList.add(new Player(8,"dennis", Position.MIDDLE, true));
        team = new Team(playerArrayList);
    }


    @Test
    public void rotateTest() {
        assertEquals(1, team.getPlayer(0).getNumber());
        assertEquals(7, team.getPlayer(6).getNumber());
        team.rotate();
        assertEquals(2, team.getPlayer(0).getNumber());
        assertEquals(7, team.getPlayer(6).getNumber());
    }

    @After
    public void tearDown(){
        playerArrayList.clear();
    }
}
