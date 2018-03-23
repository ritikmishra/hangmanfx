package hangman;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StartingScreen extends Application
{
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("startingScreen.fxml"));
        primaryStage.setTitle("Intro Screen");

        Scene scene = new Scene(root, 500, 375);

        primaryStage.setScene(scene);
        primaryStage.show();


    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
