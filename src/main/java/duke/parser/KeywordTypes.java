package duke.parser;

import duke.command.*;

public enum KeywordTypes {
    BYE {
        @Override
        public Command createCommand() {
            return new ExitCommand();
        }
    },
    LIST {
        @Override
        public Command createCommand() {
            return new ListCommand();
        }
    },
    MARK {
        @Override
        public Command createCommand() {
            return new MarkCommand();
        }
    },
    UNMARK {
        @Override
        public Command createCommand() {
            return new UnMarkCommand();
        }
    },
    TODO {
        @Override
        public Command createCommand() {
            return new TodoCommand();
        }
    },
    DEADLINE {
        @Override
        public Command createCommand() {
            return new DeadlineCommand();
        }
    },
    EVENT {
        @Override
        public Command createCommand() {
            return new EventCommand();
        }
    },
    DELETE {
        @Override
        public Command createCommand() {
            return new DeleteCommand();
        }
    },
    DPRINT {
        @Override
        public Command createCommand() {
            return new DatePrintCommand();
        }
    },
    RECUR{
        @Override
        public Command createCommand() {
            return new RecurCommand();
        }
    },
    FIND{
        @Override
        public Command createCommand() {
            return new FindCommand();
        }
    },
    UPDATE{
        @Override
        public Command createCommand(){
            return new UpdateCommand();
        }
    };

    public abstract Command createCommand();

}
