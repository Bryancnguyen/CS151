import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;


/**
 * @author bryannguyen
 * Model class that contains the data structure.
 */
public class Model
{
    private HashMap<GregorianCalendar, ArrayList<Event>> Events;
    private int[][] Calendar; //empty Array
    private ArrayList<Day> EventDays = new ArrayList<Day>();
    private int j = 0;
    private Day[][] EventCalendar;
    private String[][] EventStored;
    static int maxWeekOfMonth, maxDayOfWeek, fdayOfWeek, AllDaysOfMonth, fDayOfMonth, realMonth, realYear,
            currentMonth, currentYear, indexOfFirst, currentDay, weekDayInfo, date, currentDate;

    public Model()
    {
        Events = new HashMap<GregorianCalendar, ArrayList<Event>>();
        GregorianCalendar cal = new GregorianCalendar();
        realMonth = cal.get(GregorianCalendar.MONTH);
        realYear = cal.get(GregorianCalendar.YEAR);
        maxWeekOfMonth = cal.getActualMaximum(GregorianCalendar.WEEK_OF_MONTH); // max weeks in month 4 or 5
        maxDayOfWeek = cal.getActualMaximum(GregorianCalendar.DAY_OF_WEEK); // maximum days in a week or 7
        fdayOfWeek = cal.get(GregorianCalendar.DAY_OF_WEEK); // returns weekday ex. Monday
        AllDaysOfMonth = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH); // returns maximum amount of days in the month
        fDayOfMonth = cal.get(GregorianCalendar.DAY_OF_MONTH); //returns the firstMonth
        realMonth = cal.get(GregorianCalendar.MONTH); //Returns current Month to Date
        realYear = cal.get(GregorianCalendar.YEAR); //Returns current Year to Date
        indexOfFirst = cal.get(GregorianCalendar.DAY_OF_WEEK) - 1; //Returns what WeekDay Index to start at
        date = cal.get(GregorianCalendar.DATE);
        currentMonth = realMonth; // setting month
        currentYear = realYear; // setting year
        currentDay = fdayOfWeek;
        currentDate = date;
        Day x = new Day(realMonth, 0, realYear);
        fillEventCalendar(x);
    }

    /**
     * Fills the calendar
     * @param d the days to fill the calendar with.
     */
    public void fillEventCalendar(Day d)
    {
        GregorianCalendar cal = new GregorianCalendar(d.getYear(), d.getMonth(), 1);
        maxWeekOfMonth = cal.getActualMaximum(GregorianCalendar.WEEK_OF_MONTH); // max weeks in month 4 or 5
        maxDayOfWeek = cal.getActualMaximum(GregorianCalendar.DAY_OF_WEEK); // maximum days in a week or 7
        fdayOfWeek = cal.get(GregorianCalendar.DAY_OF_WEEK); // returns weekday ex. Monday
        AllDaysOfMonth = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH); // returns maximum amount of days in the month
        fDayOfMonth = cal.get(GregorianCalendar.DAY_OF_MONTH); //returns the firstMonth
        realMonth = cal.get(GregorianCalendar.MONTH); //Returns current Month to Date
        realYear = cal.get(GregorianCalendar.YEAR); //Returns current Year to Date
        indexOfFirst = cal.get(GregorianCalendar.DAY_OF_WEEK) - 1; //Returns what WeekDay Index to start at
        currentMonth = realMonth; // setting month
        currentYear = realYear; // setting year
        currentDay = fDayOfMonth;
        EventCalendar = new Day[maxWeekOfMonth][maxDayOfWeek];
        EventStored = new String[maxWeekOfMonth][maxDayOfWeek];
        fdayOfWeek--;
        for (int m = 0; m < EventCalendar.length; m++) {
            while (fDayOfMonth <= AllDaysOfMonth && fdayOfWeek < maxDayOfWeek)
            {
                Day newDay = new Day(d.getMonth(), fDayOfMonth, d.getYear());
                EventCalendar[j][indexOfFirst] = newDay;
                fDayOfMonth++;
                fdayOfWeek++;
                indexOfFirst++;
            }
            j++;
            indexOfFirst = 0;
            fdayOfWeek = 0;
        }
        fDayOfMonth = 0;
        fdayOfWeek = 0;
        j = 0;
    }


    /**
     * Creates the event 
     * @param d the day to create the event on
     * @param e the event object
     * @throws ParseException
     */
    public void createEvent(Day d, Event e) throws ParseException
    {
        String dateString = e.getDate() + " " + e.getTime();
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        Date parsed = df.parse(dateString);
        GregorianCalendar x = new GregorianCalendar();
        x.setTime(parsed);
        Events.put(x, e);
        Day eventDay = new Day(d.getMonth(), d.getDay(), d.getYear());
        EventDays.add(eventDay);
        System.out.print(EventDays);
    }

    /**
     * Displays the current week information.
     * @param days the days to display
     * @param months the months to currently display.
     * @param weekInfo the information of the week to display.
     * @throws ParseException
     */
    public void todaysWeekinfo(int days, int months, int weekInfo) throws ParseException {
        int month = months + 1;
        int realweekInfo = weekInfo - 1;
        String[] allweekDays = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        String[] allmonths = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        System.out.println(allweekDays[realweekInfo] + " " + allmonths[months] + " " + days + ", " + currentYear);
        String dateString = month + "/" + currentDay + "/" + currentYear;
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        Date parsed = df.parse(dateString);
        GregorianCalendar x = (GregorianCalendar)GregorianCalendar.getInstance();
        x.setTime(parsed);
        if(Events.containsKey(x))
        {
            System.out.println(Events.get(x));
        }
    }


    /**
     * Has the actual month that is in the data structure.
     * @return the actual month the user is on.
     */
    public int realMonth()
    {
        return realMonth;
    }

    /**
     * Has the actual day that is in the data structure.
     * @return the actual day the user is on.
     */
    public int realDay()
    {
        return currentDay;
    }


    /**
     * Has the current month the user is on after interaction.
     * @return the month the user is interacting with.
     */
    public int getMonth()
    {
        return currentMonth;
    }


    /**
     * Adds month when user uses the add month button.
     */
    public void addMonth()
    {

        if (currentMonth == 11)
        {
            currentMonth = 0;
            currentYear += 1;
        }
        else
        {
            currentMonth += 1;
        }
        Day d = new Day(currentMonth, 1, currentYear);
    }

    /**
     * Subtracts the month when user uses the subtract month button.
     */
    public void subtractMonth()
    {
        if (currentMonth == 0)
        {
            currentMonth = 11;
            currentYear -= 1;
        } else {
            currentMonth -= 1;
        }
        Day d = new Day(currentMonth, 1, currentYear);
    }


    /**
     * Has the current year that the user is on after interaction.
     * @return the current year.
     */
    public int getYear()
    {
        return currentYear;
    }

    /**
     * gets the maximum days in a month for the user.
     * @return the amount of months in the current month.
     */
    public int getMaxDayOfMonth()
    {
        GregorianCalendar cal = new GregorianCalendar(currentYear, currentMonth, 1);
        int MaxDays = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
        return MaxDays;
    }

    /**
     * gets the first day of the actual week.
     * @return the first day of the week.
     */
    public int getFdayOfWeek()
    {
        GregorianCalendar cal = new GregorianCalendar(currentYear, currentMonth, 1);
        int dayofWeek = cal.get(GregorianCalendar.DAY_OF_WEEK);
        return dayofWeek;
    }

    /**
     * Has the current date that the user is on.
     * @return the date the user is on.
     */
    public int getDate()
    {
        return currentDate;
    }

}
