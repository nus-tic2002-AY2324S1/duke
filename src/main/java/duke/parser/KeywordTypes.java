package duke.parser;

import duke.command.*;
import duke.task.Todo;
import duke.task.Deadline;
import duke.task.Event;

public enum KeywordTypes {
    BYE{
        @Override
        public ICommand createCommand(){
            return new Bye();
        }
    },
    LIST{
        @Override
        public ICommand createCommand(){
            return new List();
        }
    },
    MARK{
        @Override
        public ICommand createCommand(){
            return new Mark();
        }
    },
    UNMARK{
        @Override
        public ICommand createCommand(){
            return new UnMark();
        }
    },
    TODO{
        @Override
        public ICommand createCommand(){
            return new todoCommand();
        }
    },
    DEADLINE{
        @Override
        public ICommand createCommand(){
            return new deadlineCommand();
        }
    },
    EVENT{
        @Override
        public ICommand createCommand(){
            return new eventCommand();
        }
    },
    DELETE{
      @Override
      public ICommand createCommand(){
          return new Delete();
      }
    };

    public abstract ICommand createCommand();

}
