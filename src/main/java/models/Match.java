package models;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@Getter
public class Match {

    private MatchTeam[] teams = new MatchTeam[2];
    private LocalDateTime date;
    private MatchTeam winningTeam;

    public Match(MatchTeam firstTeam, MatchTeam secondTeam, LocalDateTime date) {
        this.teams = new MatchTeam[] {firstTeam, secondTeam};
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
}
