package ketujuh;

import java.util.Enumeration;
import java.util.Vector;

public class latihan7 {
    public static void main(String[] args) {
        Enumeration days;
        Vector daysNames = new Vector();
        daysNames.add("Sunday");
        daysNames.add("Monday");
        daysNames.add("Tuesday");
        daysNames.add("Wednesday");
        daysNames.add("Thursday");
        daysNames.add("Friday");
        daysNames.add("Satyrday");
        days = daysNames.elements();
        while (days.hasMoreElements()) {
            System.out.println(days.nextElement());
            
        }
    }
}
