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

    public static Stage window;

    public static Scene startScene;
    public static Scene mainScene;

    public static String name;

    @Override
    public void start(Stage primaryStage) throws Exception{
        window = primaryStage;

        primaryStage.setTitle("Hangman");

        Parent mainRoot = FXMLLoader.load(getClass().getResource("main.fxml"));
        Parent startRoot =  FXMLLoader.load(getClass().getResource("startingScreen.fxml"));

        startScene = new Scene(startRoot);
        mainScene = new Scene(mainRoot);

        primaryStage.setScene(startScene);
        primaryStage.show();
    }

    public static void switchToMainScene(String username)
    {
        name = username;
        window.setScene(mainScene);
    }


    public static void main(String[] args)
    {
        Hangman hangman = new Hangman();
        System.out.println(hangman.getWordToGuess());
        launch(args);
    }
}
