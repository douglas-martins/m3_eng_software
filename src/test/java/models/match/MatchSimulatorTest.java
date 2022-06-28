package models.match;

import models.player.Player;
import models.team.Team;
import models.team.TeamStatistics;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MatchSimulatorTest {

    @Test
    public void shouldRunSimulation(){
        Team blueTeam = createBlueTeam();
        Team greenTeam = createGreenTeam();

        List<TeamStatistics> teamStatistics = new ArrayList<>();
        teamStatistics.add(new TeamStatistics(blueTeam));
        teamStatistics.add(new TeamStatistics(greenTeam));
        MatchesStatistics matchesStatistics = new MatchesStatistics(teamStatistics);
        MatchSimulator matchSimulator = new MatchSimulator(
                new MatchTeam(blueTeam),
                new MatchTeam(greenTeam),
                LocalDateTime.now());
        matchSimulator.run(matchesStatistics, teamStatistics);
    }

    private Team createBlueTeam(){
        Team blueTeam = new Team("Blue Team");
        blueTeam.setPlayers(List.of(Player.CreateAsGoalkeeper("Gabriel", 22, 183, 80),
                Player.CreateAsDefender("Alex", 21, 70, 80),
                Player.CreateAsDefender("David", 22, 60, 66),
                Player.CreateAsStriker("Evandro", 28, 69, 89),
                Player.CreateAsStriker("Nene", 18, 100, 80)
        ));
        return blueTeam;
    }

    private Team createGreenTeam(){
        Team greenTeam = new Team("Green Team");
        greenTeam.setPlayers(List.of(Player.CreateAsGoalkeeper("Gabriel", 22, 183, 89),
                Player.CreateAsDefender("Alex", 21, 80, 70),
                Player.CreateAsDefender("David", 22, 76, 79),
                Player.CreateAsStriker("Evandro", 28, 69, 82),
                Player.CreateAsStriker("Nene", 18, 90, 60)
        ));
        return greenTeam;
    }
}
