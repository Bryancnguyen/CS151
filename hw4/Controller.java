import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;


/**
 * @author bryannguyen
 * The controller that has a main and a view.
 */
public class Controller
{
    private View view;
    private Model model;
    private Point MousePoint;

    public Controller(final View view, final Model model)
    {
        this.view = view;
        this.model = model;
        this.view.addMonthActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.removeCurrentMonthLabel();
                model.addMonth();
                view.fillCal(model);
                view.addMonthLabel(model);
            }
        });

        this.view.subtractMonthActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.removeCurrentMonthLabel();
                model.subtractMonth();
                view.fillCal(model);
                view.addMonthLabel(model);
            }
        });


        this.view.createButtonActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.createButtonFrame();
            }
        });

        this.view.quitButtonActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.quitFrame();
            }
        });

        this.view.createEventActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String Month = view.getEventDate();
                String Name = view.getEventTitle();
                String split[] = Month.split("/");
                int Month1 = Integer.parseInt(split[0]);
                int Day = Integer.parseInt(split[1]);
                int Year = Integer.parseInt(split[2]);
                String Time = view.getEventTime1() + ":" + view.getEventTime2();
                Day addDay = new Day(Month1, Day, Year);
                Event addEvent = new Event(Name, Month, Time);
                try {
                    model.createEvent(addDay, addEvent);
                } catch (ParseException e1) {
                    e1.printStackTrace();
                }
            }
        });

        this.view.addMouseListener(new MouseAdapter()
        {
            public void mousePressed(MouseEvent event)
            {
                MousePoint = event.getPoint();
                double dx = MousePoint.getX();
                double dy = MousePoint.getY();
                //view.gettable(dx, dy);
                System.out.println(MousePoint);
            }
        });

        
            }

}

   
