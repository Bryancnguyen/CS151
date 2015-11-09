import java.io.File;
import java.io.FileNotFoundException;
import java.lang.*;
import java.text.ParseException;
import java.util.GregorianCalendar;
import java.util.Scanner;

/**
 * A class that reads user input from file "input.txt"
 * 
 * These inputs are used to display a calendar and menu
 * @author bryannguyen
 *
 */
public class MyCalendarTester
{
    public static void main(String[] args) throws ParseException
    {
        try {
        	String file = args[0];
            File filename = new File(file);
            File newFile = new File("events.txt");
            boolean checkInputFile = filename.exists();
            CalendarManager Calendar = new CalendarManager();
            if(checkInputFile)
            {
                Scanner in = new Scanner(filename);
                while (in.hasNext()) {
                    Calendar.printCalendar();
                    System.out.println("Select one of the following options: ");
                    System.out.println("[L]oad [V]iew by [C]reate [G]o to [E]vent list [D]elete [Q]uit");
                    String choice = in.nextLine();
                    if (choice.equals("L")) {
                        boolean checkExistingFile = newFile.exists();
                        if (checkExistingFile) {
                            Calendar.loadFile(newFile);
                        }
                        Calendar.printCalendar();
                        System.out.println("Select one of the following options: ");
                        System.out.println("[L]oad [V]iew by [C]reate [G]o to [E]vent list [D]elete [Q]uit");
                    }
                    if (choice.equals("V")) {
                        System.out.println("[D]ay view or [M]view ? ");
                        choice = in.nextLine();
                        if (choice.equals("M")) {
                            Calendar.navigateCalendar();
                            System.out.println("[P]revious or [N]ext or [M]ain menu ?");
                            choice = in.nextLine();
                            while (choice.equals("P")) {
                                Calendar.subtractMonth();
                                System.out.println("[P]revious or [N]ext or [M]ain menu ?");
                                choice = in.nextLine();
                            }
                            while (choice.equals("N")) {
                                Calendar.addMonth();
                                System.out.println("[P]revious or [N]ext or [M]ain menu ?");
                                choice = in.nextLine();
                            }
                            if (choice.equals("M")) {
                                Calendar.printCalendar();
                                System.out.println("Select one of the following options: ");
                                System.out.println("[L]oad [V]iew by [C]reate [G]o to [E]vent list [D]elete [Q]uit");
                            }
                        }
                        if (choice.equals("D")) {
                            Calendar.currentDay();
                            System.out.println("[P]revious or [N]ext or [M]ain menu ?");
                            choice = in.nextLine();
                            while (choice.equals("P")) {
                                Calendar.subtractDay();
                                System.out.println("[P]revious or [N]ext or [M]ain menu ?");
                                choice = in.nextLine();
                            }
                            while (choice.equals("N")) {
                                Calendar.addDay();
                                System.out.println("[P]revious or [N]ext or [M]ain menu ?");
                                choice = in.nextLine();
                            }
                            if (choice.equals("M")) {
                                Calendar.printCalendar();
                                System.out.println("Select one of the following options: ");
                                System.out.println("[L]oad [V]iew by [C]reate [G]o to [E]vent list [D]elete [Q]uit");
                            }
                        }
                    }
                    if (choice.equals("C")) {
                        System.out.println("Title: ");
                        String Title = in.nextLine();
                        System.out.println("Date: ");
                        String Date = in.nextLine();
                        Scanner in3 = new Scanner(Date);
                        in3.useDelimiter("/");
                        int Month = in3.nextInt();
                        int Day = in3.nextInt();
                        int Year = in3.nextInt();
                        System.out.println("Time: ");
                        String Time = in.nextLine();
                        Day addDay = new Day(Month, Day, Year);
                        Event addEvent = new Event(Title, Date, Time);
                        Calendar.createEvent(addDay, addEvent);
                        Calendar.printCalendar();
                        System.out.println("Select one of the following options: ");
                        System.out.println("[L]oad [V]iew by [C]reate [G]o to [E]vent list [D]elete [Q]uit");
                    }
                    if (choice.equals("G")) {
                        System.out.println("Date: MM/DD/YYYY");
                        String Date = in.nextLine();
                        Scanner in3 = new Scanner(Date);
                        in3.useDelimiter("/");
                        int Month = in3.nextInt();
                        int Day = in3.nextInt();
                        int Year = in3.nextInt();
                        Day goToDay = new Day(Month, Day, Year);
                        Calendar.goToEvent(goToDay);
                        Calendar.printCalendar();
                        System.out.println("Select one of the following options: ");
                        System.out.println("[L]oad [V]iew by [C]reate [G]o to [E]vent list [D]elete [Q]uit");
                    }
                    if (choice.equals("E")) {
                        Calendar.eventList();
                        Calendar.printCalendar();
                        System.out.println("Select one of the following options: ");
                        System.out.println("[L]oad [V]iew by [C]reate [G]o to [E]vent list [D]elete [Q]uit");
                    }
                    if (choice.equals("D")) {
                        System.out.println("[S]elected or [A]ll ?");
                        choice = in.nextLine();
                        if (choice.equals("S")) {
                            String Date = in.nextLine();
                            Scanner in3 = new Scanner(Date);
                            in3.useDelimiter("/");
                            int Month = in3.nextInt();
                            int Day = in3.nextInt();
                            int Year = in3.nextInt();
                            Day deleteDay = new Day(Month, Day, Year);
                            Calendar.selectDeleteEvent(deleteDay);
                            Calendar.printCalendar();
                            System.out.println("Select one of the following options: ");
                            System.out.println("[L]oad [V]iew by [C]reate [G]o to [E]vent list [D]elete [Q]uit");
                        }
                        if (choice.equals("A")) {
                            Calendar.deleteAll();
                            Calendar.printCalendar();
                            System.out.println("Select one of the following options: ");
                            System.out.println("[L]oad [V]iew by [C]reate [G]o to [E]vent list [D]elete [Q]uit");
                        }
                    }
                    if (choice.equals("Q")) {
                        Calendar.writeToFile(newFile);
                    }
                }
            }
            else
            {
                Calendar.printCalendar();
                System.out.println("Select one of the following options: ");
                System.out.println("[L]oad [V]iew by [C]reate [G]o to [E]vent list [D]elete [Q]uit");
                Scanner in = new Scanner(args[0]);
                while (in.hasNext())
                {
                    String choice = in.nextLine();
                    if (choice.equals("L")) {
                        boolean checkExistingFile = newFile.exists();
                        if (checkExistingFile) {
                            Calendar.loadFile(newFile);
                        }
                        Calendar.printCalendar();
                        System.out.println("Select one of the following options: ");
                        System.out.println("[L]oad [V]iew by [C]reate [G]o to [E]vent list [D]elete [Q]uit");
                    }
                    if (choice.equals("V")) {
                        System.out.println("[D]ay view or [M]view ? ");
                        choice = in.nextLine();
                        if (choice.equals("M")) {
                            Calendar.navigateCalendar();
                            System.out.println("[P]revious or [N]ext or [M]ain menu ?");
                            choice = in.nextLine();
                            while (choice.equals("P")) {
                                Calendar.subtractMonth();
                                System.out.println("[P]revious or [N]ext or [M]ain menu ?");
                                choice = in.nextLine();
                            }
                            while (choice.equals("N")) {
                                Calendar.addMonth();
                                System.out.println("[P]revious or [N]ext or [M]ain menu ?");
                                choice = in.nextLine();
                            }
                            if (choice.equals("M")) {
                                Calendar.printCalendar();
                                System.out.println("Select one of the following options: ");
                                System.out.println("[L]oad [V]iew by [C]reate [G]o to [E]vent list [D]elete [Q]uit");
                            }
                        }
                        if (choice.equals("D")) {
                            Calendar.currentDay();
                            System.out.println("[P]revious or [N]ext or [M]ain menu ?");
                            choice = in.nextLine();
                            while (choice.equals("P")) {
                                Calendar.subtractDay();
                                System.out.println("[P]revious or [N]ext or [M]ain menu ?");
                                choice = in.nextLine();
                            }
                            while (choice.equals("N")) {
                                Calendar.addDay();
                                System.out.println("[P]revious or [N]ext or [M]ain menu ?");
                                choice = in.nextLine();
                            }
                            if (choice.equals("M")) {
                                Calendar.printCalendar();
                                System.out.println("Select one of the following options: ");
                                System.out.println("[L]oad [V]iew by [C]reate [G]o to [E]vent list [D]elete [Q]uit");
                            }
                        }
                    }
                    if (choice.equals("C")) {
                        System.out.println("Title: ");
                        String Title = in.nextLine();
                        System.out.println("Date: ");
                        String Date = in.nextLine();
                        Scanner in3 = new Scanner(Date);
                        in3.useDelimiter("/");
                        int Month = in3.nextInt();
                        int Day = in3.nextInt();
                        int Year = in3.nextInt();
                        System.out.println("Time: ");
                        String Time = in.nextLine();
                        Day addDay = new Day(Month, Day, Year);
                        Event addEvent = new Event(Title, Date, Time);
                        Calendar.createEvent(addDay, addEvent);
                        Calendar.printCalendar();
                        System.out.println("Select one of the following options: ");
                        System.out.println("[L]oad [V]iew by [C]reate [G]o to [E]vent list [D]elete [Q]uit");
                    }
                    if (choice.equals("G")) {
                        System.out.println("Date: MM/DD/YYYY");
                        String Date = in.nextLine();
                        Scanner in3 = new Scanner(Date);
                        in3.useDelimiter("/");
                        int Month = in3.nextInt();
                        int Day = in3.nextInt();
                        int Year = in3.nextInt();
                        Day goToDay = new Day(Month, Day, Year);
                        Calendar.goToEvent(goToDay);
                        Calendar.printCalendar();
                        System.out.println("Select one of the following options: ");
                        System.out.println("[L]oad [V]iew by [C]reate [G]o to [E]vent list [D]elete [Q]uit");
                    }
                    if (choice.equals("E")) {
                        Calendar.eventList();
                        Calendar.printCalendar();
                        System.out.println("Select one of the following options: ");
                        System.out.println("[L]oad [V]iew by [C]reate [G]o to [E]vent list [D]elete [Q]uit");
                    }
                    if (choice.equals("D")) {
                        System.out.println("[S]elected or [A]ll ?");
                        choice = in.nextLine();
                        if (choice.equals("S")) {
                            String Date = in.nextLine();
                            Scanner in3 = new Scanner(Date);
                            in3.useDelimiter("/");
                            int Month = in3.nextInt();
                            int Day = in3.nextInt();
                            int Year = in3.nextInt();
                            Day deleteDay = new Day(Month, Day, Year);
                            Calendar.selectDeleteEvent(deleteDay);
                            Calendar.printCalendar();
                            System.out.println("Select one of the following options: ");
                            System.out.println("[L]oad [V]iew by [C]reate [G]o to [E]vent list [D]elete [Q]uit");
                        }
                        if (choice.equals("A")) {
                            Calendar.deleteAll();
                            Calendar.printCalendar();
                            System.out.println("Select one of the following options: ");
                            System.out.println("[L]oad [V]iew by [C]reate [G]o to [E]vent list [D]elete [Q]uit");
                        }
                    }
                    if (choice.equals("Q")) {
                        Calendar.writeToFile(newFile);
                    }
                }
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.print("No File");
        }
    }
}
