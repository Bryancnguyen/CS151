import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.lang.String;import java.util.ArrayList;

/**
 * Created by bryannguyen 4/1/15.
 * Model Class
 */

public class Model
{
    private View area;
    private ArrayList<ChangeListener> listeners;
    private ArrayList<String> textInField;

    public Model()
    {
        listeners = new ArrayList<ChangeListener>();
        textInField = new ArrayList<String>();
    }

    public void attach(View area1)
    {
        area = area1;
    }

    public ArrayList<String> getInfo()
    {
        return textInField;
    }

    public void addText(String text)
    {
        textInField.add(text);
        ChangeEvent event;
        event = new ChangeEvent(this);
        for(ChangeListener listener: listeners)
        {
            listener.stateChanged(event);
        }
    }

    public void addActionListener(ChangeListener c)
    {
        listeners.add(c);
    }
}
