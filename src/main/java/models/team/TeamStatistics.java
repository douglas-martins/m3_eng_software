package models.team;

import lombok.Getter;
import models.PointsType;
import models.match.Match;

import java.util.ArrayList;
import java.util.List;

@Getter
public class TeamStatistics implements Comparable<TeamStatistics> {

    private Team team;
    private Integer goalsScored;
    private Integer concededGoals;
    private Integer points;
    private Integer goalDifference;
    private List<Match> matches;

    public TeamStatistics(Team team) {
        this.team = team;
        this.goalsScored = 0;
        this.concededGoals = 0;
        this.points = 0;
        this.goalDifference = 0;
        this.matches = new ArrayList<>();
    }

    public void addMatches(Match match) {
        this.matches.add(match);
    }

    public void addPoints(PointsType pointsType) {
        this.points += pointsType.getValue();
    }

    public void addGoalsScored(Integer goals) {
        this.goalsScored += goals;
        this.updateGoalDifference();
    }

    public void addConcededGoals(Integer goals) {
        this.concededGoals += goals;
        this.updateGoalDifference();
    }

    private void updateGoalDifference() {
        this.goalDifference = this.goalsScored - this.concededGoals;
    }

    @Override
    public int compareTo(TeamStatistics o) {
        return this.getPoints().compareTo(o.getPoints());
    }

    @Override
    public String toString() {
        return "TeamStatistics{" +
                "team=" + team +
                ", goalsScored=" + goalsScored +
                ", concededGoals=" + concededGoals +
                ", goalDifference=" + goalDifference +
                ", points=" + points +
                ", matches=" + matches +
                '}';
    }
}
