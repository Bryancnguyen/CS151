
/**
 * This class encapsulates a day
 *
 * @author bryannguyen
 *
 */
public class Day {
    private int Month;
    private int Day;
    private int Year;

    /**
     * Date of the day
     *
     * @param Month The month entered
     * @param Day The day entered
     * @param Year The year entered
     */
    public Day(int Month, int Day, int Year)
    {
        this.Month = Month;
        this.Day = Day;
        this.Year = Year;
    }

    /**
     * This method returns the month entered by user
     *
     * @return The month to return
     */
    public int getMonth()
    {
        return Month;
    }
    /**
     * This method returns the day entered by user
     *
     * @return The day to return
     */
    public int getDay()
    {
        return Day;
    }
    /**
     * This method returns the year entered by user
     *
     * @return The year to return 
     */
    public int getYear()
    {
        return Year;
    }
}
