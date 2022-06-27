package models.player;

import lombok.Getter;

@Getter
public class Goalkeeper implements Position{

    private static final Integer MAX_HEIGHT = 210;

    private Integer height;
    private Integer reflexes;

    public Goalkeeper(Integer height, Integer reflexes) {
        this.height = calculateHeightNormalizedValue(height);
        this.reflexes = reflexes;
    }

    @Override
    public Integer calculateAbility() {
        return ((height * 4) + (this.reflexes * 6) / 10);
    }

    private Integer calculateHeightNormalizedValue(Integer height) {
        Integer validatedheight = validateAttributeInRange(height, MAX_HEIGHT);
        Double normalized = (validatedheight * 100.0) / MAX_HEIGHT;
        return round(normalized);
    }
}
