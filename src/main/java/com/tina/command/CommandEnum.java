package com.tina.command;

import java.util.Map;

/**
 * The enumeration class contains all command type and its corresponding syntax.
 */
public enum CommandEnum {
    BYE("bye"),
    LIST("list"),
    TODO("todo [task name]"),
    DEADLINE("deadline [task name] /by [date]"),
    EVENT("event [task name] /from [date] /to [date]"),
    MARK("mark [task number]"),
    UNMARK("unmark [task number"),
    DELETE("delete [task number"),
    SCHEDULE("schedule [date]"),
    ARCHIVE("archive [file name].txt"),
    FIND("find [keyword]"),
    UNKNOWN();

    private String syntax;

    CommandEnum(String syntax) {
        this.syntax = syntax;
    }
    CommandEnum(){}

    /**
     * Gets syntax of current command type.
     *
     * @return the syntax
     */
    public String getSyntax() {
        return syntax;
    }
}
