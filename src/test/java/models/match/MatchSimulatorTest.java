package models.match;

import models.Team;
import models.player.Player;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MatchSimulatorTest {

    @Test
    public void shouldRunSimulation(){
        Team blueTeam = new Team("Blue Team");
        blueTeam.setPlayers(List.of(Player.CreateAsGoalkeeper("Gabriel", 22, 183, 89),
                Player.CreateAsDefender("Alex", 21, 80, 70),
                Player.CreateAsDefender("David", 22, 76, 79),
                Player.CreateAsStriker("Evandro", 28, 69, 82),
                Player.CreateAsStriker("Nene", 18, 90, 60)
                ));
        Team greenTeam = new Team("Green Team");
        greenTeam.setPlayers(List.of(Player.CreateAsGoalkeeper("Gabriel", 22, 183, 89),
                Player.CreateAsDefender("Alex", 21, 80, 70),
                Player.CreateAsDefender("David", 22, 76, 79),
                Player.CreateAsStriker("Evandro", 28, 69, 82),
                Player.CreateAsStriker("Nene", 18, 90, 60)
        ));
        Team redTeam = new Team("Red Team");
        redTeam.setPlayers(List.of(Player.CreateAsGoalkeeper("Gabriel", 22, 196, 90),
                Player.CreateAsDefender("Ale", 23, 84, 73),
                Player.CreateAsDefender("Rachilson", 27, 87, 90),
                Player.CreateAsStriker("Enzo", 29, 78, 90),
                Player.CreateAsStriker("Luiz", 22, 80, 81)
        ));
        Team blackTeam = new Team("Black Team");
        blackTeam.setPlayers(List.of(Player.CreateAsGoalkeeper("Jorge", 30, 195, 90),
                Player.CreateAsDefender("Alemão", 27, 75, 80),
                Player.CreateAsDefender("João", 22, 85, 75),
                Player.CreateAsStriker("Euzebio", 33, 70, 85),
                Player.CreateAsStriker("Romero", 21, 95, 85)));

        List<Team> teams = new ArrayList<>();
        teams.add(blueTeam);
        teams.add(greenTeam);
        teams.add(redTeam);
        teams.add(blackTeam);
        MatchesStatistics matchesStatistics = new MatchesStatistics(teams);
        MatchSimulator matchSimulator = new MatchSimulator(
                new MatchTeam(blueTeam),
                new MatchTeam(greenTeam),
                LocalDateTime.now());
        matchSimulator.run(matchesStatistics);
    }
}
