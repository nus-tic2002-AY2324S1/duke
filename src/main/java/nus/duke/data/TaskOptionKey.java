package nus.duke.data;

/**
 * The `TaskOptionKey` enum represents keys for task-related options.
 * These keys are used to identify and access specific task options in command input.
 */
public enum TaskOptionKey {
    AFTER("after"),
    BY("by"),
    FROM("from"),
    TO("to");

    private final String optionKey;

    /**
     * Constructs a `TaskOptionKey` with the specified option key.
     *
     * @param optionKey The string representation of the option key.
     */
    TaskOptionKey(String optionKey) {
        assert optionKey != null;

        this.optionKey = optionKey;
    }

    @Override
    public String toString() {
        return optionKey;
    }
}
