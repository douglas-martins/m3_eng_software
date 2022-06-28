package models.match;

import lombok.Getter;
import models.player.Player;
import models.PointsType;
import models.Team;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
public class MatchesStatistics {

    private List<Match> matches;
    private List<Team> standing;
    private List<Player> topScores;

    public MatchesStatistics(List<Team> teams) {
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
        Team homeTeam = match.getTeam(true);
        Team awayTeam = match.getTeam(false);

        if (match.getWinningTeam() == null) {
            homeTeam.addPoints(PointsType.DRAW);
            awayTeam.addPoints(PointsType.DRAW);
        } else {
            match.getWinningTeam().getTeam().addPoints(PointsType.WIN);
        }
        this.standing.sort(Collections.reverseOrder());
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

    private void updatePlayerScored(Player player) {
        this.topScores.add(player);
    }
}
