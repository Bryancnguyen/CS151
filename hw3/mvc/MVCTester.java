import java.lang.String;

/**
 * Created by bryannguyen on 4/1/15.
 */
public class MVCTester
{
    public static void main(String[] args)
    {
        Controller newField = new Controller();
        newField.setSize(100,250);
        newField.setDefaultCloseOperation(Controller.EXIT_ON_CLOSE);
        newField.setVisible(true);
    }
}
