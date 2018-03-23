package hangman;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import sun.jvm.hotspot.memory.PlaceholderEntry;

public class StartingScreen extends Application
{
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("startingScreen.fxml"));
        primaryStage.setTitle("Intro Screen");

        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void runAnotherApp(Class<? extends Application> anotherAppClass) throws Exception
    {
        Application app2 = anotherAppClass.newInstance();
        Stage anotherStage = new Stage();
        app2.start(anotherStage);
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
