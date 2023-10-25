package com.tina.command;

import java.util.Map;

public enum CommandEnum {
    BYE("bye"),
    LIST("list"),
    TODO("todo [task name]"),
    DEADLINE("deadline [task name] /by [date]"),
    EVENT("event [task name] /from [date] /to [date]"),
    MARK("mark [task number]"),
    UNMARK("unmark [task number"),
    DELETE("delete [task number"),
    UNKNOWN();

    private String syntax;

    CommandEnum(String syntax) {
        this.syntax = syntax;
    }
    CommandEnum(){}

    public String getSyntax() {
        return syntax;
    }
}
