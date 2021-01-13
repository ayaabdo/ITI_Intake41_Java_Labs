package mypkg;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.PublicKey;
import java.util.logging.Logger;

public class RunningBall extends Applet {
    Button b1,b2;
    int width,height,w,h;
    Thread th;
    boolean running;
    public void init()
    {
        width = height = 50;
        w = getSize().width;
        h = getSize().height;
        b1 = new Button("Run");
        b2 = new Button("Pause");
        b1.addActionListener(new MyButton1Listener());
        b2.addActionListener(new MyButton2Listener());
        add(b1);
        add(b2);
    }
    public void paint(Graphics g)
    {
        g.setColor(Color.magenta);
        g.fillOval(width, height, 40, 40);
    }
    private void checkBoundries()
    {
        w = getSize().width;
        h = getSize().height;
        if(width > w-50) {width = w - 50; running = false;}
        else if(width < 0)  width = 0;
        else {running=true; width += 5;}

    }
    class MyButton1Listener implements ActionListener , Runnable{
        public void start()
        {
            th = new Thread(this);
            running = true;
            th.start();
        }
        public void actionPerformed(ActionEvent ev)
        {
            start();
        }
        public void run(){
            while(running) {
                checkBoundries();
                try {
                    th.sleep(100);
                } catch (InterruptedException ex) {
                    Logger.getLogger(RunningBall.class.getName());
                }

                repaint();
            }

        }
    }
    class MyButton2Listener implements ActionListener{
        public void actionPerformed(ActionEvent ev)
        {
            running = false;
            repaint();
        }
    }
}
