package mypkg;

import java.applet.*;
import java.awt.*;
import java.util.*;
import java.util.logging.Logger;

public class DateApp extends Applet implements Runnable{
    Date d;
    Thread th;
    public  void init()
    {
        d = new Date();
        th = new Thread(this);
        th.start();
    }
    public void paint(Graphics g)
    {
        Font f = new Font("Arial",1,20);
        g.setFont(f);
        g.drawString(d.toString(), 50,150);
    }
    public void run()
    {
        while(true)
        {
            d = new Date();
           try {
               th.sleep(1000);
           }
           catch (InterruptedException ex){
               Logger.getLogger(DateApp.class.getName());
           }
           repaint();
        }
    }

}
