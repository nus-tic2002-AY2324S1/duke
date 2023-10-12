package seedu.duke.commands;

public enum WonkyMode {
    NORMAL("normal"),
    TEST("test");

    private String mode;

    WonkyMode(String mode) {
        this.mode = mode;
    }

    public String getLitr() {
        return mode;
    }

    public static WonkyMode getEnum(String mode) {
        return WonkyMode.valueOf(mode.toUpperCase());
    }
}
