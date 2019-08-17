package pl.swozniak.register.gradeprocessing;

import org.springframework.stereotype.Component;
import pl.swozniak.register.dtos.GradeDTO;
import pl.swozniak.register.exceptions.ResourceNotFoundException;

@Component
public class GradeUpdater {


    public GradeDTO updateGrade(GradeDTO oldGrade, GradeDTO newGrade){
        //todo
        if (!oldGrade.getGrade().equals(newGrade.getGrade())) {
            oldGrade.setGrade(newGrade.getGrade());
        }

        if (!oldGrade.getNotes().equals(newGrade.getNotes())) {
            oldGrade.setNotes(newGrade.getNotes());
        }

        if (oldGrade.getStudent() == null){
            throw new ResourceNotFoundException();
        }
        return oldGrade;
    }

}
