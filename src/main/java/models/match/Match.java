package models.match;

import exception.MatchNoHomeTeamException;
import lombok.Getter;
import models.team.Team;

import java.time.LocalDateTime;
import java.util.Arrays;

@Getter
public class Match implements Cloneable {

    private MatchTeam[] teams = new MatchTeam[2];
    private LocalDateTime date;
    private MatchTeam winningTeam;

    public Match(MatchTeam firstTeam, MatchTeam secondTeam, LocalDateTime date) {
        this.teams = new MatchTeam[] {firstTeam.clone(), secondTeam.clone()};
        this.date = date;
    }

    public Team getTeam(Boolean isHome) {
        return getMatchTeam(isHome).getTeam();
    }

    public MatchTeam getMatchTeam(Boolean isHome){
        return Arrays.stream(this.teams)
                .filter(matchTeam -> matchTeam.getIsHome() == isHome)
                .findFirst()
                .orElseThrow(MatchNoHomeTeamException::new);
    }

    public void setWinningTeam() {
        MatchTeam homeMatchTeam = this.getMatchTeam(true);
        MatchTeam awayMatchTeam = this.getMatchTeam(false);

        if (homeMatchTeam.getGoals() > awayMatchTeam.getGoals()) {
            this.winningTeam = homeMatchTeam;
        } else if (homeMatchTeam.getGoals().equals(awayMatchTeam.getGoals())) {
            this.winningTeam = null;
        } else {
            this.winningTeam = awayMatchTeam;
        }
    }

    @Override
    public Match clone() {
        try {
            Match clone = (Match) super.clone();
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
