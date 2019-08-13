package pl.swozniak.register.gradeprocessing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.swozniak.register.dtos.GradeDTO;
import pl.swozniak.register.dtos.SubjectDTO;
import pl.swozniak.register.model.enums.GradeValue;
import pl.swozniak.register.model.enums.SubjectName;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class ProcessNewGradeStrategyFactoryTest {


    private BadBehaviorProcessNewGradeStrategy badBehavior;

    private PositiveProcessNewGradeStrategy positiveGrade;

    private ProcessNewGradeStrategyFactory factory;

    @BeforeEach
    void setUp() {
        badBehavior = mock(BadBehaviorProcessNewGradeStrategy.class);
        positiveGrade = mock(PositiveProcessNewGradeStrategy.class);

        factory = new ProcessNewGradeStrategyFactory(badBehavior, positiveGrade);
    }

    @Test
    void getRightStrategyOneFromBehavior() {
        SubjectDTO subject = new SubjectDTO();
        subject.setName(SubjectName.BEHAVIOR.toString());

        GradeDTO grade = new GradeDTO();
        grade.setSubject(subject);
        grade.setGrade(GradeValue.ONE.toString());

        ProcessNewGradeStrategy strategy = factory.getRightStrategy(grade);

        assertEquals(badBehavior, strategy);
    }

    @Test
    void getRightStrategyPositiveGradeFromBehavior(){
        SubjectDTO subject = new SubjectDTO();
        subject.setName(SubjectName.BEHAVIOR.toString());

        GradeDTO grade = new GradeDTO();
        grade.setSubject(subject);
        grade.setGrade(GradeValue.THREE.toString());

        ProcessNewGradeStrategy strategy = factory.getRightStrategy(grade);

        assertEquals(positiveGrade, strategy);
    }

    @Test
    void getRightStrategyOneFromNotBehavior() {
        SubjectDTO subject = new SubjectDTO();
        subject.setName(SubjectName.BIOLOGY.toString());

        GradeDTO grade = new GradeDTO();
        grade.setSubject(subject);
        grade.setGrade(GradeValue.ONE.toString());

        ProcessNewGradeStrategy strategy = factory.getRightStrategy(grade);

        assertEquals(positiveGrade, strategy);
    }

    @Test
    void getRightStrategyPositiveGradeFromNotBehavior(){
        SubjectDTO subject = new SubjectDTO();
        subject.setName(SubjectName.BIOLOGY.toString());

        GradeDTO grade = new GradeDTO();
        grade.setSubject(subject);
        grade.setGrade(GradeValue.FOUR.toString());

        ProcessNewGradeStrategy strategy = factory.getRightStrategy(grade);

        assertEquals(positiveGrade, strategy);
    }

}