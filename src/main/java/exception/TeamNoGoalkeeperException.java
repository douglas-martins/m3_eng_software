package exception;

public class TeamNoGoalkeeperException extends RuntimeException {

    public TeamNoGoalkeeperException() {
        super("Can not find a goalkeeper on this team");
    }
}
