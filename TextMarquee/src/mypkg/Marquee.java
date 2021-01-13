package mypkg;

import java.applet.Applet;
import java.awt.*;
import java.util.logging.Logger;

public class Marquee extends Applet implements Runnable {

    int width,height,y,w,h;
    Thread th;
    public void init()
    {
        width = -50;
        height = 50;
        y = 0;
        th = new Thread(this);
        th.start();
    }
    public void paint(Graphics g)
    {
        w = getSize().width;
        h = getSize().height;
        Font f = new Font("Arial",1,20);
        g.setFont(f);
        if(width+y == 400) {
            width = -50;
            height +=30;
            y=0;
            g.drawString("Java World",width,height);
        }
        else {
            g.drawString("Java World", width + y, height);
        }
    }
    public void run()
    {
        while(true)
        {
            y += 10;
            try{
                th.sleep(100);
            }catch (InterruptedException ex){
                Logger.getLogger(Marquee.class.getName());
            }

            this.repaint();

        }
    }
}
