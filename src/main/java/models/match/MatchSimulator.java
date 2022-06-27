package models.match;

import models.player.Player;
import models.team.Team;
import models.team.TeamStatistics;

import java.time.LocalDateTime;
import java.util.*;

public class MatchSimulator {

    private final int ROUNDS = 10;
    private final double HOME_TEAM_PLUS = 1.25;

    private Match match;

    public MatchSimulator(MatchTeam firstTeam, MatchTeam secondTeam, LocalDateTime date) {
        this.match = new Match(firstTeam, secondTeam, date);

        this.chooseHomeAndAwayTeam();
    }

    public void run(MatchesStatistics matchesStatistics, List<TeamStatistics> teamsStatistics) {
        boolean isHomeTeamAttacking = true;
        MatchTeam homeMatchTeam = this.match.getMatchTeam(true);
        MatchTeam awayMatchTeam = this.match.getMatchTeam(false);

        for (int i = 0; i < ROUNDS; i++) {
            if (isHomeTeamAttacking) {
                this.simulateRound(homeMatchTeam.getTeam(), awayMatchTeam.getTeam(), isHomeTeamAttacking);
            } else {
                this.simulateRound(awayMatchTeam.getTeam(), homeMatchTeam.getTeam(), isHomeTeamAttacking);
            }

            isHomeTeamAttacking = !isHomeTeamAttacking;
        }

        this.match.setWinningTeam();
        teamsStatistics.forEach(teamStatistics -> teamStatistics.addMatches(this.match));
        matchesStatistics.addMatch(this.match);

        homeMatchTeam.getTeam().resetPlayersCurrentGoals();
        awayMatchTeam.getTeam().resetPlayersCurrentGoals();

        System.out.println(homeMatchTeam);
        System.out.println(awayMatchTeam);
    }

    private void chooseHomeAndAwayTeam() {
        Random random = new Random();
        int homeIndex = random.nextInt(2);
        int awayIndex = homeIndex == 1 ? 0 : 1;

        this.match.getTeams()[homeIndex].setIsHome(true);
        this.match.getTeams()[awayIndex].setIsHome(false);
    }

    private void simulateRound(Team attackingTeam, Team defenderTeam, boolean isHomeTeamAttacking) {
        double attackingTeamPower = attackingTeam.getTeamPower().doubleValue();
        double defenderTeamPower = defenderTeam.getTeamPower().doubleValue();

        this.addHomeTeamAdvantage(attackingTeamPower, defenderTeamPower, isHomeTeamAttacking);

        double roundPower = attackingTeamPower + defenderTeamPower;
        double attackingOdds = (double) ((attackingTeamPower * 100) / roundPower);
        Random random = new Random();
        int result = random.nextInt(100);


        if (result <= attackingOdds) {
            Player goalkeeper = defenderTeam.getGoalkeeper();
            Player shooterPlayer = this.simulateGoalScored(attackingTeam);

            if (this.simulateGoalkeeperSave(goalkeeper, shooterPlayer, isHomeTeamAttacking)) {
                shooterPlayer.addGoal();
                shooterPlayer.addCurrentMatchGoal();

                this.match.getMatchTeam(isHomeTeamAttacking).addGoal();
            }
        }
    }

    private Boolean simulateGoalkeeperSave(Player goalkeeper, Player shooter, boolean isHomeTeamShooting) {
        double goalkeeperPower = goalkeeper.getAbility().doubleValue();
        double shooterPower = shooter.getAbility().doubleValue();

        this.addHomeTeamAdvantage(shooterPower, goalkeeperPower, isHomeTeamShooting);

        double confrontationPower = shooterPower + goalkeeperPower;
        double scoringOdds = (double) ((shooterPower * 100) / confrontationPower);
        Random random = new Random();
        int result = random.nextInt(100);

        return result <= scoringOdds;
    }

    private Player simulateGoalScored(Team team) {
        List<Player> players = new ArrayList<>(team.getStrikers());
        players.addAll(team.getDefenders());

        Collections.shuffle(players);

        return players.get(0);
    }

    private void addHomeTeamAdvantage(double attackingValue,
                                             double defendingValue,
                                             boolean isHomeTeamAttacking) {
        if (isHomeTeamAttacking) {
            attackingValue *= HOME_TEAM_PLUS;
        } else {
            defendingValue *= HOME_TEAM_PLUS;
        }
    }
}
