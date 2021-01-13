package mypkg;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class DrawLines extends Applet {
    int[] x1;
    int[] y1;
    int[] x2;
    int[] y2;
    int cnt ;
    public void init()
    {
        cnt = -1;
        x1 = new int[20]; y1 = new int[20];
        x2 = new int[20]; y2 = new int[20];
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }
            @Override
            public void mousePressed(MouseEvent e) {
                cnt++;
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
        for(int i = 0;i <= cnt; ++i) {
            g.drawLine(x1[i], y1[i], x2[i], y2[i]);
        }
    }
    void getStartCoord(MouseEvent e)
    {
        x1[cnt] = e.getX();
        y1[cnt] = e.getY();
    }
    void getEndCoord(MouseEvent e)
    {
        x2[cnt] = e.getX();
        y2[cnt] = e.getY();
        this.repaint();
    }

}
