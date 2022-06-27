package models.player;

public class Striker implements Position{

    private Integer velocity;
    private Integer technique;

    public Striker(Integer velocity, Integer technique) {
        this.velocity = validateAttributeInRange(velocity);
        this.technique = validateAttributeInRange(technique);
    }

    @Override
    public Integer calculateAbility() {
        return ((this.velocity * 4) + (this.technique * 6)) / 10;
    }
}
