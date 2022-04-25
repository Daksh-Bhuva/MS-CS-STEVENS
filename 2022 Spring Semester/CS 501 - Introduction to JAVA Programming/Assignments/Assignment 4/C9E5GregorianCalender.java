import java.util.GregorianCalendar;

public class C9E5GregorianCalender {
    public static void main(String[] args) {

        System.out.println("\n- This program uses the GregorianCalendar class in the java.util package, to obtain the year, month, and day of a date.");
        System.out.println("- The no-arg constructor constructs an instance for the current date,");
        System.out.println("- and the methods get(GregorianCalendar.YEAR), get(GregorianCalendar.MONTH), and get(GregorianCalendar.DAY_OF_MONTH) return the year, month, and day.");
        System.out.println("\n- This program performs two tasks:");
        System.out.println(" ^ Displays the current year, month, and day.");
        System.out.println(" ^ Displays the year, month, and day since January 1, 1970 after 1234567898765 milliseconds time is elapsed.\n");

        GregorianCalendar GC = new GregorianCalendar();

        System.out.println("* Current Date:");
        System.out.println("Year: " + GC.get(GregorianCalendar.YEAR) + " | Month: " + (GC.get(GregorianCalendar.MONTH) + 1) + " | Day: " + GC.get(GregorianCalendar.DATE));

        GC.setTimeInMillis(1234567898765L);

        System.out.println("\n* Date 1234567898765 milliseconds after 1/1/1970 :");
        System.out.println("Year: " + GC.get(GregorianCalendar.YEAR) + " | Month: " + GC.get(GregorianCalendar.MONTH)+ " | Day: " + GC.get(GregorianCalendar.DATE));
    }
}
