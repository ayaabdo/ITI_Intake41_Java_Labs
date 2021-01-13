package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Main extends Application {

    Socket mySocket;
    DataInputStream dis;
    PrintStream ps;
    TextArea chatTxt;
    TextField txt;
    TextField txtName;

    @Override
    public void start(Stage primaryStage) throws Exception{
        chatTxt = new TextArea();
        txt = new TextField();
        txt.setPrefWidth(400);
        txtName = new TextField();
        Button btn = new Button("Send");
        start();

        btn.setOnAction(new EventHandler<ActionEvent>() {
            String username;
            String message;
            @Override
            public void handle(ActionEvent event) {

                username = txtName.getText();
                message = txt.getText();
                if (username.length() == 0) {
                    username = "Unknown";
                }
                if (message.length() == 0) {
                    return;
                }
                r.run();
            }
            Runnable r = new Runnable() {
                    public void run() {

                        ps.println("[" + username + "]: " + message);
                        //ps.flush();
                        String replyMsg = null;
                        try {
                            replyMsg = dis.readLine();
                            chatTxt.appendText(replyMsg + "\n");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                };
        });

        FlowPane fp = new FlowPane();
        fp.getChildren().addAll(txtName,txt, btn);
        fp.setAlignment(Pos.CENTER);
        fp.setMargin(fp, new Insets(0, 100, 100, 100));

        BorderPane pane = new BorderPane();
        pane.setBottom(fp);
        pane.setCenter(chatTxt);

        primaryStage.setTitle("Chat Room");
        primaryStage.setScene(new Scene(pane, 700, 700));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
    public void start()
    {
        try {
            mySocket = new Socket("127.0.0.1", 5005);
            chatTxt.appendText("Connected. \n");

        }catch (UnknownHostException ex) {
            System.out.println("Server not found: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("I/O Error: " + ex.getMessage());
        }
        try {
            ps = new PrintStream(mySocket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            dis = new DataInputStream(mySocket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void serverListened(String s)
    {
        chatTxt.appendText(s);
    }
}
