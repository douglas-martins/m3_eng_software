import models.*;
import models.match.MatchSimulator;
import models.match.MatchTeam;
import models.match.MatchesStatistics;
import models.player.Defender;
import models.player.Goalkeeper;
import models.player.Player;
import models.player.Striker;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
       Main main = new Main();

       main.runMatchSimulator();
    }

    private void runMatchSimulator() {
        List<Player> blackTeamPlayers = this.createBlackTeamPlayers();
        Team blackTeam = this.createBlackTeam(blackTeamPlayers);
        List<Player> greenTeamPlayers = this.createGreenTeamPlayers();
        Team greenTeam = this.createGreenTeam(greenTeamPlayers);
        List<Team> teams = new ArrayList<>();
        teams.add(blackTeam);
        teams.add(greenTeam);

        MatchesStatistics matchesStatistics = new MatchesStatistics(teams);
        MatchSimulator matchSimulator = new MatchSimulator(
                new MatchTeam(blackTeam),
                new MatchTeam(greenTeam),
                LocalDateTime.now());

        matchSimulator.run(matchesStatistics);
        System.out.println("a");
    }

    private List<Player> createBlackTeamPlayers() {
        Player goalkeeper = Player.CreateAsGoalkeeper("Jorge", 30, 195, 90);
        Player defenderOne = Player.CreateAsDefender("Alemão", 27, 75, 80);
        Player defenderTwo = Player.CreateAsDefender("João", 22, 85, 75);
        Player strikerOne = Player.CreateAsStriker("Euzebio", 33, 70, 85);
        Player strikerTwo = Player.CreateAsStriker("Romero", 21, 95, 85);

        return List.of(goalkeeper, defenderOne, defenderTwo, strikerOne, strikerTwo);
    }

    private Team createBlackTeam(List<Player> players) {
        Team blackTeam = new Team("Black FC");
        int shirtNumber = 1;

        for (Player player : players) {
            blackTeam.addPlayer(player, shirtNumber);
        }

        return blackTeam;
    }

    private List<Player> createGreenTeamPlayers() {
        Player goalkeeper = Player.CreateAsGoalkeeper("Arnaldo", 33, 189, 80);
        Player defenderOne = Player.CreateAsDefender("Igor", 29, 65, 70);
        Player defenderTwo = Player.CreateAsDefender("Gerson", 20, 70, 65);
        Player strikerOne = Player.CreateAsStriker("Renato", 35, 60, 80);
        Player strikerTwo = Player.CreateAsStriker("Eduardo", 20, 75, 75);

        return List.of(goalkeeper, defenderOne, defenderTwo, strikerOne, strikerTwo);
    }

    private Team createGreenTeam(List<Player> players) {
        Team blackTeam = new Team("Green FC");
        int shirtNumber = 1;

        for (Player player : players) {
            blackTeam.addPlayer(player, shirtNumber);
        }

        return blackTeam;
    }
}
