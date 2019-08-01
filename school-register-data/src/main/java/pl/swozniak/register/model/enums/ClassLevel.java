package pl.swozniak.register.model.enums;

public enum ClassLevel {
    FIRST(1), SECOND(2), THIRD(3), FOURTH(4);

    private final Integer value;

    ClassLevel(Integer value) {
        this.value = value;
    }
}
