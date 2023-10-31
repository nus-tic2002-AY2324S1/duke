package nus.duke.data;

import java.util.HashMap;
import java.util.Optional;

/**
 * The `TaskSource` class represents a task source with a description and optional key-value options.
 * This class is used to parse and store task-related information from user input.
 */
public class TaskSource {

    private final String description;
    private final HashMap<String, String> options;

    /**
     * Constructs a `TaskSource` with the given description.
     *
     * @param description The description of the task source. Must not be null.
     */
    public TaskSource(String description) {
        this(description, new HashMap<>());
    }

    /**
     * Constructs a `TaskSource` with the given description and options.
     *
     * @param description The description of the task source. Must not be null.
     * @param options     The key-value options associated with the task source. Must not be null.
     */
    public TaskSource(String description, HashMap<String, String> options) {
        assert description != null;
        assert options != null;

        this.description = description;
        this.options = options;
    }

    /**
     * Get the description of the task source.
     *
     * @return The description of the task source.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Get the key-value options associated with the task source.
     *
     * @return A HashMap containing the key-value options of the task source.
     */
    public HashMap<String, String> getOptions() {
        return options;
    }

    /**
     * Get the value associated with a specific option key.
     *
     * @param optionKey The key of the option for which to retrieve the value. Must not be null.
     * @return An Optional containing the value of the option if it exists, or an empty Optional if it doesn't.
     */
    public Optional<String> getOptionValue(String optionKey) {
        assert optionKey != null;

        return options.containsKey(optionKey)
            ? Optional.of(options.get(optionKey))
            : Optional.empty();
    }

    /**
     * Get the value associated with a specific option key represented by a `TaskOptionKey` enum.
     *
     * @param optionKey The `TaskOptionKey` enum representing the option key to retrieve.
     * @return An Optional containing the value of the option if it exists, or an empty Optional if it doesn't.
     */
    public Optional<String> getOptionValue(TaskOptionKey optionKey) {
        assert optionKey != null;

        return getOptionValue(optionKey.toString());
    }
}
