package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRICE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TYPE;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.property.Property;

/**
 * Represents a command to add a property to the address book.
 */
public class AddPropertyCommand extends Command {
    public static final String COMMAND_WORD = "add-property";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a property to the address book.\n"
            + "Parameters: "
            + PREFIX_PRICE + "PRICE "
            + PREFIX_LOCATION + "LOCATION "
            + PREFIX_TYPE + "TYPE "
            + PREFIX_STATUS + "STATUS\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_PRICE + "$500,000 "
            + PREFIX_LOCATION + "123 Main St "
            + PREFIX_TYPE + "Condo "
            + PREFIX_STATUS + "Available";

    public static final String MESSAGE_SUCCESS = "New property added: %1$s";

    private final Property toAdd;

    /**
     * Creates an {@code AddPropertyCommand} to add the specified {@code Property}
     * @param property The property to be added.
     */
    public AddPropertyCommand(Property property) {
        requireNonNull(property);
        toAdd = property;
    }


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.addProperty(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }
}
