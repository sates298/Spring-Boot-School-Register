package pl.swozniak.register.mail.messagegenerators;


import pl.swozniak.register.exceptions.NullPointerInTextContentException;

public abstract class AbstractTextMessageGenerator implements TextMessageGenerator{

    protected StringBuilder generateIntroduction(String addresseeFirstName, String addresseeLastName){
        if(addresseeFirstName == null || addresseeLastName == null) throw new NullPointerInTextContentException();

        StringBuilder builder = new StringBuilder();
        builder.append("Dear ").append(addresseeFirstName).append(" ").append(addresseeLastName).append("!")
                .append('\n');
        return builder;
    }

    protected StringBuilder generateEnding(StringBuilder builder){
        builder.append("Yours faithfully")
                .append('\n')
                .append("School XYZ");
        return builder;
    }

}
