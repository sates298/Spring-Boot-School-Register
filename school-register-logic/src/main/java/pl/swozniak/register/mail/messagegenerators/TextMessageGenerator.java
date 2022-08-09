package pl.swozniak.register.mail.messagegenerators;

import pl.swozniak.register.dtos.ParentDTO;
import pl.swozniak.register.dtos.StudentDTO;
import pl.swozniak.register.exceptions.NullPointerInTextContentException;

public interface TextMessageGenerator {
    String generateTextMessage(TextMessageContent content);

    default TextMessageContent generateDefaultContent(ParentDTO parent, StudentDTO student, String notes){
        if(parent == null || student == null) throw new NullPointerInTextContentException();
        return TextMessageContent.builder()
                .addresseeFirstName(parent.getFirstName())
                .addresseeLastName(parent.getLastName())
                .studentFirstName(student.getFirstName())
                .studentLastName(student.getLastName())
                .notes(notes)
                .build();
    }
}
