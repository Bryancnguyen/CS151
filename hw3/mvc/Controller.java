import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.BorderLayout;import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;import java.lang.Override;
import java.lang.String;

/**
 * Created by bryannguyen on 4/1/15.
 * Controller Class
 */

public class Controller extends JFrame
{
    final View area = new View();
    final private JFrame frame;
    final private Model i;
    final JTextField newField;
    final JButton newButton;

    public Controller()
    {
        frame = new JFrame();
        i = new Model();
        newField = new JTextField(50);
        newButton = new JButton();

        area.addInfo(i);
        newButton.setText("Add Text!");
        i.attach(area);

        i.addActionListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent changeEvent) {
                area.showText();
            }
        });

        newButton.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                String x = newField.getText();
                i.addText(x);
                newField.setText(null);
            }
        });

        add(area, BorderLayout.CENTER);
        add(newButton, BorderLayout.NORTH);
        add(newField, BorderLayout.SOUTH);
    }

}
