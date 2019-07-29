package pl.swozniak.register.model;

import java.time.LocalTime;

public enum LessonNumber {
    //todo verify hours
    ZERO(LocalTime.of(7, 30), LocalTime.of(8, 15)),
    FIRST(LocalTime.of(7, 30), LocalTime.of(8, 15)),
    SECOND(LocalTime.of(7, 30), LocalTime.of(8, 15)),
    THIRD(LocalTime.of(7, 30), LocalTime.of(8, 15)),
    FOURTH(LocalTime.of(7, 30), LocalTime.of(8, 15)),
    FIFTH(LocalTime.of(7, 30), LocalTime.of(8, 15)),
    SIXTH(LocalTime.of(7, 30), LocalTime.of(8, 15)),
    SEVENTH(LocalTime.of(7, 30), LocalTime.of(8, 15)),
    EIGHTH(LocalTime.of(7, 30), LocalTime.of(8, 15)),
    NINTH(LocalTime.of(7, 30), LocalTime.of(8, 15));

    private final LocalTime start;
    private final LocalTime end;

    LessonNumber(LocalTime start, LocalTime end) {
        this.start = start;
        this.end = end;
    }

    public LocalTime getStart() {
        return start;
    }

    public LocalTime getEnd() {
        return end;
    }
}
