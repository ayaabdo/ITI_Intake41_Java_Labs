package mypkg;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class DrawLine extends Applet {
    int x1,y1,x2,y2;
    public void init()
    {
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }
            @Override
            public void mousePressed(MouseEvent e) {
                getStartCoord(e);
            }
            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                getEndCoord(e);
            }
            @Override
            public void mouseMoved(MouseEvent e) {

            }
        });
    }
    public void paint(Graphics g)
    {
        g.drawLine(x1,y1,x2,y2);
    }
    public void getStartCoord(MouseEvent e)
    {
        x1 = e.getX();
        y1 = e.getY();
    }
    public void getEndCoord(MouseEvent e)
    {
        x2 = e.getX();
        y2 = e.getY();
        this.repaint();
    }


}
