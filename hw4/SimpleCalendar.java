import javax.swing.*;


/**
 * @author bryannguyen
 * Main class
 */
public class SimpleCalendar
{
    public static void main (String args[])
    {
        View calendar = new View();
        Model i = new Model();
        Controller c = new Controller(calendar, i);
        calendar.fillCal(i);
        calendar.addMonthLabel(i);
    }
}
