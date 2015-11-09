import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * This class manages the calendar
 * @author bryannguyen
 *
 */
public class CalendarManager {
    private HashMap<GregorianCalendar, ArrayList<Event>> Events;
    private int[][] Calendar; //empty Array
    private ArrayList<Day> EventDays = new ArrayList<Day>();
    private int j = 0;
    private Day[][] EventCalendar;
    private String[][] EventStored;
    static int maxWeekOfMonth, maxDayOfWeek, fdayOfWeek, AllDaysOfMonth, fDayOfMonth, realMonth, realYear,
            currentMonth, currentYear, indexOfFirst, currentDay, weekDayInfo;

    public CalendarManager() 
    {
        Events = new HashMap<GregorianCalendar, ArrayList<Event>>();
        GregorianCalendar cal = new GregorianCalendar();
        realMonth = cal.get(GregorianCalendar.MONTH);
        realYear = cal.get(GregorianCalendar.YEAR);
        fillCalendar(realMonth, realYear);
        Day x = new Day(realMonth, 0, realYear);
        fillEventCalendar(x);
    }


    /**
     * Method that fills the calendar based on month and year
     * 
     * @param month The month to add
     * @param year The year to add
     */
    public void fillCalendar(int month, int year) {
        GregorianCalendar cal = new GregorianCalendar(year, month, 1);
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
        Calendar = new int[maxWeekOfMonth][maxDayOfWeek];
        fdayOfWeek--;
        for (int m = 0; m < Calendar.length; m++) {
            while (fDayOfMonth <= AllDaysOfMonth && fdayOfWeek < maxDayOfWeek) {
                Calendar[j][indexOfFirst] = fDayOfMonth;
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
     * Method that fills calendar with days
     * @param d The Day object to add
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
        for (int m = 0; m < Calendar.length; m++) {
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
     * Method that displays weekday headers
     */
    public void displayWeekdays() {
        String[] headers = {"Su", "Mo", "Tu", "We", "Th", "Fr", "Sa"}; //All headers
        for (int i = 0; i < 7; i++) {
            System.out.print(headers[i] + " ");
        }
    }

    /**
     * Method that displays month headers based on month
     * @param months The month to display
     * @return
     */
    public String monthDisplay(int months)
    {
        String[] allMonths = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        return allMonths[months];
    }

    /**
     * Method that prints the menu calendar to the user
     */
    public void printCalendar() {
        GregorianCalendar cal = new GregorianCalendar();
        System.out.println(monthDisplay(realMonth) + " " + realYear);
        displayWeekdays();
        System.out.print("\n");
        int todaysDate = cal.get(GregorianCalendar.DAY_OF_MONTH);
        for (int row = 0; row < Calendar.length; row++)
        {
            for (int column = 0; column < Calendar[0].length; column++)
            {
                if (Calendar[row][column] == todaysDate && realMonth == currentMonth)
                {
                    Calendar[row][column] = todaysDate;
                    System.out.print("[" + Calendar[row][column] + "]" + "");
                }
                if (Calendar[row][column] == todaysDate && realMonth != currentMonth)
                {
                    Calendar[row][column] = todaysDate;
                    System.out.print(Calendar[row][column] + " ");
                }
                else if (Calendar[row][column] != todaysDate && Calendar[row][column] == 0)
                {
                    System.out.print("  ");
                }
                else if (Calendar[row][column] != todaysDate && Calendar[row][column] < 10)
                {
                    System.out.print(" " + Calendar[row][column] + " ");
                }
                else if (todaysDate != Calendar[row][column])
                {
                    System.out.print(Calendar[row][column] + " ");
                }
            }
            System.out.print("\n");
        }
    }

    /**
     * Method that displays the view by month calendar to the user
     */
    public void navigateCalendar()
    {
        GregorianCalendar cal = new GregorianCalendar();
        realMonth = cal.get(GregorianCalendar.MONTH);
        realYear = cal.get(GregorianCalendar.YEAR);
        System.out.println(monthDisplay(currentMonth) + " " + realYear);
        displayWeekdays();
        System.out.print("\n");
        int todaysDate = cal.get(GregorianCalendar.DAY_OF_MONTH);
        int EventMonths = 0;
        for (int row = 0; row < EventCalendar.length; row++)
        {
            for (int column = 0; column < EventCalendar[0].length; column++)
            {
                if (EventCalendar[row][column] != null)
                {
                    if (EventCalendar[row][column].getDay() == todaysDate && realMonth == currentMonth)
                    {
                        for (int i = 0; i < EventDays.size(); i++)
                        {
                            EventMonths = EventDays.get(i).getMonth();
                            if (EventDays.get(i).getDay() == EventCalendar[row][column].getDay() && EventMonths == currentMonth + 1)
                            {
                                EventStored[row][column] = "[" + EventCalendar[row][column].getDay() + "]" + "";
                                break;
                            }
                            else
                            {
                                EventStored[row][column] = " " + EventCalendar[row][column].getDay() + " " + "";
                            }
                        }
                        if(EventStored[row][column] == null)
                        {
                            EventStored[row][column] = " " + EventCalendar[row][column].getDay() + " ";
                        }
                        System.out.print(EventCalendar[row][column].getDay() + " ");
                    }
                    if (EventCalendar[row][column].getDay() == todaysDate && realMonth != currentMonth)
                    {
                        for (int i = 0; i < EventDays.size(); i++)
                        {
                            EventMonths = EventDays.get(i).getMonth();
                            if (EventDays.get(i).getDay() == EventCalendar[row][column].getDay() && EventMonths == currentMonth +1)
                            {
                                EventStored[row][column] = "[" + EventCalendar[row][column].getDay() + "]" + "";
                                break;
                            }
                            else
                            {
                                EventStored[row][column] = " " + EventCalendar[row][column].getDay() + " " + "";
                            }
                        }
                        if(EventStored[row][column] == null)
                        {
                            EventStored[row][column] = " " + EventCalendar[row][column].getDay() + " ";
                        }
                        System.out.print(" " + EventCalendar[row][column].getDay() + " ");
                    }
                    if (EventCalendar[row][column].getDay() != todaysDate && EventCalendar[row][column].getDay() == 0) {
                        for (int i = 0; i < EventDays.size(); i++)
                        {
                            EventMonths = EventDays.get(i).getMonth();
                            if (EventDays.get(i).getDay() == EventCalendar[row][column].getDay() && EventMonths == currentMonth + 1)
                            {
                                EventStored[row][column] = "[" + EventCalendar[row][column].getDay() + "]" + "";
                                break;
                            }
                            else
                            {
                                EventStored[row][column] = " " + EventCalendar[row][column].getDay() + " " + "";
                            }
                        }
                        System.out.print("  ");
                    }
                    else if (EventCalendar[row][column].getDay() != todaysDate && EventCalendar[row][column].getDay() < 10)
                    {
                        for (int i = 0; i < EventDays.size(); i++)
                        {
                            EventMonths = EventDays.get(i).getMonth();
                            if (EventDays.get(i).getDay() == EventCalendar[row][column].getDay() && EventMonths == currentMonth + 1)
                            {
                                EventStored[row][column] = "[" + EventCalendar[row][column].getDay() + "]" + "";
                                break;
                            }
                            else
                            {
                                EventStored[row][column] = " " + EventCalendar[row][column].getDay() + " ";
                            }
                        }
                        if(EventStored[row][column] == null)
                        {
                            EventStored[row][column] = " " + EventCalendar[row][column].getDay() + " ";
                        }
                        System.out.print(EventStored[row][column]);
                    }
                    else if (todaysDate != EventCalendar[row][column].getDay())
                    {
                        for (int i = 0; i < EventDays.size(); i++)
                        {
                            EventMonths = EventDays.get(i).getMonth();
                            if (EventDays.get(i).getDay() == EventCalendar[row][column].getDay() && EventMonths == currentMonth + 1)
                            {
                                EventStored[row][column] = "[" + EventCalendar[row][column].getDay() + "]" + "";
                                break;
                            }
                            else
                            {
                                EventStored[row][column] = EventCalendar[row][column].getDay() + " ";
                            }
                        }
                        if(EventStored[row][column] == null)
                        {
                            EventStored[row][column] = EventCalendar[row][column].getDay() + " ";
                        }
                        System.out.print(EventStored[row][column]);
                    }
                }
                else if (EventCalendar[row][column] == null)
                {
                    System.out.print("   ");
                }
            }
            System.out.print("\n");
        }
    }


    /**
     * Method that subtracts the month of the view month calendar
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
        fillEventCalendar(d);
        navigateCalendar();
    }

 
    /**
     * Method that adds the month of the view month calendar
     */
    public void addMonth() {

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
        fillEventCalendar(d);
        navigateCalendar();
    }


    /**
     * This method displays the current day information of the current day
     * 
     * This method will throw an exception if the entered date is not in the correct format
     * 
     * @param days The day to display
     * @param months The month to display
     * @param weekInfo The weekday the day starts on
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
     * This method gets the current day information
     * 
     * This method will throw an exception if entered date is not in the correct format
     * 
     * @throws ParseException
     */
    public void currentDay() throws ParseException {
        GregorianCalendar cal = new GregorianCalendar();
        int todays = cal.get(GregorianCalendar.DATE);
        currentDay = todays;
        int weekInfo = cal.get(GregorianCalendar.DAY_OF_WEEK);
        todaysWeekinfo(todays, currentMonth, weekInfo);
    }


    /**
     * This method subtracts the day info for day view
     * 
     * This method will throw an exception if entered day is not in the correct format 
     * 
     * @throws ParseException
     */
    public void subtractDay() throws ParseException {
        if(currentDay == 1)
        {
            currentMonth -= 1;
            GregorianCalendar x = new GregorianCalendar(currentYear, currentMonth, 1);
            AllDaysOfMonth = x.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
            weekDayInfo = x.getActualMaximum(GregorianCalendar.DAY_OF_WEEK);
            weekDayInfo -= 1;
            currentDay = AllDaysOfMonth;

        }
        else
        {
            currentDay -= 1;
            GregorianCalendar x = new GregorianCalendar(currentYear, currentMonth, currentDay);
            weekDayInfo = x.get(GregorianCalendar.DAY_OF_WEEK);
            weekDayInfo -= 1;
        }
        subtractWeekDayInfo(weekDayInfo, currentMonth);
    }


    /**
     * This method adds the day info for day view
     * 
     * This method will throw an exception if entered day is not in the correct format
     * 
     * @throws ParseException
     */
    public void addDay() throws ParseException {
        if(currentDay == AllDaysOfMonth)
        {
            currentMonth += 1;
            currentDay = fDayOfMonth;
        }
        else
        {
            currentDay += 1;
            GregorianCalendar x = new GregorianCalendar(currentYear, currentMonth, currentDay);
            weekDayInfo = x.get(GregorianCalendar.DAY_OF_WEEK);
            weekDayInfo -= 1;
        }
        addWeekDayInfo(weekDayInfo, currentMonth);
    }

    
    /**
     * This method displays the added day view information based on the addDay() method
     * 
     * This method throws an exception if the entered day is not in the correct format
     * 
     * @param days The day to display
     * @param months The month to display
     * 
     * @throws ParseException
     */
    public void addWeekDayInfo(int days, int months) throws ParseException {
        int month = months + 1;
        String[] allweekDays = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        String[] allmonths = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        System.out.println(allweekDays[days] + " " + allmonths[months] + " " + (currentDay) + ", " + currentYear);
        String dateString = month + "/" + currentDay + "/" + currentYear;
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        Date parsed = df.parse(dateString);
        GregorianCalendar x = (GregorianCalendar)GregorianCalendar.getInstance();
        x.setTime(parsed);
        if(Events.containsKey(x))
        {
            Event e = (Event) Events.get(x);
            System.out.println(e.getName() + " " + e.getDate() + " " + e.getTime());
        }
    }


    /**
     * This method displays the subtracted day view information based on the subtractDay() method
     * 
     * This method throws an exceptionif the entered day is not in the correct format
     * 
     * @param days The day to display
     * @param months The month to display
     * 
     * @throws ParseException
     */
    public void subtractWeekDayInfo(int days, int months) throws ParseException {
        int month = months + 1;
        String[] allweekDays = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        String[] allmonths = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        System.out.println(allweekDays[days] + " " + allmonths[months] + " " + currentDay + ", " + currentYear);
        String dateString = month + "/" + currentDay + "/" + currentYear;
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        Date parsed = df.parse(dateString);
        GregorianCalendar x = (GregorianCalendar)GregorianCalendar.getInstance();
        x.setTime(parsed);
        if(Events.containsKey(x))
        {
            Event e = (Event) Events.get(x);
            System.out.println(e.getName() + " " + e.getDate() + " " + e.getTime());
        }
    }


    /**
     * This method creates an event
     * 
     * This method throws an exception if date entered is not in the correct format
     * 
     * @param d The day the event is on
     * @param e The event to add
     * 
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
    }

    /**
     * This method goes to the day entered and displays event on the day
     * 
     * This method throws an exception if date entered is not in the correct format
     * 
     * @param d The day to go to
     * @throws ParseException
     */
    public void goToEvent(Day d) throws ParseException
    {
        String dateString = d.getMonth() + "/" + d.getDay() + "/" + d.getYear();
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        Date parsed = df.parse(dateString);
        GregorianCalendar x = (GregorianCalendar)GregorianCalendar.getInstance();
        x.setTime(parsed);
        if(Events.containsKey(x))
        {
            int findWeekDay = x.get(GregorianCalendar.DAY_OF_WEEK);
            currentDay = d.getDay();
            currentYear = d.getYear();
            displayGoToEvent(findWeekDay, d.getMonth());
            Event e = (Event) Events.get(x);
            System.out.println(e.getName() + " " + e.getDate() + " " + e.getTime());
        }
    }


    /**
     * This method displays the header for the goToEvent() method
     * 
     * @param days The day to display
     * @param months The month to display
     */
    public void displayGoToEvent(int days, int months)
    {
        int today = days - 1;
        int month = months - 1;
        String[] allweekDays = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        String[] allmonths = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        System.out.println(allweekDays[today] + " " + allmonths[month] + " " + currentDay + ", " + currentYear);
    }

    /**
     * This method displays the list of events for
     * 
     * This method throws an exception if the dates are not in the correct format
     * 
     * @throws ParseException
     */
    public void eventList() throws ParseException
    {
        List sortedKeys = new ArrayList(Events.keySet());
        Collections.sort(sortedKeys);

        for(int i = 0; i < sortedKeys.size(); i++)
        {
            if (Events.containsKey(sortedKeys.get(i)))
            {
                Event e = (Event) Events.get(sortedKeys.get(i));
                String dateString = e.getDate() + " " + e.getTime();
                DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                Date parsed = df.parse(dateString);
                GregorianCalendar x = (GregorianCalendar)GregorianCalendar.getInstance();
                x.setTime(parsed);
                System.out.println(x.get(GregorianCalendar.YEAR));
                System.out.println(e.getName() + " " + e.getDate() + " " + e.getTime());
            }
        }
    }

    /**
     * This method deletes a day event
     * 
     * This method throws an exception if entered date is not in the correct format
     * 
     * @param d The day to delete
     * 
     * @throws ParseException
     */
    public void selectDeleteEvent(Day d) throws ParseException {
        String dateString = d.getMonth() + "/" + d.getDay() + "/" + d.getYear();
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        Date parsed = df.parse(dateString);
        GregorianCalendar x = (GregorianCalendar)GregorianCalendar.getInstance();
        x.setTime(parsed);
        if(Events.containsKey(x))
        {
            Events.remove(x);
        }
        for(int i = 0; i < EventDays.size(); i++)
        {
            if(d.getDay() == EventDays.get(i).getDay() && d.getMonth() == EventDays.get(i).getMonth() && d.getYear() == EventDays.get(i).getYear())
            {
                EventDays.remove(i);
            }
        }
    }

    /**
     * This method simply deletes all events
     */
    public void deleteAll()
    {
        Events.clear();
        EventDays.clear();
        for(int row = 0; row < EventCalendar.length; row++)
        {
            for(int column = 0; column < EventCalendar[0].length; column++)
            {
                EventCalendar[row][column] = null;
                Day d = new Day(currentMonth, currentDay, currentYear);
                fillEventCalendar(d);
            }
        }
    }

    /**
     * This method loads events into the hashmap
     * 
     * This method throws an exception if the events are not in the correct format
     * 
     * @param filename The file to load
     * @throws ParseException
     */
    public void loadFile(File filename) throws ParseException {
        try
        {
            Scanner in = new Scanner(filename);

            while(in.hasNext())
            {
                String dateString = in.nextLine();
                String Title = dateString.substring(0, dateString.indexOf(" "));
                String Date = dateString.substring(dateString.indexOf(" ") + 1, dateString.length());
                String Time = Date.substring(Date.indexOf(" ") + 1, Date.length());
                String EventDate = Date.substring(0, Date.indexOf(" "));
                DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                Date parsed = df.parse(Date);
                GregorianCalendar x = (GregorianCalendar)GregorianCalendar.getInstance();
                x.setTime(parsed);
                Event e = new Event(Title, EventDate, Time);
                Events.put(x, e);
                int month = x.get(GregorianCalendar.MONTH);
                Day d = new Day(month , x.get(GregorianCalendar.DATE), x.get(GregorianCalendar.YEAR));
                EventDays.add(d);
            }

        }
        catch(FileNotFoundException e)
        {
            System.out.print("Could not find File");
        }
    }

    /**
     * This method writes out to file
     * 
     * @param filename The file to write out to
     * @throws ParseException
     */
    public void writeToFile(File filename) throws ParseException
    {
        try {
            PrintWriter out = new PrintWriter(filename);
            {
                {
                    List sortedKeys = new ArrayList(Events.keySet());
                    Collections.sort(sortedKeys);

                    for(int i = 0; i < sortedKeys.size(); i++)
                    {
                        if (Events.containsKey(sortedKeys.get(i)))
                        {
                            Event e = (Event) Events.get(sortedKeys.get(i));
                            String dateString = e.getDate() + " " + e.getTime();
                            DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                            Date parsed = df.parse(dateString);
                            GregorianCalendar x = new GregorianCalendar();
                            x.setTime(parsed);
                            out.write(e.getName() + " " + e.getDate() + " " + e.getTime());
                            out.write("\n");
                        }
                    }
                    out.close();
                }
            }
        }
        catch(FileNotFoundException e)
        {
            System.out.println("Unable to save information");
        }
    }

}
