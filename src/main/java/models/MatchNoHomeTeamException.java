package models;

public class MatchNoHomeTeamException extends RuntimeException {
    
    public MatchNoHomeTeamException() {
        super("Can not find a home team!");
    }
}
