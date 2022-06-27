package models;

import lombok.Getter;

@Getter
public enum PointsType {
    WIN(3),
    DRAW(1),
    LOSE(0);

    private final Integer value;

    PointsType(Integer value) {
        this.value = value;
    }
}
