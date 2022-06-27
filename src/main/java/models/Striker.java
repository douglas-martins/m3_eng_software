package models;

public class Striker extends Player {

    private Integer velocity;
    private Integer technique;

    public Striker(String name, Integer age, Integer velocity, Integer technique) {
        super(name, age);
        this.velocity = velocity;
        this.technique = technique;

        this.setAbility();
    }

    @Override
    public Integer calculateAbility() {
        return (this.velocity * 4) + (this.technique * 6);
    }
}
