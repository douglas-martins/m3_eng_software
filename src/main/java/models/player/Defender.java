package models.player;

public class Defender extends Player {

    private Integer cover;
    private Integer disarm;

    public Defender(String name, Integer age, Integer cover, Integer disarm) {
        super(name, age);
        this.cover = cover;
        this.disarm = disarm;

        this.setAbility();
    }

    @Override
    public Integer calculateAbility() {
        return (this.cover * 6) + (this.disarm * 4);
    }
}
