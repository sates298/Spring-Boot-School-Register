package pl.swozniak.register.gradeprocessing;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import pl.swozniak.register.dtos.GradeDTO;
import pl.swozniak.register.model.enums.GradeValue;
import pl.swozniak.register.model.enums.SubjectName;

@Component
public class ProcessNewGradeStrategyFactory {

    private final ProcessNewGradeStrategy badBehaviorStrategy;
    private final ProcessNewGradeStrategy positiveGradeStrategy;

    public ProcessNewGradeStrategyFactory(@Qualifier("badBehaviorProcessNewGradeStrategy") ProcessNewGradeStrategy badBehaviorStrategy,
                                          @Qualifier("positiveProcessNewGradeStrategy") ProcessNewGradeStrategy positiveGradeStrategy) {
        this.badBehaviorStrategy = badBehaviorStrategy;
        this.positiveGradeStrategy = positiveGradeStrategy;
    }

    ProcessNewGradeStrategy getRightStrategy(GradeDTO grade) {
        return SubjectName.BEHAVIOR.toString()
                .equals(grade
                        .getSubject()
                        .getName())
                && GradeValue.ONE.toString()
                .equals(grade
                        .getGrade())
                ? badBehaviorStrategy : positiveGradeStrategy;
    }
}
