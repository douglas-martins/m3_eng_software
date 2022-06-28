package models.match;

import models.player.Player;
import models.team.Team;
import models.team.TeamStatistics;
import org.junit.jupiter.api.Assertions;
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

        List<TeamStatistics> teamStatistics = new ArrayList<>();
        teamStatistics.add(new TeamStatistics(blueTeam));
        teamStatistics.add(new TeamStatistics(greenTeam));
        MatchesStatistics matchesStatistics = new MatchesStatistics(teamStatistics);
        matchesStatistics.addMatch(match1);
        matchesStatistics.addMatch(match2);

        assertNotNull(matchesStatistics.getMatches());
        assertEquals(match1, matchesStatistics.getMatches().get(0));
        assertEquals(match2, matchesStatistics.getMatches().get(1));
    }

    @Test
    public void shouldRankTeamsCorrectlyByRules() {
        Team blueTeam = createBlueTeam();
        Team greenTeam = createGreenTeam();
        Team redTeam = createRedTeam();
        Team blackTeam = createBlackTeam();

        MatchTeam blueMatchTeam;
        MatchTeam greenMatchTeam;
        MatchTeam redMatchTeam;
        MatchTeam blackMatchTeam;

        List<TeamStatistics> teamStatistics = new ArrayList<>();
        teamStatistics.add(new TeamStatistics(blueTeam));
        teamStatistics.add(new TeamStatistics(greenTeam));
        teamStatistics.add(new TeamStatistics(redTeam));
        teamStatistics.add(new TeamStatistics(blackTeam));

        MatchesStatistics matchesStatistics = new MatchesStatistics(teamStatistics);

        blueMatchTeam = createMatchTeam(blueTeam);
        greenMatchTeam = createMatchTeam(greenTeam);

        Match matchOne = createMatch(blueMatchTeam, greenMatchTeam);
        matchOne.getTeams()[0].setIsHome(true);
        matchOne.getTeams()[1].setIsHome(false);

        matchOne.getTeam(true).getPlayers().get(4).addGoal();
        matchOne.getTeam(true).getPlayers().get(4).addCurrentMatchGoal();
        matchOne.getTeam(true).getPlayers().get(4).addGoal();
        matchOne.getTeam(true).getPlayers().get(4).addCurrentMatchGoal();
        matchOne.getTeam(false).getPlayers().get(4).addGoal();
        matchOne.getTeam(false).getPlayers().get(4).addCurrentMatchGoal();
        matchOne.getTeam(false).getPlayers().get(4).addGoal();
        matchOne.getTeam(false).getPlayers().get(4).addCurrentMatchGoal();

        matchOne.getMatchTeam(true).addGoal();
        matchOne.getMatchTeam(true).addGoal();
        matchOne.getMatchTeam(false).addGoal();
        matchOne.getMatchTeam(false).addGoal();

        matchOne.setWinningTeam();
        matchesStatistics.addMatch(matchOne);

        matchOne.getTeam(true).resetPlayersCurrentGoals();
        matchOne.getTeam(false).resetPlayersCurrentGoals();

        blueMatchTeam = createMatchTeam(blueTeam);
        redMatchTeam = createMatchTeam(redTeam);

        Match matchTwo = createMatch(blueMatchTeam, redMatchTeam);
        matchTwo.getTeams()[0].setIsHome(false);
        matchTwo.getTeams()[1].setIsHome(true);

        matchTwo.getTeam(true).getPlayers().get(4).addGoal();
        matchTwo.getTeam(true).getPlayers().get(4).addCurrentMatchGoal();
        matchTwo.getTeam(false).getPlayers().get(4).addGoal();
        matchTwo.getTeam(false).getPlayers().get(4).addCurrentMatchGoal();
        matchTwo.getTeam(false).getPlayers().get(4).addGoal();
        matchTwo.getTeam(false).getPlayers().get(4).addCurrentMatchGoal();

        matchTwo.getMatchTeam(true).addGoal();
        matchTwo.getMatchTeam(false).addGoal();
        matchTwo.getMatchTeam(false).addGoal();

        matchTwo.setWinningTeam();
        matchesStatistics.addMatch(matchTwo);

        matchTwo.getTeam(true).resetPlayersCurrentGoals();
        matchTwo.getTeam(false).resetPlayersCurrentGoals();

        blueMatchTeam = createMatchTeam(blueTeam);
        blackMatchTeam = createMatchTeam(blackTeam);

        Match matchThree = createMatch(blueMatchTeam, blackMatchTeam);
        matchThree.getTeams()[0].setIsHome(false);
        matchThree.getTeams()[1].setIsHome(true);

        matchThree.getTeam(true).getPlayers().get(4).addGoal();
        matchThree.getTeam(true).getPlayers().get(4).addCurrentMatchGoal();
        matchThree.getTeam(false).getPlayers().get(4).addGoal();
        matchThree.getTeam(false).getPlayers().get(4).addCurrentMatchGoal();

        matchThree.getMatchTeam(true).addGoal();
        matchThree.getMatchTeam(false).addGoal();

        matchThree.setWinningTeam();
        matchesStatistics.addMatch(matchThree);

        matchThree.getTeam(true).resetPlayersCurrentGoals();
        matchThree.getTeam(false).resetPlayersCurrentGoals();

        redMatchTeam = createMatchTeam(redTeam);
        greenMatchTeam = createMatchTeam(greenTeam);

        Match matchFour = createMatch(redMatchTeam, greenMatchTeam);
        matchFour.getTeams()[0].setIsHome(true);
        matchFour.getTeams()[1].setIsHome(false);

        matchFour.getTeam(true).getPlayers().get(4).addGoal();
        matchFour.getTeam(true).getPlayers().get(4).addCurrentMatchGoal();

        matchFour.getMatchTeam(true).addGoal();

        matchFour.setWinningTeam();
        matchesStatistics.addMatch(matchFour);

        matchFour.getTeam(true).resetPlayersCurrentGoals();
        matchFour.getTeam(false).resetPlayersCurrentGoals();

        redMatchTeam = createMatchTeam(redTeam);
        blackMatchTeam = createMatchTeam(blackTeam);

        Match matchFive = createMatch(redMatchTeam, blackMatchTeam);
        matchFive.getTeams()[0].setIsHome(true);
        matchFive.getTeams()[1].setIsHome(false);

        matchFive.getTeam(false).getPlayers().get(4).addGoal();
        matchFive.getTeam(false).getPlayers().get(4).addCurrentMatchGoal();
        matchFive.getTeam(false).getPlayers().get(4).addGoal();
        matchFive.getTeam(false).getPlayers().get(4).addCurrentMatchGoal();

        matchFive.getMatchTeam(false).addGoal();
        matchFive.getMatchTeam(false).addGoal();

        matchFive.setWinningTeam();
        matchesStatistics.addMatch(matchFive);

        matchFive.getTeam(true).resetPlayersCurrentGoals();
        matchFive.getTeam(false).resetPlayersCurrentGoals();

        greenMatchTeam = createMatchTeam(greenTeam);
        blackMatchTeam = createMatchTeam(blackTeam);

        Match matchSix = createMatch(greenMatchTeam, blackMatchTeam);
        matchSix.getTeams()[0].setIsHome(false);
        matchSix.getTeams()[1].setIsHome(true);

        matchSix.getTeam(true).getPlayers().get(4).addGoal();
        matchSix.getTeam(true).getPlayers().get(4).addCurrentMatchGoal();
        matchSix.getTeam(true).getPlayers().get(4).addGoal();
        matchSix.getTeam(true).getPlayers().get(4).addCurrentMatchGoal();

        matchSix.getMatchTeam(true).addGoal();
        matchSix.getMatchTeam(true).addGoal();

        matchSix.setWinningTeam();
        matchesStatistics.addMatch(matchSix);

        matchSix.getTeam(true).resetPlayersCurrentGoals();
        matchSix.getTeam(false).resetPlayersCurrentGoals();

        Assertions.assertEquals(teamStatistics.get(0), matchesStatistics.getStanding().get(0),
                "Should return Black Team, as leader after matches played");
        Assertions.assertEquals(teamStatistics.get(1), matchesStatistics.getStanding().get(1),
                "Should return Blue Team, as second place after matches played");
        Assertions.assertEquals(teamStatistics.get(2), matchesStatistics.getStanding().get(2),
                "Should return Red Team, as leader third place matches played");
        Assertions.assertEquals(teamStatistics.get(0), matchesStatistics.getStanding().get(0),
                "Should return Green Team, as last matches played");
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

    private Team createBlackTeam(){
        Team blackTeam = new Team("Black Team");
        blackTeam.setPlayers(List.of(Player.CreateAsGoalkeeper("Jorge", 30, 195, 90),
                Player.CreateAsDefender("Alemão", 27, 75, 80),
                Player.CreateAsDefender("João", 22, 85, 75),
                Player.CreateAsStriker("Euzebio", 33, 70, 85),
                Player.CreateAsStriker("Romero", 21, 95, 85)
        ));

        return blackTeam;
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

        List<TeamStatistics> teamStatistics = new ArrayList<>();
        teamStatistics.add(new TeamStatistics(blueTeam));
        teamStatistics.add(new TeamStatistics(greenTeam));
        MatchesStatistics matchesStatistics = new MatchesStatistics(teamStatistics);

        assertEquals(blueTeam.getName(),matchesStatistics.getStandingByTeamPower().get(0).getName());
    }
}