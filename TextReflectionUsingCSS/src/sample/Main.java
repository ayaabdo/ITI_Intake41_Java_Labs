package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.effect.Reflection;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Text txt = new Text("Hello World");
        txt.setId("txt");
        Reflection r = new Reflection();
        r.setFraction(0.9);
        txt.setEffect(r);

        Rectangle rec = new Rectangle(50,50,250,250);
        rec.setId("rec");

        StackPane root = new StackPane();
        root.getChildren().add(rec);
        root.getChildren().add(txt);

        Scene scene = new Scene(root, 500, 500);
        scene.getStylesheets().add(getClass().getResource("style.css").toString());

        primaryStage.setTitle("CSS example");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
