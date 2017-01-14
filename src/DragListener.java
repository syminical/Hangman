
//I did not create this
//I got this code from https://tips4java.wordpress.com/2009/06/14/moving-windows/

import javax.swing.event.*;
import java.awt.event.*;
import java.awt.*;

public class DragListener extends MouseInputAdapter
{
    Point location;
    MouseEvent pressed;
 
    public void mousePressed(MouseEvent me)
    {
        pressed = me;
    }
 
    public void mouseDragged(MouseEvent me)
    {
        Component component = me.getComponent();
        location = component.getLocation(location);
        int x = location.x - pressed.getX() + me.getX();
        int y = location.y - pressed.getY() + me.getY();
        component.setLocation(x, y);
     }
}