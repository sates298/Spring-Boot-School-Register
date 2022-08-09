package pl.swozniak.register.model.enums;

import java.util.Arrays;

public enum GradeValue {
    ONE(1d, "1"),
    TWOMINUS(1.75, "2-"),
    TWO(2d, "2"),
    TWOPLUS(2.5, "2+"),
    THREEMINUS(2.75, "3-"),
    THREE(3d, "3"),
    THREEPLUS(3.5, "3+"),
    FOURMINUS(3.75, "4-"),
    FOUR(4d, "4"),
    FOURPLUS(4.5, "4+"),
    FIVEMINUS(4.75, "5-"),
    FIVE(5d, "5"),
    FIVEPLUS(5.5, "5+"),
    SIX(6d, "6");

    private final Double value;
    private final String repr;

    GradeValue(Double value, String repr) {
        this.value = value;
        this.repr = repr;
    }

    public Double getValue() {
        return value;
    }

    @Override
    public String toString(){
        return this.repr;
    }

    public static GradeValue fromString(String string){
        return Arrays.stream(GradeValue.values())
                .filter(val -> val.toString().equals(string))
                .findAny()
                .orElseThrow(RuntimeException::new);
    }

}
