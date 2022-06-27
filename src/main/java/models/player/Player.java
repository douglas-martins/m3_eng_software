package models.player;

import lombok.Getter;

import java.util.Objects;

@Getter
public class Player implements Comparable<Player> {

    private Position position;
    private final String name;
    private Integer ability;
    private Integer age;
    private Integer shirtNumber;
    private Integer goals;
    private Integer currentMatchGoals;

    private Player(String name, Integer age) {
        this.name = name;
        this.age = age;
        this.shirtNumber = 0;
        this.goals = 0;
        this.currentMatchGoals = 0;
    }

    public static Player CreateAsGoalkeeper(String name, Integer age, Integer height, Integer reflex){
        Player player = new Player(name, age);
        player.setPosition(new Goalkeeper(height, reflex));
        return player;
    }
    public static Player CreateAsDefender(String name, Integer age, Integer cover, Integer disarm){
        Player player = new Player(name, age);
        player.setPosition(new Defender(cover, disarm));
        return player;
    }
    public static Player CreateAsStriker(String name, Integer age, Integer velocity, Integer technique){
        Player player = new Player(name, age);
        player.setPosition(new Striker(velocity, technique));
        return player;
    }

    public void setPosition(Position position) {
        this.position = position;
        this.setAbility();
    }

    public void setAbility() {
        this.ability = this.position.calculateAbility();
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Player)) return false;
        Player player = (Player) o;
        return getPosition().equals(player.getPosition())
                && getName().equals(player.getName())
                && getAbility().equals(player.getAbility())
                && getAge().equals(player.getAge())
                && getShirtNumber().equals(player.getShirtNumber())
                && getGoals().equals(player.getGoals())
                && getCurrentMatchGoals().equals(player.getCurrentMatchGoals());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPosition(),
                getName(),
                getAbility(),
                getAge(),
                getShirtNumber(),
                getGoals(),
                getCurrentMatchGoals()
        );
    }
}
