import amebot.App;

public class Duke {
    private static final String BORDER = "================================================================================\n";

    private static final String MESSAGE = "                               _|\n"
            + "           _|    _|    _|_|    _|  _|      _|_|      _|_|_|    _|_|\n"
            + "           _|    _|  _|    _|  _|_|      _|    _|  _|_|      _|    _|\n"
            + "           _|    _|  _|    _|  _|  _|    _|    _|      _|_|  _|    _|\n"
            + "             _|_|_|    _|_|    _|    _|    _|_|    _|_|_|      _|_|\n"
            + "                 _|\n"
            + "             _|_|\n";
    private static final String LOGO = "    _     _      _     _      _     _      _     _      _     _      _     _\n"
            + "   (c).-.(c)    (c).-.(c)    (c).-.(c)    (c).-.(c)    (c).-.(c)    (c).-.(c)\n"
            + "    / ._. \\      / ._. \\      / ._. \\      / ._. \\      / ._. \\      / ._. \\\n"
            + "  __\\( Y )/__  __\\( Y )/__  __\\( Y )/__  __\\( Y )/__  __\\( Y )/__  __\\( Y )/__\n"
            + " (_.-/'-'\\-._)(_.-/'-'\\-._)(_.-/'-'\\-._)(_.-/'-'\\-._)(_.-/'-'\\-._)(_.-/'-'\\-._)\n"
            + "    || A ||      || M ||      || E ||      || B ||      || O ||      || T ||\n"
            + "  _.' `-' '._  _.' `-' '._  _.' `-' '._  _.' `-' '._  _.' `-' '._  _.' `-' '._\n"
            + " (.-./`-'\\.-.)(.-./`-'\\.-.)(.-./`-'\\.-.)(.-./`-'\\.-.)(.-./`-'\\.-.)(.-./`-'\\.-.)\n"
            + "  `-'     `-'  `-'     `-'  `-'     `-'  `-'     `-'  `-'     `-'  `-'     `-'\n";

    private static final String GREETING = "Yokoso, your personal assistant AMEBOT~!\n"
            + "Please enter the command(s):";

    public static void printHeader() {
        System.out.print(BORDER
                + MESSAGE
                + LOGO
                + BORDER);
    }

    public static void printGreeting() {
        System.out.println(GREETING);
    }

    public static void main(String[] args) {
        printHeader();
        printGreeting();

        App.launch();
    }
}
