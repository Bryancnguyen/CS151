import javax.swing.JTextArea;import java.lang.String;

/**
 * Created by bryannguyen on 4/1/15.
 * View Class
 */
public class View extends JTextArea
{
    private Model i;
    private JTextArea textArea;

    public View()
    {
        i = new Model();
        textArea = new JTextArea();
    }

    public void addInfo(Model i)
    {
        this.i = i;
    }

    public void showText()
    {
        int textCount = i.getInfo().size() - 1;
        String text = i.getInfo().get(textCount) + "\n";
        this.append(text);
    }

}
