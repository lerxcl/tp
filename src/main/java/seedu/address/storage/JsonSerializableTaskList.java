package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.ReadOnlyTaskList;
import seedu.address.model.TaskList;
import seedu.address.model.task.Task;

/**
 * An Immutable TaskList that is serializable to JSON format.
 */
@JsonRootName(value = "tasklist")
public class JsonSerializableTaskList {
    public static final String MESSAGE_DUPLICATE_PERSON = "Task list contains duplicate task(s).";
    private final List<JsonAdaptedTask> tasks = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableTaskList} with the given tasks.
     */
    @JsonCreator
    public JsonSerializableTaskList(@JsonProperty("tasks") List<? extends JsonAdaptedTask> tasks) {
        this.tasks.addAll(tasks);
    }

    /**
     * Converts a given {@code ReadOnlyTaskList} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableTaskList}.
     */
    public JsonSerializableTaskList(ReadOnlyTaskList source) {
        tasks.addAll(source.getTaskList().stream().map(x -> x.isTodo()
                ? new JsonAdaptedTodo(x) : new JsonAdaptedEvent(x)).collect(Collectors.toList()));
    }

    /**
     * Converts this Task List into the model's {@code TaskList} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public TaskList toModelType() throws IllegalValueException {
        TaskList taskList = new TaskList();
        for (JsonAdaptedTask jsonAdaptedTask : tasks) {
            Task task = jsonAdaptedTask.toModelType();
            if (taskList.hasTask(task)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            taskList.addTask(task);
        }
        return taskList;
    }
}
