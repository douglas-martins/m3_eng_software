package models;

import lombok.Data;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Getter
public class MatchesStatics {

    private List<Match> matches;
    private List<Team> standing;
    private List<Player> topScores;

    public MatchesStatics(List<Team> teams) {
        this.standing = teams;
        this.topScores = new ArrayList<>();
        this.matches = new ArrayList<>();
    }

    public void addMatch(Match match) {
        this.matches.add(match);

        this.refreshStanding(match);
        this.refreshTopScores(match);
    }

    private void refreshStanding(Match match) {
        this.addTeamPoints(match);

        Collections.sort(this.standing);
        Collections.sort(this.topScores);
    }

    private void refreshTopScores(Match match) {
        for (MatchTeam matchTeam : match.getTeams()) {
            matchTeam.getTeam().getPlayers().stream().filter(player -> player.getCurrentMatchGoals() > 0).forEach(player -> addt);
        }
    }

    private void addTeamPoints(Match match) {
        Team homeTeam = match.getTeam(true);
        Team awayTeam = match.getTeam(false);

        if (match.getWinningTeam() == null) {
            homeTeam.addPoints(PointsType.DRAW);
            awayTeam.addPoints(PointsType.DRAW);
        } else {
            match.getWinningTeam().getTeam().addPoints(PointsType.WIN);
        }
    }

    private void updatePlayerScored() {
    }
}
