package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.paint.*;
import javafx.scene.text.*;
import javafx.scene.effect.*;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Text txt = new Text("Hello World");
        txt.setFill(Color.BLUE);
        txt.setFont(Font.font("null", FontWeight.BOLD, 30));
        Reflection r = new Reflection();
        r.setFraction(0.9);

        txt.setEffect(r);

        Stop[] stop = {new Stop(0, Color.PINK),
                new Stop(0.5, Color.WHITE),
                new Stop(1, Color.AQUAMARINE)};

        LinearGradient linear_gradient = new LinearGradient(1, 0,
                1, 1, true, CycleMethod.NO_CYCLE, stop);

        Rectangle rec = new Rectangle(50,50,250,250);
        rec.setFill(linear_gradient);

        StackPane root = new StackPane();
        root.getChildren().add(rec);
        root.getChildren().add(txt);

        primaryStage.setTitle("Javafx example");
        primaryStage.setScene(new Scene(root, 500, 500));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
