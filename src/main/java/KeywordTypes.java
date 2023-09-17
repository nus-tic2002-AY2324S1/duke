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

}
