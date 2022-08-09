package pl.swozniak.register.mail.messagegenerators;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class TextMessageContent {
    private String addresseeFirstName;
    private String addresseeLastName;
    private String studentFirstName;
    private String studentLastName;
    
    private String notes;
    
    @Builder
    public TextMessageContent(String addresseeFirstName, String addresseeLastName, String studentFirstName, String studentLastName, String notes) {
        this.addresseeFirstName = addresseeFirstName;
        this.addresseeLastName = addresseeLastName;
        this.studentFirstName = studentFirstName;
        this.studentLastName = studentLastName;
        this.notes = notes;
    }
}
