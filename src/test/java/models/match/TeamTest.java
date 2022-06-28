package models.match;

import models.team.Team;
import models.player.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class TeamTest {

    private Team team;

    @BeforeEach
    public void TeamTest(){
        team = new Team("Team");
    }

    @Test
    public void shouldAddPlayerToTheTeam() {
        Player goalkeeper = createGoalKeeper();
        team.addPlayer(goalkeeper, 1);
        Assertions.assertNotNull(team.getPlayer(1), "Team should return the player with the shirt number 1");
    }

    @Test
    public void shouldRemovePlayerFromTheTeam(){
        Player goalkeeper = createGoalKeeper();
        team.addPlayer(goalkeeper, 1);

        team.removePlayer(1);
        Assertions.assertNull(team.getPlayer(1), "Team should return the null because it dont have player with the number 1 shirt");
    }

    @Test
    public void shouldGetTheGoalkeeper(){
        Player goalkeeper = createGoalKeeper();
        team.addPlayer(goalkeeper, 1);
        Assertions.assertEquals(goalkeeper, team.getGoalkeeper());
    }

    @Test
    public void shouldGetTheDefenders(){
        List<Player> defenders = createDefenders(2);

        for (int i = 0; i< defenders.size(); i++) team.addPlayer(defenders.get(i), i);

        Assertions.assertEquals(defenders, team.getDefenders(), "Should return the same defenders that was added in the team");
    }

    @Test
    public void shouldGetTheStrikers(){
        List<Player> striker = createStrikers(2);

        for (int i = 0; i< striker.size(); i++) team.addPlayer(striker.get(i), i);

        Assertions.assertEquals(striker, team.getStrikers(), "Should return the same strikers that was added in the team");
    }

    @Test
    public void shouldReturnFalseToAddAGoalkeeper(){
        Player goalkeeper = createGoalKeeper();
        team.addPlayer(goalkeeper, 1);
        Assertions.assertFalse(team.addPlayer(goalkeeper, 1), "Should return false because the team already have a goalkeeper");
    }

    @Test
    public void shouldReturnFalseToAddADefensor(){
        List<Player> defensors = createDefenders(3);
        for (int i = 0; i< defensors.size(); i++) team.addPlayer(defensors.get(i), i);

        Assertions.assertFalse(team.addPlayer(defensors.get(2), 3), "Should return false because the team already have the limit of defensors");
    }

    @Test
    public void shouldReturnFalseToAddAStriker(){
        List<Player> strikers = createStrikers(3);
        for (int i = 0; i< strikers.size(); i++) team.addPlayer(strikers.get(i), i);

        Assertions.assertFalse(team.addPlayer(strikers.get(2), 3), "Should return false because the team already have the limit of strikers");
    }

    @Test
    public void shouldNotRepeatShirtNumbers(){
        List<Player> defenders = createDefenders(5);
        setShirtNumberTo(1, defenders);
        defenders.forEach(defender -> team.addPlayer(defender, defender.getShirtNumber()));

        Assertions.assertTrue(HasDifferentShirtNumber(team.getPlayers()), "Should return pass because the number shirt will not repeat");
    }

    private boolean HasDifferentShirtNumber (List<Player> players){
        for (int i = 0; i < players.size() - 1; i++){
            for (int j = i + 1; j < players.size() - 1; j++){
                if(Objects.equals(players.get(i).getShirtNumber(), players.get(j).getShirtNumber())) return false;
            }
        }
        return true;
    }

    private void setShirtNumberTo(int shirtNumber, List<Player> players){
        players.forEach(player -> player.setShirtNumber(shirtNumber));
    }

    private Player createGoalKeeper(){
        return Player.CreateAsGoalkeeper("Goalkeeper", 21, 200, 87);
    }

    private List<Player> createDefenders(int totalToCreate){
        List<Player> defenders = new ArrayList<>();
        for (int i = 0; i < totalToCreate; i++){
            defenders.add(Player.CreateAsDefender("Defender " + i, 18, 92,79));
        }
        return defenders;
    }

    private List<Player> createStrikers(int totalToCreate){
        List<Player> strikers = new ArrayList<>();
        for (int i = 0; i < totalToCreate; i++){
            strikers.add(Player.CreateAsStriker("Striker " + i, 18, 92,79));
        }
        return strikers;
    }
}