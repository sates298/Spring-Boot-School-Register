package pl.swozniak.register.gradestrategy;

import org.springframework.stereotype.Component;
import pl.swozniak.register.dtos.ParentDTO;

@Component
public class BadBehaviorMailMessageGenerator {

    public String generateMessage(ParentDTO parent, String studentFirstName, String notes){
        return notes.isBlank()
                ? generateMessageWithoutNotes(parent, studentFirstName)
                : generateMessageWithNotes(parent, studentFirstName, notes);
    }

    private String generateMessageWithNotes(ParentDTO parent, String studentFirstName, String notes){
        StringBuilder builder = generateIntroduction(parent, studentFirstName);
        builder.append("Grade with notes: ").append(notes);
        return generateEnding(builder).toString();
    }

    private String generateMessageWithoutNotes(ParentDTO parent, String studentFirstName) {
        StringBuilder builder = generateIntroduction(parent, studentFirstName);
        return generateEnding(builder).toString();
    }

    private StringBuilder generateIntroduction(ParentDTO parent, String studentFirstName){
        StringBuilder builder = new StringBuilder();
        builder.append("Dear ").append(parent.getFirstName()).append(" ").append(parent.getLastName())
                .append('\n')
                .append("Your child, ").append(studentFirstName).append(", get a negative grade from behavior.")
                .append('\n');
        return builder;
    }

    private StringBuilder generateEnding(StringBuilder builder){
        builder.append("Yours faithfully")
                .append('\n')
                .append("School XYZ");
        return builder;
    }

}
