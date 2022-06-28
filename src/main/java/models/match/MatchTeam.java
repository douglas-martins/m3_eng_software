package models.match;

import lombok.Data;
import models.team.Team;

@Data
public class MatchTeam implements Cloneable {

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

    public void resetGoals() {
        this.goals = 0;
    }

    @Override
    public MatchTeam clone() {
        try {
            MatchTeam clone = (MatchTeam) super.clone();
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
