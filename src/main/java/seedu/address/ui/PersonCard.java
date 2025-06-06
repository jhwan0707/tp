package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.ClientType;
import seedu.address.model.person.Person;

/**
 * A UI component that displays information of a {@code Person}.
 */
public class PersonCard extends UiPart<Region> {

    private static final String FXML = "PersonListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Person person;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private FlowPane tags;
    @FXML
    private FlowPane leadstatus;
    @FXML
    private Label district;
    @FXML
    private Label price;
    @FXML
    private Label landsize;
    @FXML
    private Label clienttype;


    private CommandBox commandBox;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PersonCard(Person person, int displayedIndex) {
        super(FXML);
        this.person = person;
        id.setText(displayedIndex + ". ");
        name.setText(person.getName().fullName);
        phone.setText(person.getPhone().value);
        address.setText(person.getAddress().value);
        email.setText(person.getEmail().value);
        if (person.getDistrict() != null) {
            district.setText(person.getDistrict().getFormattedDistrict());
            district.setVisible(true);
            district.setManaged(true);
        }
        if (person.getPrice() != null) {
            System.out.println(person.getPrice().getFormattedPrice());
            price.setText(person.getPrice().getFormattedPrice());
            price.setVisible(true);
            price.setManaged(true);
        }
        if (person.getLandSize() != null) {
            landsize.setText(person.getLandSize().getFormattedLandSize());
            landsize.setVisible(true);
            landsize.setManaged(true);
        }
        if (person.getLeadStatus() != null) {
            leadstatus.getChildren().add(new Label(person.getLeadStatus()));
            leadstatus.setManaged(true);
            leadstatus.setVisible(true);
        }
        if (person.getClientType() != ClientType.UNKNOWN) {
            clienttype.setText(person.getClientType().toString());
            clienttype.setVisible(true);
            clienttype.setManaged(true);
        }

        person.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> {
                    Label tagLabel = new Label(tag.tagName);
                    tagLabel.setOnMouseClicked(event -> {
                        if (this.commandBox != null) {
                            try {
                                this.commandBox.getCommandExecutor().execute("tag " + tag.tagName);
                            } catch (CommandException | ParseException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                    tags.getChildren().add(tagLabel);
                });
    }

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PersonCard(Person person, int displayedIndex, CommandBox commandBox) {
        this(person, displayedIndex);
        this.commandBox = commandBox;
    }

}
