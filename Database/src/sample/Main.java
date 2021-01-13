package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.control.*;
import sun.rmi.server.InactiveGroupException;

import java.util.*;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class Main extends Application {

    Dbms db;
    List<TextField> txt;
    @Override
    public void start(Stage primaryStage) throws Exception{
        db = new Dbms();

        List<Button> btn = new ArrayList<Button>();
        List<Label> lbl = new ArrayList<Label>();
        txt = new ArrayList<TextField>();

        lbl.add(new Label("ID"));
        lbl.add(new Label("Name"));
        lbl.add(new Label("Track"));
        lbl.add(new Label("Email"));
        lbl.add(new Label("Phone"));

        txt.add(new TextField());
        txt.add(new TextField());
        txt.add(new TextField());
        txt.add(new TextField());
        txt.add(new TextField());
        txt.get(0).setEditable(false);

        lbl.get(0).setLabelFor(txt.get(0));
        lbl.get(1).setLabelFor(txt.get(1));
        lbl.get(2).setLabelFor(txt.get(2));
        lbl.get(3).setLabelFor(txt.get(3));
        lbl.get(4).setLabelFor(txt.get(4));

        btn.add(new Button("New.."));
        btn.get(0).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                db.newRecord(txt.get(1).getText(),txt.get(2).getText(),txt.get(3).getText(),txt.get(4).getText());
            }
        });

        btn.add(new Button("Update"));
        btn.get(1).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                db.updateDb(txt.get(0).getText(),txt.get(1).getText(),txt.get(2).getText(),txt.get(3).getText(),txt.get(4).getText());
            }
        });

        btn.add(new Button("Delete"));
        btn.get(2).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                db.deleteDb(txt.get(0).getText());
                clearTextBoxes();
            }
        });

        btn.add(new Button("First"));
        btn.get(3).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                PersonalData pd = db.getFirstRow();
                setTextBoxes(pd);
            }
        });

        btn.add(new Button("Previous"));
        btn.get(4).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                PersonalData pd = db.getPrevRow(txt.get(0).getText());
                if(pd != null)
                    setTextBoxes(pd);
                else
                {
                    raiseAlert();
                }
            }
        });

        btn.add(new Button("Next"));
        btn.get(5).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                PersonalData pd = db.getNextRow(txt.get(0).getText());
                if(pd != null)
                    setTextBoxes(pd);
                else
                {
                    raiseAlert();
                }
            }
        });

        btn.add(new Button("Last"));
        btn.get(6).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                PersonalData pd = db.getLastRow();
                setTextBoxes(pd);
            }
        });

        btn.add(new Button("Clear"));
        btn.get(7).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                clearTextBoxes();
            }
        });

        btn.add(new Button("Exit"));
        btn.get(8).setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                db.closeConnection();
                Platform.exit();
            }
        });

        FlowPane fp = new FlowPane();
        fp.getChildren().addAll(btn);
        fp.setAlignment(Pos.CENTER);
        fp.setMargin(fp, new Insets(0, 100, 100, 100));

        GridPane gPane = new GridPane();
        gPane.setPadding(new Insets(100, 100, 100, 100));

        gPane.add(lbl.get(0),0,100,50,50);
        gPane.add(txt.get(0),50,100,50,50);
        gPane.add(lbl.get(1),0,150,50,50);
        gPane.add(txt.get(1),50,150,50,50);
        gPane.add(lbl.get(2),0,200,50,50);
        gPane.add(txt.get(2),50,200,50,50);
        gPane.add(lbl.get(3),0,250,50,50);
        gPane.add(txt.get(3),50,250,50,50);
        gPane.add(lbl.get(4),0,300,50,50);
        gPane.add(txt.get(4),50,300,50,50);

        BorderPane pane = new BorderPane();
        pane.setCenter(gPane);
        pane.setBottom(fp);

        Scene scene = new Scene(pane, 600, 500);
        primaryStage.setTitle("Personal Details");
        primaryStage.setScene(scene);
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
        //closeConn();
    }
    public void setTextBoxes(PersonalData pd)
    {
        txt.get(0).setText(String.valueOf(pd.getId()));
        txt.get(1).setText(pd.getName());
        txt.get(2).setText(pd.getTrack());
        txt.get(3).setText(pd.getEmail());
        txt.get(4).setText(pd.getPhone());
    }
    public void clearTextBoxes()
    {
        txt.get(0).setText("");
        txt.get(1).setText("");
        txt.get(2).setText("");
        txt.get(3).setText("");
        txt.get(4).setText("");
    }
    public void raiseAlert()
    {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Error");
        alert.setContentText("No more records");
        alert.showAndWait();
    }
}
