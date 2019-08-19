package pl.swozniak.register.gradeprocessing;

import org.springframework.stereotype.Component;
import pl.swozniak.register.dtos.GradeDTO;
import pl.swozniak.register.exceptions.IllegalOperationException;
import pl.swozniak.register.exceptions.ResourceNotFoundException;

@Component
public class GradeUpdater {

    private GradeDTO toUpdate;
    private GradeDTO updatingBase;

    public GradeDTO updateGrade(GradeDTO oldGrade, GradeDTO newGrade){
        setFields(oldGrade, newGrade);

        checkOwners();
        GradeDTO returnedGrade = updateGradeValueAndNotes();

        resetFields();
        return returnedGrade;
    }

    private void setFields(GradeDTO toUpdate, GradeDTO updatingBase){
        this.toUpdate = toUpdate;
        this.updatingBase = updatingBase;
    }

    private void resetFields(){
        this.toUpdate = null;
        this.updatingBase = null;
    }

    private void checkOwners(){
        if(toUpdate.getStudent() == null) throw new ResourceNotFoundException();

        if(updatingBase.getStudent() != null &&
                !updatingBase.getStudent().equals(toUpdate.getStudent())) throw new IllegalOperationException();
    }

    private GradeDTO updateGradeValueAndNotes(){
        checkAndUpdateGradeValue();
        checkAndUpdateNotes();
        return toUpdate;
    }

    private GradeDTO checkAndUpdateGradeValue(){
        if(updatingBase.getGrade() != null &&
                !updatingBase.getGrade().equals(toUpdate.getGrade())) {
            toUpdate.setGrade(updatingBase.getGrade());
        }
        return toUpdate;
    }

    private GradeDTO checkAndUpdateNotes(){
        if(updatingBase.getNotes() != null &&
                !updatingBase.getNotes().equals(toUpdate.getNotes())) {
            toUpdate.setNotes(updatingBase.getNotes());
        }
        return toUpdate;
    }

}
