package amebot.common;

public class Title {
    private static final String BORDER = "================================================================================" + System.lineSeparator();

    private static final String MESSAGE = "                               _|" + System.lineSeparator()
            + "           _|    _|    _|_|    _|  _|      _|_|      _|_|_|    _|_|" + System.lineSeparator()
            + "           _|    _|  _|    _|  _|_|      _|    _|  _|_|      _|    _|" + System.lineSeparator()
            + "           _|    _|  _|    _|  _|  _|    _|    _|      _|_|  _|    _|" + System.lineSeparator()
            + "             _|_|_|    _|_|    _|    _|    _|_|    _|_|_|      _|_|" + System.lineSeparator()
            + "                 _|" + System.lineSeparator()
            + "             _|_|" + System.lineSeparator();

    private static final String LOGO = "    _     _      _     _      _     _      _     _      _     _      _     _" + System.lineSeparator()
            + "   (c).-.(c)    (c).-.(c)    (c).-.(c)    (c).-.(c)    (c).-.(c)    (c).-.(c)" + System.lineSeparator()
            + "    / ._. \\      / ._. \\      / ._. \\      / ._. \\      / ._. \\      / ._. \\" + System.lineSeparator()
            + "  __\\( Y )/__  __\\( Y )/__  __\\( Y )/__  __\\( Y )/__  __\\( Y )/__  __\\( Y )/__" + System.lineSeparator()
            + " (_.-/'-'\\-._)(_.-/'-'\\-._)(_.-/'-'\\-._)(_.-/'-'\\-._)(_.-/'-'\\-._)(_.-/'-'\\-._)" + System.lineSeparator()
            + "    || A ||      || M ||      || E ||      || B ||      || O ||      || T ||" + System.lineSeparator()
            + "  _.' `-' '._  _.' `-' '._  _.' `-' '._  _.' `-' '._  _.' `-' '._  _.' `-' '._" + System.lineSeparator()
            + " (.-./`-'\\.-.)(.-./`-'\\.-.)(.-./`-'\\.-.)(.-./`-'\\.-.)(.-./`-'\\.-.)(.-./`-'\\.-.)" + System.lineSeparator()
            + "  `-'     `-'  `-'     `-'  `-'     `-'  `-'     `-'  `-'     `-'  `-'     `-'" + System.lineSeparator();

    private static final String GREETING = "Yokoso, your personal assistant AMEBOT~!" + System.lineSeparator()
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
}
