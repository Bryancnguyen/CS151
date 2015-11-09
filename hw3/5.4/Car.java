import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
/**
 * Created by bryannguyen on 3/27/15.
 */
public class Car implements Icon
{
    private int carWidth;

    public Car(int iconWidth)
    {
        carWidth = iconWidth;
    }

    public void setIconWidth(int iconWidth)
    {
        carWidth = iconWidth;
    }

    public int getIconWidth()
    {
        return carWidth;
    }
    public int getIconHeight()
    {
        return carWidth / 2;
    }

    public void paintIcon(Component c, Graphics g, int x, int y)
    {
        Graphics2D g2 = (Graphics2D) g;
        Rectangle2D.Double carBody = new Rectangle2D.Double (x, y + carWidth/6, carWidth - 1, carWidth/6);

        Point2D.Double point_1 = new Point2D.Double(x + carWidth/6, y + carWidth/6);
        Point2D.Double point_2 = new Point2D.Double(x + carWidth/3, y);
        Point2D.Double point_3 = new Point2D.Double(x + carWidth * 2/3, y);
        Point2D.Double point_4 = new Point2D.Double(x + carWidth * 5/6, y + carWidth/6);

        Line2D.Double frontWindow = new Line2D.Double(point_1, point_2);
        Line2D.Double roofTop = new Line2D.Double(point_2, point_3);
        Line2D.Double rearWindow = new Line2D.Double(point_3, point_4);

        Ellipse2D.Double tire1 = new Ellipse2D.Double(x + carWidth/6, y + carWidth / 3, carWidth / 6, carWidth / 6);
        Ellipse2D.Double tire2 = new Ellipse2D.Double(x + carWidth * 2/3, y + carWidth/3, carWidth / 6, carWidth / 6);


        g2.setColor(Color.blue);
        g2.fill(carBody);
        g2.draw(frontWindow);
        g2.draw(roofTop);
        g2.draw(rearWindow);
        g2.fill(tire1);
        g2.fill(tire2);
    }
}
