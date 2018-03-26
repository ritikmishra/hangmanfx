package hangman;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.MoveTo;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;


public class Main extends Application implements Initializable{


    @FXML
    private static Text showName;


    private static final int startX = 25;
    private static final int startY = 25;
    private static final MoveTo ORIGIN = new MoveTo(startX, startY);
    private static final int WIDTH = 500;
    private static final int HEIGHT = 375;

    public static Stage window;

    public static Scene startScene;
    public static Scene mainScene;

    public static String name;

    public static Text displayName;

    public Scene getMainScene()
    {
        GridPane pane = new GridPane();
        displayName = new Text();

        pane.getChildren().addAll(displayName);
        return new Scene(pane, WIDTH, HEIGHT);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        window = primaryStage;

        primaryStage.setTitle("Hangman");


        Parent startRoot =  FXMLLoader.load(getClass().getResource("startingScreen.fxml"));

        startScene = new Scene(startRoot);
        mainScene = getMainScene();

        primaryStage.setScene(startScene);
        primaryStage.show();
    }

    public static void switchToMainScene(String username)
    {
        name = username;
        displayName.setText(name);
        System.out.println("switched scene");
        window.setScene(mainScene);
    }


    public static void main(String[] args)
    {
        Hangman hangman = new Hangman();
        System.out.println(hangman.getWordToGuess());
        launch(args);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {

    }
}
