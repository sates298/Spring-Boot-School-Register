package pl.swozniak.register.mail.messagegenerators;

import org.springframework.stereotype.Component;
import pl.swozniak.register.exceptions.NullPointerInTextContentException;

@Component
public class BadBehaviorTextMessageGenerator extends AbstractTextMessageGenerator {

    @Override
    public String generateTextMessage(TextMessageContent content) {
        if (content == null) throw new NullPointerInTextContentException();
        return content.getNotes() == null || content.getNotes().isBlank()
                ? generateMessageWithoutNotes(content)
                : generateMessageWithNotes(content);
    }

    private String generateMessageWithNotes(TextMessageContent content) {
        String firstName = content.getAddresseeFirstName();
        String lastName = content.getAddresseeLastName();
        if(firstName == null || lastName == null) throw new NullPointerInTextContentException();

        StringBuilder builder =
                generateIntroduction(firstName, lastName);

        builder = generateCommonMiddle(content.getStudentFirstName(),content.getStudentLastName(), builder);
        builder.append("Grade with notes: ")
                .append(content.getNotes());

        return generateEnding(builder).toString();
    }

    private String generateMessageWithoutNotes(TextMessageContent content) {
        String firstName = content.getAddresseeFirstName();
        String lastName = content.getAddresseeLastName();
        if(firstName == null || lastName == null) throw new NullPointerInTextContentException();

        StringBuilder builder =
                generateIntroduction(firstName, lastName);

        builder = generateCommonMiddle(content.getStudentFirstName(),content.getStudentLastName(), builder);
        return generateEnding(builder).toString();
    }

    private StringBuilder generateCommonMiddle(String studentFirstName,String studentLastName, StringBuilder builder) {
        if(studentFirstName == null || studentLastName == null) throw new NullPointerInTextContentException();
        return builder
                .append("Your child, ")
                .append(studentFirstName)
                .append(" ")
                .append(studentLastName)
                .append(", got a negative grade from behavior.")
                .append('\n');
    }


}
