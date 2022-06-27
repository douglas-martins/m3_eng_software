package models;

import lombok.Getter;

@Getter
public abstract class Player implements Comparable<Player> {

    private final String name;
    private Integer ability;
    private Integer age;
    private Integer shirtNumber;
    private Integer goals;
    private Integer currentMatchGoals;

    public Player(String name, Integer age) {
        this.name = name;
        this.age = age;
        this.shirtNumber = 0;
        this.goals = 0;
        this.currentMatchGoals = 0;
    }

    protected abstract Integer calculateAbility();

    public void setAbility() {
        this.ability = this.calculateAbility();
    }

    public void setShirtNumber(Integer number) {
        this.shirtNumber = number;
    }

    public void addGoal() {
        this.goals += 1;
    }

    public void addCurrentMatchGoal() {
        this.currentMatchGoals += 1;
    }

    public void resetCurrentMatchGoals() {
        this.currentMatchGoals = 0;
    }

    @Override
    public int compareTo(Player o) {
        return this.goals.compareTo(o.getGoals());
    }
}
