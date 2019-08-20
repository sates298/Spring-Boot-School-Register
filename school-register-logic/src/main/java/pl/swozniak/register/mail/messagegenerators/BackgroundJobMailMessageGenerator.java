package pl.swozniak.register.mail.messagegenerators;

import org.springframework.stereotype.Component;
import pl.swozniak.register.dtos.ParentDTO;

@Component
public class BackgroundJobMailMessageGenerator {

    public String generateMessage(){
        return "string";
    }
}
