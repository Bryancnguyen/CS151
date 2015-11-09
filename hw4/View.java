import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;


/**
 * @author bryannguyen
 * The GUI of that is displayed to the user.
 */
public class View extends JFrame
{
    final private JFrame frame;
    final private Container pane;
    private Model i;
    private String month;
    static JLabel labelMonth, labelDay;
    static JButton prevButton, nextButton, createButton, quitButton, create;
    static JTable calendarTable;
    static DefaultTableModel tableModel; //Table model
    static JScrollPane calendarScrollPane; //The scrollpane
    static JPanel calendarPanel, dayViewPanel;
    static JTextArea Events;
    private String EventTime1, EventTime2, EventTitle, EventDate;
    static JTextArea createEvent, timeField1, timeField2, EventNameField;

    public View()
    {
        frame = new JFrame("Bryan's Calendar");
        frame.setSize(900, 400);
        pane = frame.getContentPane();
        pane.setLayout(null);
        i = new Model();
        Events = new JTextArea();
        String day = getDay(i.getFdayOfWeek());
        labelDay = new JLabel (day + " " + i.getMonth() + "/" + i.getDate());
        prevButton = new JButton ("<");
        nextButton = new JButton (">");
        createButton = new JButton ("CREATE");
        quitButton = new JButton ("QUIT");
        tableModel = new DefaultTableModel(){public boolean isCellEditable(int rowIndex, int mColIndex){return false;}};
        calendarTable = new JTable(tableModel);
        calendarScrollPane = new JScrollPane(calendarTable);
        dayViewPanel = new JPanel(null);
        calendarPanel = new JPanel(null);
        create = new JButton("Save");

        pane.add(dayViewPanel);
        dayViewPanel.add(labelDay);
        dayViewPanel.add(Events);
        dayViewPanel.add(createButton);
        dayViewPanel.add(quitButton);

        pane.add(calendarPanel);
        calendarPanel.add(prevButton);
        calendarPanel.add(nextButton);
        calendarPanel.add(calendarScrollPane);



        //Set bounds
        calendarPanel.setBounds(0, 0, 320, 335);
        dayViewPanel.setBounds(330, 0, 600, 335);
        Events.setBounds(Events.getPreferredSize().width/2, 50, 550, 360);
        labelDay.setBounds(270- labelDay.getPreferredSize().width/2, 25, 150, 25);
        //Buttons

        createButton.setBounds(0,20,100,25);
        prevButton.setBounds(10, 25, 50, 25);
        nextButton.setBounds(260, 25, 50, 25);
        quitButton.setBounds(440, 20, 100, 25);

        calendarScrollPane.setBounds(10, 50, 300, 250);
        //Make frame visible
        frame.setResizable(false);
        frame.setVisible(true);


        //Add headers
        String[] headers = {"S", "M", "T", "W", "T", "F", "S"}; //All headers
        for (int i=0; i<7; i++)
        {
            tableModel.addColumn(headers[i]);
        }

        //Table Alignment (Make sure to change);
        calendarTable.getParent().setBackground(calendarTable.getBackground()); //Set background

        //No resize/reorder
        calendarTable.getTableHeader().setResizingAllowed(false);
        calendarTable.getTableHeader().setReorderingAllowed(false);

        //Single cell selection
        calendarTable.setColumnSelectionAllowed(true);
        calendarTable.setRowSelectionAllowed(true);
        calendarTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        //Set row/column count
        calendarTable.setRowHeight(38);
        tableModel.setColumnCount(7);
        tableModel.setRowCount(6);

        //Add Listeners
    }


    /**
     * method that displays Month label to user.
     * @param i the month to display.
     */
    public void addMonthLabel(Model i)
    {
        String months = getMonth(i.getMonth());
        labelMonth = new JLabel(months);
        calendarPanel.add(labelMonth);
        labelMonth.setBounds(160- labelMonth.getPreferredSize().width/2, 25, 160, 25);
    }

    /**
     * Method that removes and updates the new month based.
     */
    public void removeCurrentMonthLabel()
    {
        calendarPanel.remove(labelMonth);
        calendarPanel.validate();
        calendarPanel.repaint();
    }


    /**
     * fills the Calendar with numbers
     * @param i the Model to get information of days, months, and year from.
     */
    public void fillCal(Model i)
    {
        for (int y = 0; y < 6; y++)
        {
            for (int j=0; j< 7; j++)
            {
                tableModel.setValueAt(null, y, j);
            }
        }
        int NumberOfDays = i.getMaxDayOfMonth();
        int DayOfWeek = i.getFdayOfWeek();
        JLabel xCord = new JLabel(String.valueOf(NumberOfDays));
        for (int x = 1; x <= NumberOfDays; x++)
        {
            int row = new Integer((x+ DayOfWeek - 2) / 7);
            int column = (x + DayOfWeek - 2) % 7;
            if(x == i.getDate() && i.getMonth() == i.realMonth())
            {
                String y = "[" + x + "]";
                tableModel.setValueAt(y, row, column);
            }
            else {
                tableModel.setValueAt(x, row, column);
            }
        }
    }

