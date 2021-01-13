package chatpkg;


import javafx.application.Platform;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Date;
import java.util.Vector;

public class ChatHandler extends Thread{
    DataInputStream dis;
    PrintStream ps;
    static Vector<ChatHandler> clientsVector = new Vector<ChatHandler>();
    public ChatHandler(Socket cs)
    {
        try {
            dis = new DataInputStream(cs.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            ps = new PrintStream(cs.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        clientsVector.add(this);
        start();
    }
    public void run()
    {
        while(true)
        {
            String str = null;
            try {
                str = dis.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            sendMessageToAll(str);
        }

    }
    void sendMessageToAll(String msg)
    {
        System.out.println(clientsVector.size());
        for(ChatHandler ch : clientsVector)
        {
            ch.ps.println(msg);
        }
    }
}
