package models.match;

import models.player.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PlayerTest {

    private Player goalkeeper;
    private Player defender;
    private Player striker;

    @BeforeEach
    public void Player(){
        goalkeeper = Player.CreateAsGoalkeeper("Goalkeeper", 20, 196, 90);
        defender = Player.CreateAsDefender("Defender", 23, 89, 95);
        striker = Player.CreateAsStriker("Striker", 18, 98, 81);
    }

    @Test
    public void shouldValidateTheMaxAbilityScoreGoalkeeper(){
        Player goalkeeperMaxAbility = Player.CreateAsGoalkeeper("Goalkeeper", 18, Integer.MAX_VALUE, Integer.MAX_VALUE);

        Assertions.assertEquals(100, goalkeeperMaxAbility.getAbility(), "Should return the max ability score possible (100)");
    }

    @Test
    public void shouldValidateTheMaxAbilityScoreDefender(){
        Player defenderMaxAbility = Player.CreateAsDefender("Defender", 18, Integer.MAX_VALUE, Integer.MAX_VALUE);

        Assertions.assertEquals(100, defenderMaxAbility.getAbility(), "Should return the max ability score possible (100)");
    }

    @Test
    public void shouldValidateTheMaxAbilityScoreStriker(){
        Player strikerMaxAbility = Player.CreateAsStriker("Striker", 18, Integer.MAX_VALUE, Integer.MAX_VALUE);

        Assertions.assertEquals(100, strikerMaxAbility.getAbility(), "Should return the max ability score possible (100)");
    }

    @Test
    public void shouldValidateTheMinAbilityScoreGoalkeeper(){
        Player goalkeeperMinAbility = Player.CreateAsGoalkeeper("Goalkeeper", 18, Integer.MIN_VALUE, Integer.MIN_VALUE);

        Assertions.assertEquals(0, goalkeeperMinAbility.getAbility(), "Should return the minimum ability score possible (0)");
    }

    @Test
    public void shouldValidateTheMinAbilityScoreDefender(){
        Player defenderMinAbility = Player.CreateAsDefender("Defender", 18, Integer.MIN_VALUE, Integer.MIN_VALUE);

        Assertions.assertEquals(0, defenderMinAbility.getAbility(), "Should return the minimum ability score possible (0)");
    }

    @Test
    public void shouldValidateTheMinAbilityScoreStriker(){
        Player strikerMinAbility = Player.CreateAsStriker("Striker", 18, Integer.MIN_VALUE, Integer.MIN_VALUE);

        Assertions.assertEquals(0, strikerMinAbility.getAbility(), "Should return the minimum ability score possible (0)");
    }
}