    /**
     * Creates the add month listener
     * @param l the listener to add.
     */
    public void addMonthActionListener(ActionListener l)
    {
        nextButton.addActionListener(l);
    }

    /**
     * Creates the subtract month listener
     * @param l the listener to add.
     */
    public void subtractMonthActionListener(ActionListener l)
    {
        prevButton.addActionListener(l);
    }

    /**
     * Creates the listener for creating an event on the main screen.
     * @param l the listener to add.
     */
    public void createButtonActionListener(ActionListener l) {
        createButton.addActionListener(l);
    }

    /**
     * Creates the listener for quitting an event.
     * @param l the listener to add.
     */
    public void quitButtonActionListener(ActionListener l) {
        quitButton.addActionListener(l);
    }

    /**
     * Creates the listener for creating an event on the create event screen.
     * @param l the listener to add.
     */
    public void createEventActionListener(ActionListener l)
    {
        create.addActionListener(l);
    }

    public void addMouseListener(MouseListener m)
    {
        calendarTable.addMouseListener(m);
    }

    /**
     * gets the month that the user is currently on.
     * @param Month the index of the month to display.
     * @return the string of the month.
     */
    public String getMonth(int Month)
    {
        String[] months =  {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        String realMonth = months[Month] + " " + i.getYear();
        return realMonth;
    }



    /**
     * Gets the day the user is on.
     * @param Day the index of the day.
     * @return the string of the day.
     */
    public String getDay(int Day)
    {
        int dayIndex = Day;
        String[] days = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        String realDay = days[dayIndex];
        return realDay;
    }


    /**
     * Creates the initial frame for creating an event.
     */
    public void createButtonFrame()
    {
        JFrame createFrame = new JFrame();
        createFrame.setSize(560,200);
        Container pane2;
        pane2 = createFrame.getContentPane();
        JPanel createPanel = new JPanel(null);
        createEvent = new JTextArea("MM/DD/YYYY");
        timeField1 = new JTextArea("00:00");
        timeField2 = new JTextArea("00:00");
        EventNameField = new JTextArea("UNTILED NAME");
        JLabel EnterEventLabel = new JLabel("Enter Event Name:");
        JLabel EnterDateLabel = new JLabel("Date:");
        JLabel EnterStartLabel = new JLabel("Start:");
        JLabel EnterEndLabel = new JLabel("End:");
        JLabel toLabel = new JLabel("to");
        pane2.add(createPanel);
        createPanel.add(createEvent);
        createPanel.add(timeField1);
        createPanel.add(EventNameField);
        createPanel.add(timeField2);
        createPanel.add(create);
        createPanel.add(EnterEventLabel);
        createPanel.add(EnterDateLabel);
        createPanel.add(EnterStartLabel);
        createPanel.add(EnterEndLabel);
        createPanel.add(toLabel);

        createPanel.setBounds(0, 0, 400, 200); //panel
        createEvent.setBounds(20, 90, 100, 25); //Date field
        timeField1.setBounds(150, 90, 80, 25);  //Time field
        timeField2.setBounds(280, 90, 80, 25); //Time field 2
        create.setBounds(380, 100, 150, 50); //Create Button

        EnterStartLabel.setBounds(150, 65, 80, 25);
        EnterEndLabel.setBounds(280, 65, 80, 25);
        toLabel.setBounds(250, 90, 80, 25);
        EnterDateLabel.setBounds(20, 65, 80, 25);
        EventNameField.setBounds(20, 30, 300, 25); //Event Name Field
        EnterEventLabel.setBounds(20, 5, 150, 25);




        createFrame.setVisible(true);
        createFrame.setResizable(false);
    }

    /**
     * gets the entered name of the event.
     * @return the title of the event.
     */
    public String getEventTitle()
    {
        EventTitle = EventNameField.getText();
        return EventTitle;
    }

    /**
     * gets the time of the event the user starts at.
     * @return the event time. 
     */
    public String getEventTime1()
    {
        EventTime1 = timeField1.getText();
        return EventTime1;
    }

    /**
     * gets the time of the event the user ends at.
     * @return the event time.
     */
    public String getEventTime2()
    {
        EventTime2 = timeField2.getText();
        return EventTime2;
    }

    /**
     * gets the event date the user wants.
     * @return the date of the event.
     */
    public String getEventDate()
    {
        EventDate = createEvent.getText();
        return EventDate;
    }


    /**
     * Closes the program.
     */
    public void quitFrame()
    {

        System.exit(0);
    }
}
