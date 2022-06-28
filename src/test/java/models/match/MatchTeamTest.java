package models.match;

import models.player.Player;
import models.team.Team;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class MatchTeamTest {

    private MatchTeam matchTeam;

    @BeforeEach
    public void TeamTest(){
        this.matchTeam = new MatchTeam(this.createBlackTeam(this.createBlackTeamPlayers()));
    }

    @Test
    public void shouldAddGoalToTeam() {
        this.matchTeam.addGoal();
        Assertions.assertEquals(1, this.matchTeam.getGoals(),"Black FC has to be with 1 goal");
    }

    @Test
    public void shouldAddMatchTeamHome() {
        this.matchTeam.setIsHome(true);
        Assertions.assertTrue(this.matchTeam.getIsHome(), "Should return true, as the match team is home");
    }

    @Test
    public void shouldAddMatchTeaAway() {
        this.matchTeam.setIsHome(false);
        Assertions.assertFalse(this.matchTeam.getIsHome(), "Should return false, as the match team is away");
    }

    private Team createBlackTeam(List<Player> players) {
        Team blackTeam = new Team("Black FC");
        int shirtNumber = 1;

        for (Player player : players) {
            blackTeam.addPlayer(player, shirtNumber);
        }

        return blackTeam;
    }

    private List<Player> createBlackTeamPlayers() {
        Player goalkeeper = Player.CreateAsGoalkeeper("Jorge", 30, 195, 90);
        Player defenderOne = Player.CreateAsDefender("Alemão", 27, 75, 80);
        Player defenderTwo = Player.CreateAsDefender("João", 22, 85, 75);
        Player strikerOne = Player.CreateAsStriker("Euzebio", 33, 70, 85);
        Player strikerTwo = Player.CreateAsStriker("Romero", 21, 95, 85);

        return List.of(goalkeeper, defenderOne, defenderTwo, strikerOne, strikerTwo);
    }
}
