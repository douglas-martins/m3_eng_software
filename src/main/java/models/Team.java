package models;

import lombok.Data;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Data
public class Team implements Comparable<Team> {

    private String name;
    private List<Player> players;
    private List<Match> matches;
    private Integer points;

    public Team(String name) {
        this.name = name;
        this.points = 0;
        this.players = new ArrayList<>();
        this.matches = new ArrayList<>();
    }

    public void addPlayer(Player player, Integer shirtNumber) {
        player.setShirtNumber(this.getPlayerShirtNumber(shirtNumber));
        this.players.add(player);
    }

    public void addPoints(PointsType pointsType) {
        this.points += pointsType.getValue();
    }

    public void addMatches(Match match) {
        this.matches.add(match);
    }

    public Integer getTeamPower() {
        return this.players.stream().map(Player::getAbility).reduce(0, Integer::sum);
    }

    public List<Player> getDefenders() {
        return getPlayerByPosition(Defender.class);
    }

    public List<Player> getStrikers() {
        return getPlayerByPosition(Striker.class);
    }

    public Player getGoalkeeper() {
        return getPlayerByPosition(Goalkeeper.class).stream().findFirst().orElseThrow(TeamNoGoalkeeperException::new);
    }

    private List<Player> getPlayerByPosition(Class<?> position){
        return this.players.stream()
                .filter(player -> player.getPosition().getClass().equals(position))
                .collect(Collectors.toList());
    }

    public Player getPlayer(Integer shirtNumber) {
        return this.players.stream()
                .filter(player -> player.getShirtNumber().equals(shirtNumber))
                .findFirst()
                .orElse(null);
    }

    public void resetPlayersCurrentGoals() {
        this.players.forEach(Player::resetCurrentMatchGoals);
    }

    private Integer getPlayerShirtNumber(Integer shirtNumber) {
        Player hasPlayer = getPlayer(shirtNumber);

        return hasPlayer != null ? shirtNumber : this.getFirstShirtNumberAvailable();
    }

    private Integer getFirstShirtNumberAvailable() {
        List<Integer> numbers = this.players.stream().map(Player::getShirtNumber).collect(Collectors.toList());
        return this.getRandomShirtNumberAvailable(numbers);
    }

    private Integer getRandomShirtNumberAvailable(List<Integer> numbers) {
        Random rand = new Random();
        int range = 1;
        int random;

        do{
            random = rand.nextInt(range) + 1;
        }while (numbers.contains(random));

        return random;
    }

    @Override
    public int compareTo(Team o) {
        return this.getPoints().compareTo(o.getPoints());
    }
}
