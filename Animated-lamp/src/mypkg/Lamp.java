package mypkg;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Lamp extends Applet {
    int cnt;
    Button b;
    public void init() {
        cnt = 0;
        b = new Button();
        this.setLayout(null);
        b.setBounds(140,270,20,20);
        b.setBackground(Color.YELLOW);
        b.addActionListener(new MyButtonListener());
        add(b);
    }
    public void paint(Graphics g)
    {
        g.setColor(Color.PINK);
        g.fillRoundRect(90, 250, 120, 80, 50, 30);

        g.setColor(Color.YELLOW);
        g.drawLine(125, 250, 125, 100);
        g.drawLine(175, 250, 175, 100);

        g.setColor(Color.orange);
        g.fillOval(85, 161, 135, 40);
        g.drawArc(85, 87, 130, 50, 62, 58);

        g.setColor(Color.ORANGE);
        g.drawLine(85, 180, 119, 89);
        g.drawLine(215, 180, 181, 89);
        if(cnt % 2 ==  0) {
            g.setColor(Color.green);
            g.fillArc(78, 120, 40, 40, 63, -174);

            g.setColor(Color.red);
            g.fillOval(120, 96, 40, 40);

            g.setColor(Color.PINK);
            g.fillArc(173, 100, 40, 40, 110, 180);
        }
        else{
            g.setColor(Color.magenta);
            g.fillArc(78, 120, 40, 40, 63, -174);

            g.setColor(Color.CYAN);
            g.fillOval(120, 96, 40, 40);

            g.setColor(Color.BLUE);
            g.fillArc(173, 100, 40, 40, 110, 180);
        }
    }
    class MyButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent ev)
        {
            cnt++;
            repaint();
        }
    }

}
