package clientpkg;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

public class Client extends Application{
    Socket mySocket;
    DataInputStream dis;
    PrintStream ps;
    TextArea chatTxt;
    TextField txt;
    TextField txtName;
    Label msgLbl,usrLbl;
    Thread th;

    public Client() {
        chatTxt = new TextArea();
        chatTxt.setEditable(false);
        try {
            mySocket = new Socket("127.0.0.1", 5005);
            chatTxt.appendText("Connected..." + "\n");
            dis = new DataInputStream(mySocket.getInputStream());
            ps = new PrintStream(mySocket.getOutputStream());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
         th = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    String replyMsg = null;
                    try {
                        replyMsg = dis.readLine();
                        chatTxt.appendText(replyMsg + "\n");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        });
    }
    public void start(Stage primaryStage) throws Exception{
        txt = new TextField();
        txt.setPrefWidth(300);
        txtName = new TextField();
        usrLbl = new Label("User name");
        msgLbl = new Label("Message");
        Button btn = new Button("Send");

        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String username = txtName.getText();
                String message = txt.getText();
                if (username.length() == 0) {
                    username = "Unknown";
                }
                if (message.length() == 0) {
                    return;
                }
                ps.println("[" + username + "]: " + message);
                ps.flush();
            }
        });

        FlowPane fp = new FlowPane();
        fp.getChildren().addAll(usrLbl,txtName,msgLbl,txt, btn);
        fp.setAlignment(Pos.CENTER);
        //fp.setMargin(fp, new Insets(0, 100, 100, 100));

        BorderPane pane = new BorderPane();
        pane.setBottom(fp);
        pane.setCenter(chatTxt);

        primaryStage.setTitle("Chat Room");
        primaryStage.setScene(new Scene(pane, 700, 700));
        th.start();
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
        new Client();
    }
}
