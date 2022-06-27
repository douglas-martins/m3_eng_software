package models.player;

public class Defender implements Position{

    private Integer cover;
    private Integer disarm;

    public Defender(Integer cover, Integer disarm) {
        this.cover = validateAttributeInRange(cover);
        this.disarm = validateAttributeInRange(disarm);
    }

    @Override
    public Integer calculateAbility() {
        return ((this.cover * 6) + (this.disarm * 4));
    }
}
