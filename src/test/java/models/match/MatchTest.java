package models.match;

import models.player.Player;
import models.team.Team;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

public class MatchTest {

    private Match match;

    @BeforeEach
    public void MatchTest() {
        Team blackTeam = this.createBlackTeam(this.createBlackTeamPlayers());
        Team greenTeam = this.createGreenTeam(this.createGreenTeamPlayers());

        this.match = new Match(new MatchTeam(blackTeam), new MatchTeam(greenTeam), LocalDateTime.now());
        this.match.getTeams()[0].setIsHome(true);
        this.match.getTeams()[1].setIsHome(false);
    }

    @Test
    public void shouldGetMatchHomeTeam() {
        MatchTeam homeMatchTeam = this.match.getMatchTeam(true);
        Assertions.assertTrue(homeMatchTeam.getIsHome(), "Match should return false, signaling o Black FC team");
    }

    @Test
    public void shouldGetMatchAwayTeam() {
        MatchTeam awayMatchTeam = this.match.getMatchTeam(false);
        Assertions.assertFalse(awayMatchTeam.getIsHome(), "Match should return false, signaling o Green FC team");
    }

    @Test
    public void shouldGetHomeTeam() {
        Team homeTeam = this.match.getTeam(true);
        Assertions.assertEquals("Black FC", homeTeam.getName(), "Match should return the home team name, Black FC");
    }

    @Test
    public void shouldGetAwayTeam() {
        Team awayTeam = this.match.getTeam(false);
        Assertions.assertEquals("Green FC", awayTeam.getName(), "Match should return the away team name, Green FC");
    }

    @Test
    public void shouldSetBlackTeamWinner() {
        this.match.getMatchTeam(true).addGoal();
        this.match.getMatchTeam(true).addGoal();
        this.match.getMatchTeam(true).addGoal();
        this.match.getMatchTeam(false).addGoal();

        this.match.setWinningTeam();

        Assertions.assertNotNull(this.match.getWinningTeam(), "Match should return a instance object for Black FC team");
        Assertions.assertEquals(this.match.getMatchTeam(true), this.match.getWinningTeam(),
                "Match should has the same reference for team, on the winningTeam and homeTeam variables");
    }

    @Test
    public void shouldSetGreenTeamWinner() {
        this.match.getMatchTeam(false).addGoal();

        this.match.setWinningTeam();

        Assertions.assertNotNull(this.match.getWinningTeam(), "Match should return a instance object for Green FC team");
        Assertions.assertEquals(this.match.getMatchTeam(false), this.match.getWinningTeam(),
                "Match should has the same reference for team, on the winningTeam and homeTeam variables");
    }

    private Team createBlackTeam(List<Player> players) {
        Team blackTeam = new Team("Black FC");
        int shirtNumber = 1;

        for (Player player : players) {
            blackTeam.addPlayer(player, shirtNumber);
        }

        return blackTeam;
    }

    private Team createGreenTeam(List<Player> players) {
        Team blackTeam = new Team("Green FC");
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

    private List<Player> createGreenTeamPlayers() {
        Player goalkeeper = Player.CreateAsGoalkeeper("Arnaldo", 33, 189, 80);
        Player defenderOne = Player.CreateAsDefender("Igor", 29, 65, 70);
        Player defenderTwo = Player.CreateAsDefender("Gerson", 20, 70, 65);
        Player strikerOne = Player.CreateAsStriker("Renato", 35, 60, 80);
        Player strikerTwo = Player.CreateAsStriker("Eduardo", 20, 75, 75);

        return List.of(goalkeeper, defenderOne, defenderTwo, strikerOne, strikerTwo);
    }
}
