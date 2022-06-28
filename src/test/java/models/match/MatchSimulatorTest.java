package models.match;

import com.sun.jdi.Method;
import exception.MatchNoHomeTeamException;
import models.player.Player;
import models.team.Team;
import models.team.TeamStatistics;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MatchSimulatorTest {

    @Test
    public void shouldRunSimulation(){
        Team blueTeam = createBlueTeam();
        Team dummyTeam = createDummyTeam();

        List<TeamStatistics> teamStatistics = new ArrayList<>();
        teamStatistics.add(new TeamStatistics(blueTeam));
        teamStatistics.add(new TeamStatistics(dummyTeam));
        MatchesStatistics matchesStatistics = new MatchesStatistics(teamStatistics);
        MatchSimulator matchSimulator = new MatchSimulator(
                new MatchTeam(blueTeam),
                new MatchTeam(dummyTeam),
                LocalDateTime.now());
        Match result = matchSimulator.run(matchesStatistics, teamStatistics);

        assertEquals(blueTeam.getName(), result.getWinningTeam().getTeam().getName());
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

    private Team createDummyTeam(){
        Team dummyTeam = new Team("Dummy Team");
        dummyTeam.setPlayers(List.of(Player.CreateAsGoalkeeper("Gabriel", 22, 183, 0),
                Player.CreateAsDefender("Alex", 21, 0, 0),
                Player.CreateAsDefender("David", 22, 0, 0),
                Player.CreateAsStriker("Evandro", 28, 0, 0),
                Player.CreateAsStriker("Nene", 18, 0, 0)
        ));
        return dummyTeam;
    }

    @Test
    public void shouldThrowMatchNoHomeTeamException(){
        assertThrows(MatchNoHomeTeamException.class, () ->  new MatchSimulator(
                null,
                null,
                LocalDateTime.now()));
    }
}
