package hangman;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.stage.Stage;


public class Main extends Application {

    private static final int startX = 25;
    private static final int startY = 25;
    private static final MoveTo ORIGIN = new MoveTo(startX, startY);
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");

        Path gallows = new Path(ORIGIN, new LineTo(startX, startY+60));

//        root.get
        primaryStage.setScene(new Scene(root, 500, 375));
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
