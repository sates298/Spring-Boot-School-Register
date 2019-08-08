package pl.swozniak.register.services.gradestrategy;


import pl.swozniak.register.dtos.GradeDTO;

public class NewGradeRecorder {

    private NewGradeStrategy newGradeStrategy;

    public NewGradeRecorder(NewGradeStrategy newGradeStrategy) {
        this.newGradeStrategy = newGradeStrategy;
    }

    public GradeDTO checkNewGrade(GradeDTO grade){
        return newGradeStrategy.checkNewGrade(grade);
    }
}
