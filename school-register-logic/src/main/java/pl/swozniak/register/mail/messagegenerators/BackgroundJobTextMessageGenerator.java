package pl.swozniak.register.mail.messagegenerators;

import org.springframework.stereotype.Component;
import pl.swozniak.register.backgroundjob.BackgroundJobStudentFinder;
import pl.swozniak.register.exceptions.NullPointerInTextContentException;

@Component
public class BackgroundJobTextMessageGenerator extends AbstractTextMessageGenerator {

    @Override
    public String generateTextMessage(TextMessageContent content) {
        if (content == null) throw new NullPointerInTextContentException();

        return generateFullText(content).toString();
    }

    private StringBuilder generateFullText(TextMessageContent content){
        String firstName = content.getAddresseeFirstName();
        String lastName = content.getAddresseeLastName();
        if(firstName == null || lastName == null) throw new NullPointerInTextContentException();

        StringBuilder builder = super.generateIntroduction(firstName, lastName);

        builder = generateMiddle(content, builder);

        return super.generateEnding(builder);
    }

    private StringBuilder generateMiddle(TextMessageContent content, StringBuilder builder){
        String studentFirstName = content.getStudentFirstName();
        String studentLastName = content.getStudentLastName();
        String average = content.getNotes();
        if(studentFirstName == null || studentLastName == null || average == null)
            throw new NullPointerInTextContentException();

        builder.append("Your child, ").append(studentFirstName)
                .append(" ")
                .append(studentLastName)
                .append(", has at least")
                .append(BackgroundJobStudentFinder.NUMBER_OF_CORRECT_GRADES.toString())
                .append(" grades ")
                .append(BackgroundJobStudentFinder.GRADE_VALUE.toString())
                .append(".\n");

        builder.append("His\\Her grades average is equal ")
                .append(average)
                .append(".\n");

        return builder;
    }

}
