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
        Team blueTeam = createBlueTeam();
        Team greenTeam = createGreenTeam();
        Team redTeam = createRedTeam();

        MatchTeam matchTeam1 = createMatchHomeTeam(blueTeam);
        MatchTeam matchTeam2 = createMatchTeam(greenTeam);
        MatchTeam matchTeam3 = createMatchHomeTeam(redTeam);
        Match match1 = createMatch(matchTeam1,matchTeam2);
        Match match2 = createMatch(matchTeam3, matchTeam2);
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

    private Team createRedTeam(){
        Team redTeam = new Team("Red Team");
        redTeam.setPlayers(List.of(Player.CreateAsGoalkeeper("Gabriel", 22, 196, 90),
                Player.CreateAsDefender("Ale", 23, 80, 70),
                Player.CreateAsDefender("Rachilson", 27, 100, 60),
                Player.CreateAsStriker("Enzo", 29, 60, 70),
                Player.CreateAsStriker("Luiz", 22, 80, 80)
        ));
        return redTeam;
    }

    private Match createMatch( MatchTeam matchTeam1,  MatchTeam matchTeam2){
        return new Match(matchTeam1, matchTeam2, LocalDateTime.now());
    }

    private MatchTeam createMatchHomeTeam(Team team){
        MatchTeam matchHomeTeam = new MatchTeam(team);
        matchHomeTeam.setIsHome(true);
        return matchHomeTeam;
    }

    private MatchTeam createMatchTeam(Team team){
        MatchTeam matchTeam = new MatchTeam(team);
        matchTeam.setIsHome(false);
        return matchTeam;
    }

    @Test
    public void shouldClassifyWithTeamPower(){
        Team blueTeam = createBlueTeam();
        Team greenTeam = createGreenTeam();
        Team redTeam = createRedTeam();

        List<Team> teams = new ArrayList<>();
        teams.add(blueTeam);
        teams.add(greenTeam);
        teams.add(redTeam);
        MatchesStatistics matchesStatistics = new MatchesStatistics(teams);

        assertEquals(blueTeam.getName(),matchesStatistics.getStandingByTeamPower().get(0).getName());
    }
}