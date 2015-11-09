import java.util.ArrayList;


/**
 * This class encapsulates an event
 * 
 * @author bryannguyen
 *
 */
public class Event extends ArrayList<Event> 
{
    private String Name;
    private String Date;
    private String Time;

    /**
     * An Event's information
     * 
     * @param Name The title of the event
     * @param Date The date of the event
     * @param Time The time of the event
     */
    public Event(String Name, String Date, String Time)
    {
        this.Name = Name;
        this.Date = Date;
        this.Time = Time;

    }

    /**
     * This method returns the name of the event
     * 
     * @return The name to return
     */
    public String getName()
    {
        return Name;
    }

    /**
     * This method returns the date of the event
     * 
     * @return The date to return
     */
    public String getDate()
    {
        return Date;
    }

    /**
     * This method returns the time of the event 
     * 
     * @return The time to return 
     */
    public String getTime()
    {
        return Time;
    }
    
    public String toString()
    {
        return Name + Date + Time;
    }

}
