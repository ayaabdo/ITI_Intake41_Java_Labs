package mypkg;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CounterButton extends Applet {
    int x;
    Button b1,b2;
    public void init()
    {
        b1 = new Button("X++");
        b2 = new Button("X--");
        b1.addActionListener(new MyButton1Listener());
        b2.addActionListener(new MyButton2Listener());
        add(b1);
        add(b2);
    }
    public void paint(Graphics g)
    {
        Font f = new Font("Arial",1,20);
        g.setFont(f);
        g.drawString("X= "+x,50,100);
    }
    class MyButton1Listener implements ActionListener{
            public void actionPerformed(ActionEvent ev)
            {
                if(x < 10)
                    x++;
                repaint();
            }
    }
    class MyButton2Listener implements ActionListener{
        public void actionPerformed(ActionEvent ev)
        {
            if(x > 0)
                x--;
            repaint();
        }
    }

}
