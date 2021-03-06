package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.UNKNOWN_ADD_COMMAND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ENDDATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ENDTIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STARTDATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STARTTIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;

import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.add.AddContactCommand;
import seedu.address.logic.commands.add.AddEventCommand;
import seedu.address.logic.commands.add.AddTodoCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Event;
import seedu.address.model.task.MeetingLink;
import seedu.address.model.task.Todo;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        String[] splitArgs = args.trim().split(" ", 2);
        if (splitArgs[0].trim().split(" ")[0].trim().equals("contact")) {
            ArgumentMultimap argMultimap =
                    ArgumentTokenizer.tokenize(" " + splitArgs[1], PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL,
                            PREFIX_ADDRESS, PREFIX_TAG);

            if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_ADDRESS, PREFIX_PHONE, PREFIX_EMAIL)
                    || !argMultimap.getPreamble().isEmpty()) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        AddContactCommand.MESSAGE_USAGE));
            }

            Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
            Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
            Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
            Address address = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get());
            Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

            Person person = new Person(name, phone, email, address, tagList);

            return new AddContactCommand(person);
        } else if (splitArgs[0].trim().equals("todo")) {
            ArgumentMultimap argMultimap =
                    ArgumentTokenizer.tokenize(" " + splitArgs[1], PREFIX_DESCRIPTION, PREFIX_DATE, PREFIX_TIME);
            if (!arePrefixesPresent(argMultimap, PREFIX_DESCRIPTION, PREFIX_DATE, PREFIX_TIME)
                    || !argMultimap.getPreamble().isEmpty()) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        AddTodoCommand.MESSAGE_USAGE));
            }
            String description = argMultimap.getValue(PREFIX_DESCRIPTION).get().trim();
            String date = argMultimap.getValue(PREFIX_DATE).get().trim();
            String time = argMultimap.getValue(PREFIX_TIME).get().trim();
            String deadline = date + " " + time;
            Todo todo = new Todo(description, deadline);
            return new AddTodoCommand(todo);
        } else if (splitArgs[0].trim().equals("event")) {
            ArgumentMultimap argMultimap =
                    ArgumentTokenizer.tokenize(" " + splitArgs[1], PREFIX_DESCRIPTION, PREFIX_STARTDATE,
                            PREFIX_STARTTIME, PREFIX_ENDDATE, PREFIX_ENDTIME);
            if (!arePrefixesPresent(argMultimap, PREFIX_DESCRIPTION, PREFIX_STARTDATE,
                    PREFIX_STARTTIME, PREFIX_ENDDATE, PREFIX_ENDTIME)
                    || !argMultimap.getPreamble().isEmpty()) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        AddEventCommand.MESSAGE_USAGE));
            }
            String description = argMultimap.getValue(PREFIX_DESCRIPTION).get().trim();
            String stDate = argMultimap.getValue(PREFIX_STARTDATE).get().trim();
            String stTime = argMultimap.getValue(PREFIX_STARTTIME).get().trim();
            String endDate = argMultimap.getValue(PREFIX_ENDDATE).get().trim();
            String endTime = argMultimap.getValue(PREFIX_ENDTIME).get().trim();
            String stDateTime = stDate + " " + stTime;
            String endDateTime = endDate + " " + endTime;
            MeetingLink meetingLink = new MeetingLink();

            Event event = new Event(description, stDateTime, endDateTime, meetingLink);

            return new AddEventCommand(event);
        } else {
            throw new ParseException(UNKNOWN_ADD_COMMAND);
        }
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
