package models.match;

import models.Team;
import models.player.Player;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class MatchesStatisticsTest {

    @Test
    public void shouldAddMatch() {
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

        MatchTeam matchTeam1 = new MatchTeam(blueTeam);
        matchTeam1.setIsHome(true);
        MatchTeam matchTeam2 = new MatchTeam(greenTeam);
        matchTeam2.setIsHome(false);
        MatchTeam matchTeam3 = new MatchTeam(redTeam);
        matchTeam3.setIsHome(true);
        Match match1 = new Match(matchTeam1, matchTeam2, LocalDateTime.now());
        Match match2 = new Match(matchTeam3, matchTeam2, LocalDateTime.now());
        List<Team> teams = new ArrayList<>();
        teams.add(blueTeam);
        teams.add(greenTeam);
        teams.add(redTeam);
        MatchesStatistics matchesStatistics = new MatchesStatistics(teams);
        matchesStatistics.addMatch(match1);
        matchesStatistics.addMatch(match2);

        assertNotNull(matchesStatistics.getMatches());
        assertEquals(match1, matchesStatistics.getMatches().get(0));
        assertEquals(match2, matchesStatistics.getMatches().get(1));
    }
}