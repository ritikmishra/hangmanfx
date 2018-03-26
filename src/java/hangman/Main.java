package hangman;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.shape.MoveTo;

import javafx.scene.text.Text;

import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;


public class Main extends Application implements Initializable{

    private static final int startX = 25;
    private static final int startY = 25;
    private static final MoveTo ORIGIN = new MoveTo(startX, startY);
    private static final int WIDTH = 500;
    private static final int HEIGHT = 375;

    public static Hangman hangman;

    public static Stage window;

    public static Scene startScene;
    public static Scene mainScene;

    public static String name;

    public static String getName()
    {
        return name;
    }

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
        hangman = new Hangman(username);
        System.out.println(hangman.getWordToGuess());

        name = username;
        MainController.kShowName.setText(name);
        window.setScene(mainScene);
    }


    public static void main(String[] args)
    {

        launch(args);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {

    }
}
