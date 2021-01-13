package chatpkg;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer {
    ServerSocket serverSocket;
    public ChatServer()
    {
        try {
            serverSocket = new ServerSocket(5005);
        } catch (IOException e) {
            e.printStackTrace();
        }
        while(true)
        {
            Socket s = null;
            try {
                s = serverSocket.accept();
            } catch (IOException e) {
                e.printStackTrace();
            }
            new ChatHandler(s);
        }
    }
    public static void main(String[] args)
    {
        new ChatServer();
    }

}
