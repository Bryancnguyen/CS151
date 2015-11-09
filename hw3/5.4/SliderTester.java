import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;

/**
 * Created by bryannguyen on 3/27/15.
 */
public class SliderTester
{
    private static JSlider SlideZoomer;
    private static final int width_Frame = 300;
    private static final int Slider_Maximum_Size = width_Frame;
    private static final int height_Frame = 300;
    private static final int Slider_Smallest_Size = 0;
    private static JLabel Label;
    private static Car Car;

    public static void main(String[] args)
    {
        JFrame frame = new JFrame();
        frame.setSize(width_Frame, height_Frame);
        Container frameContentPane = frame.getContentPane();

        frame.setLayout(new BoxLayout(frameContentPane, BoxLayout.Y_AXIS));
        SlideZoomer = new JSlider(Slider_Smallest_Size, Slider_Maximum_Size);
        SlideZoomer.addChangeListener(createSlideZoomerListener());

        Car = new Car(Slider_Maximum_Size);
        Label = new JLabel(Car);
        frame.add(SlideZoomer);
        frame.add(Label);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        Car.setIconWidth(SlideZoomer.getValue());
        frame.setVisible(true);
    }

    public static ChangeListener createSlideZoomerListener()
    {
        return new ChangeListener()
        {
            @Override
            public void stateChanged(ChangeEvent e)
            {
                Car.setIconWidth(SlideZoomer.getValue());
                Label.repaint();
            }
        };
    }
}
