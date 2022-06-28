package models.match;

import lombok.Getter;
import models.PointsType;
import models.player.Player;
import models.team.Team;
import models.team.TeamStatistics;

import java.lang.ref.Reference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
public class MatchesStatistics {

    private List<Match> matches;
    private List<TeamStatistics> standing;
    private List<Player> topScores;

    public MatchesStatistics(List<TeamStatistics> teams) {
        this.standing = teams;
        this.topScores = new ArrayList<>();
        this.matches = new ArrayList<>();
    }

    public void addMatch(Match match) {
        this.matches.add(match);

        this.refreshStanding(match);
        this.refreshTopScores(match);
        this.refreshTeamStatistics(match);
    }

    private void refreshStanding(Match match) {
        Team homeTeam = match.getTeam(true);
        Team awayTeam = match.getTeam(false);

        if (match.getWinningTeam() == null) {
            TeamStatistics homeTeamStatistics = this.getTeamPlayed(homeTeam);
            TeamStatistics awayTeamStatistics = this.getTeamPlayed(awayTeam);

            homeTeamStatistics.addPoints(PointsType.DRAW);
            awayTeamStatistics.addPoints(PointsType.DRAW);
        } else {
            TeamStatistics winningTeamStatistics = this.getTeamPlayed(match.getWinningTeam().getTeam());

            winningTeamStatistics.addPoints(PointsType.WIN);
        }
    }

    private void refreshTopScores(Match match) {
        if (this.topScores.isEmpty()) {
            for (MatchTeam matchTeam : match.getTeams()) {
                matchTeam.getTeam().getPlayers()
                        .stream()
                        .filter(player -> player.getCurrentMatchGoals() > 0)
                        .forEach(this::updatePlayerScored);
            }
        }

        this.topScores.sort(Collections.reverseOrder());
    }

    private void refreshTeamStatistics(Match match) {
        MatchTeam homeMatchTeam = match.getMatchTeam(true);
        MatchTeam awayMatchTeam = match.getMatchTeam(false);
        TeamStatistics homeTeamStatistics = this.getTeamPlayed(homeMatchTeam);
        TeamStatistics awayTeamStatistics = this.getTeamPlayed(awayMatchTeam);

        homeTeamStatistics.addGoalsScored(homeMatchTeam.getGoals());
        homeTeamStatistics.addConcededGoals(awayMatchTeam.getGoals());

        awayTeamStatistics.addGoalsScored(awayMatchTeam.getGoals());
        awayTeamStatistics.addConcededGoals(homeMatchTeam.getGoals());

        this.standing.sort(Collections.reverseOrder());
    }

    private void updatePlayerScored(Player player) {
        this.topScores.add(player);
    }

    private TeamStatistics getTeamPlayed(Team team) {
        return this.standing.stream().filter(t -> t.getTeam().equals(team)).findFirst().orElse(null);
    }

    private TeamStatistics getTeamPlayed(MatchTeam matchTeam) {
        return this.standing.stream()
                .filter(t -> t.getTeam().equals(matchTeam.getTeam()))
                .findFirst()
                .orElse(null);
    }

    private TeamStatistics getTeamByPlayer(Player player) {
        return this.standing.stream()
                .filter(t -> t.getTeam().getPlayer(player.getShirtNumber()).equals(player))
                .findFirst()
                .orElse(null);
    }

    public List<Team> getStandingByTeamPower(){
        List<Team> teamPowerStanding = new ArrayList<>();
        for (TeamStatistics t : standing){
            teamPowerStanding.add(t.getTeam());
        }
        teamPowerStanding.sort(Team::compareTeamPower);
        return teamPowerStanding;
    }
}
