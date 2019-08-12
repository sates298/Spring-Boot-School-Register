package pl.swozniak.register.services.gradestrategy;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import pl.swozniak.register.dtos.GradeDTO;
import pl.swozniak.register.model.enums.GradeValue;
import pl.swozniak.register.model.enums.SubjectName;

@Component
public class ProcessNewGradeStrategyFactory {

    private final ProcessNewGradeStrategy badBehaviorStrategy;
    private final ProcessNewGradeStrategy positiveGradeStrategy;

    public ProcessNewGradeStrategyFactory(@Qualifier("processNewGradeBadBehavior") ProcessNewGradeStrategy badBehaviorStrategy,
                                          @Qualifier("processNewPositiveGrade") ProcessNewGradeStrategy positiveGradeStrategy) {
        this.badBehaviorStrategy = badBehaviorStrategy;
        this.positiveGradeStrategy = positiveGradeStrategy;
    }

    ProcessNewGradeStrategy getRightStrategy(GradeDTO grade){
        return grade
                .getSubject()
                .getName()
                .equals(SubjectName.BEHAVIOR.toString())
                && grade
                .getGrade()
                .equals(GradeValue.ONE.toString())
                ? badBehaviorStrategy : positiveGradeStrategy;
    }
}
