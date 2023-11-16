package io;

/**
 * NaturalDatesMessage class is a class that handles the natural dates message.
 * It has a method to print out the list keywords of natural dates that what CrabY can do.
 */
public class NaturalDatesMessage extends CrabyMessage {
    /**
     * Sends the list keywords of natural dates to the user.
     */
    public static void printNaturalDatesList() {
        System.out.println(SPACES + "❉⊱• This is the list keywords of natural dates: •⊰❉");
        System.out.println(SPACES + "  • Mon/Monday -> next Monday");
        System.out.println(SPACES + "  • Tue/Tuesday -> next Tuesday");
        System.out.println(SPACES + "  • Wed/Wednesday -> next Wednesday");
        System.out.println(SPACES + "  • Thu/Thursday -> next Thursday");
        System.out.println(SPACES + "  • Fri/Friday -> next Friday");
        System.out.println(SPACES + "  • Sat/Saturday -> next Saturday");
        System.out.println(SPACES + "  • Sun/Sunday -> next Sunday");
        System.out.println(SPACES + "  • Today -> today");
        System.out.println(SPACES + "  • tmr/Tomorrow -> tomorrow");
        System.out.println(SPACES + "  • eom/End-of-month -> end of this month");
        System.out.println(SPACES + "  • eoy/End-of-year -> end of this year");
        System.out.println(LINE);
    }
}