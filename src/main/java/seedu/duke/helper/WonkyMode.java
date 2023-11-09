package seedu.duke.helper;

/**
 * Represents the different modes that the program can run in.
 */
public enum WonkyMode {
    NORMAL("normal"),
    TEST("test");

    private String mode;

    /**
     * Constructs a WonkyMode object with the specified mode.
     *
     * @param mode the mode of the WonkyMode object.
     */
    WonkyMode(String mode) {
        this.mode = mode;
    }

    /**
     * Returns the mode of the WonkyMode object.
     *
     * @return the mode of the WonkyMode object.
     */
    public String getLitr() {
        return mode;
    }

    /**
     * Returns the WonkyMode object that corresponds to the specified mode.
     *
     * @param mode the mode to search for.
     * @return the WonkyMode object that corresponds to the specified mode.
     */
    public static WonkyMode getEnum(String mode) {
        return WonkyMode.valueOf(mode.toUpperCase());
    }
}
