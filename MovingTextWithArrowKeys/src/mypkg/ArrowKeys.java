package mypkg;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ArrowKeys extends Applet {
    int width,height;
    public void init()
    {
        width = height = 50;
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                moveIt(e);
            }
        });
    }
    public void paint(Graphics g)
    {
        Font f = new Font("Arial",1,20);
        g.setFont(f);
        int strwidth = g.getFontMetrics().stringWidth("Java");
        checkBoundries(strwidth);
        g.drawString("Java", width, height);


    }
    private void checkBoundries(int strwidth)
    {
        int w = getSize().width;
        int h = getSize().height;
        if(width >= w) width = w - strwidth;
        else if(height >= h) height = h - strwidth;
        else if(width < 0)  width = 0;
        else if(height < 0)  height = 0;

    }
    public void moveIt(KeyEvent e)
    {
        if(e.getKeyCode() == 39) //right
        {
            width += 10;
        }
        else if(e.getKeyCode() == 37) //left
        {
            width -= 10;
        }
        else if(e.getKeyCode() == 38) //up
        {
            height -= 10;
        }
        else if(e.getKeyCode() == 40)  //down
        {
            height += 10;
        }
        this.repaint();
        //System.out.println("hi "+e.getKeyCode());
    }
}


















