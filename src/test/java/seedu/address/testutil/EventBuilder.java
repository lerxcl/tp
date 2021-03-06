package seedu.address.testutil;

import java.time.format.DateTimeFormatter;

import seedu.address.model.task.Event;

/**
 * A utility class to help with building To-do objects.
 */
public class EventBuilder {
    /**
     * The format of inputted dates that the class can accept.
     */
    private static final DateTimeFormatter INPUT_DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
    /**
     * The format of outputted dates by the class.
     */
    private static final DateTimeFormatter OUTPUT_DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("dd MMM yyyy HHmm");

    private static final String DEFAULT_DESC = "meeting";
    private static final String DEFAULT_STARTDATETIME = "12-12-2020 1000";
    private static final String DEFAULT_ENDDATETIME = "12-12-2020 1130";

    private String description;
    private String startDateTime;
    private String endDateTime;

    /**
     * Creates a {@code EventBuilder} with the default details.
     */
    public EventBuilder() {
        description = DEFAULT_DESC;
        startDateTime = DEFAULT_STARTDATETIME;
        endDateTime = DEFAULT_ENDDATETIME;
    }

    /**
     * Initializes the EventBuilder with the data of {@code EventToCopy}.
     */
    public EventBuilder(Event eventToCopy) {
        description = eventToCopy.getDescription();
        startDateTime = eventToCopy.getStartDateTime();
        endDateTime = eventToCopy.getEndDateTime();
    }

    /**
     * Sets the {@code Description} of the {@code To-do} that we are building.
     */
    public EventBuilder withDescription(String desc) {
        this.description = desc;
        return this;
    }

    /**
     * Sets the {@code startDateTime} of the {@code Event} that we are building.
     */
    public EventBuilder withStartDateTime(String startDateTime) {
        this.startDateTime = startDateTime;
        return this;
    }

    /**
     * Sets the {@code endDateTime} of the {@code Event} that we are building.
     */
    public EventBuilder withEndDateTime(String endDateTime) {
        this.endDateTime = endDateTime;
        return this;
    }


    public Event build() {
        return new Event(description, startDateTime, endDateTime);
    }

}
