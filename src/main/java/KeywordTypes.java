public enum Keyword {
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
            return new Unmark();
        }
    },
    TODO{
        @Override
        public ICommand createCommand(){
            return new Todo();
        }
    },
    DEADLINE{
        @Override
        public ICommand createCommand(){
            return new Deadline();
        }
    },
    EVENT{
        @Override
        public ICommand createCommand(){
            return new Event();
        }
    };

    public abstract ICommand createCommand();

    /*public static Keyword getKeyword(String input) {
        String key = input.toUpperCase();
        switch (key) {
        case "BYE":
            return BYE;
        case "LIST":
            return LIST;
        case "MARK":
            return MARK;
        case "UNMARK":
            return UNMARK;
        case "TODO":
            return TODO;
        case "DEADLINE":
            return DEADLINE;
        case "EVENT":
            return EVENT;
        }
        return null;
    }*/

}
