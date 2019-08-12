package pl.swozniak.register.services.gradestrategy;


import org.springframework.stereotype.Component;
import pl.swozniak.register.dtos.GradeDTO;

@Component
public class NewGradeProcessor {

    private final ProcessNewGradeStrategyFactory strategyFactory;

    public NewGradeProcessor(ProcessNewGradeStrategyFactory strategyFactory) {
        this.strategyFactory = strategyFactory;
    }

    public GradeDTO processNewGrade(GradeDTO grade) {
        ProcessNewGradeStrategy newGradeStrategy = checkGradeStrategy(grade);
        return newGradeStrategy.processNewGrade(grade);
    }

    private ProcessNewGradeStrategy checkGradeStrategy(GradeDTO grade) {
        return strategyFactory.getRightStrategy(grade);
    }
}
