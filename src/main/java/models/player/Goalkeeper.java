package models.player;

import lombok.Getter;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Getter
public class Goalkeeper extends Player {

    private static final Double MAX_HEIGHT = 2.1;

    private Double height;
    private Integer reflexes;

    public Goalkeeper(String name, Integer age, Double height, Integer reflexes) {
        super(name, age);
        this.height = height;
        this.reflexes = reflexes;

        this.setAbility();
    }

    @Override
    public Integer calculateAbility() {
        int heightFixed = 0;

        if (this.height >= MAX_HEIGHT) {
            heightFixed = 100;
        } else {
            heightFixed = this.calculateHeightAbilityValue();
        }

        return (heightFixed * 4) + (this.reflexes * 6);
    }

    private Integer calculateHeightAbilityValue() {
        BigDecimal result = new BigDecimal((this.height * 100) / MAX_HEIGHT)
                .setScale(0, RoundingMode.HALF_EVEN);
        return result.intValue();
    }
}
