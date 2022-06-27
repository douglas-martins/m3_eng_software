package models.match;

import lombok.Data;
import models.Team;

import java.util.ArrayList;
import java.util.List;

@Data
public class MatchTeam {

    private Team team;
    private Boolean isHome;
    private Integer goals;

    public MatchTeam(Team team) {
        this.team = team;
        this.goals = 0;
    }

    public void addGoal() {
        this.goals += 1;
    }
}
