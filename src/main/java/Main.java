import models.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
       Main main = new Main();

       main.testMatchSimulator();
        System.out.println("a");
    }

    private void testMatchSimulator() {
        List<Player> blackTeamPlayers = this.createBlackTeamPlayers();
        Team blackTeam = this.createBlackTeam(blackTeamPlayers);
        List<Player> greenTeamPlayers = this.createGreenTeamPlayers();
        Team greenTeam = this.createGreenTeam(greenTeamPlayers);
        List<Team> teams = new ArrayList<>();
        teams.add(blackTeam);
        teams.add(greenTeam);

        MatchesStatics matchesStatics = new MatchesStatics(teams);
        MatchSimulator matchSimulator = new MatchSimulator(
                new MatchTeam(blackTeam),
                new MatchTeam(greenTeam),
                LocalDateTime.now());

        matchSimulator.run(matchesStatics);
    }

    private List<Player> createBlackTeamPlayers() {
        Player goalkeeper = new Goalkeeper("Jorge", 30, 1.95, 90);
        Player defenderOne = new Defender("Alemão", 27, 75, 80);
        Player defenderTwo = new Defender("João", 22, 85, 75);
        Player strikerOne = new Striker("Euzebio", 33, 70, 85);
        Player strikerTwo = new Striker("Romero", 21, 95, 85);

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
        Player goalkeeper = new Goalkeeper("Arnaldo", 33, 1.89, 80);
        Player defenderOne = new Defender("Igor", 29, 65, 70);
        Player defenderTwo = new Defender("Gerson", 20, 70, 65);
        Player strikerOne = new Striker("Renato", 35, 60, 80);
        Player strikerTwo = new Striker("Eduardo", 20, 75, 75);

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
