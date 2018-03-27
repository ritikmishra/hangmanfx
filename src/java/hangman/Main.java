package hangman;

import hangman.controller.MainController;
import hangman.util.Hangman;
import hangman.util.Leaderboard;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.shape.MoveTo;

import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;


public class Main extends Application {

    /**
     * Our instance of the Hangman game. This will keep track of letters guessed and lives left for us.
     */
    public static Hangman hangman;

    /**
     * The Stage on which all of our scenes are displayed. By keeping a reference to it, we can change scenes at any time.
     */
    private static Stage window;

    /**
     * The scene to be displayed when the user first opens the game.
     * Contains Nodes that let the user input their name
     */
    private static Scene startScene;

    /**
     * The scene to be displayed when the user is playing hangman
     *
     * Contains nodes that let the user guess a letter, view how many lives they have left, and continue to the leaderboard
     */
    private static Scene mainScene;

    /**
     * The scene to be displayed when the user is looking at the leaderboard
     */
    private static Scene leaderboardScene;

    /**
     * The user's name. We keep this in order to create an entry in the leaderboard later.
     */
    private static String name;

    /**
     * The leaderboard. Static so that {@link hangman.controller.LeaderboardController} can read off the leaderboard entries.
     */
    public static Leaderboard leaderboard = new Leaderboard();

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Keep a reference to the window
        window = primaryStage;

        primaryStage.setTitle("Hangman");

        // Build all 3 of our scenes
        Parent mainRoot = FXMLLoader.load(getClass().getResource("main.fxml"));
        Parent startRoot =  FXMLLoader.load(getClass().getResource("startingScreen.fxml"));
        Parent leaderboardRoot = FXMLLoader.load(getClass().getResource("showLeaderboard.fxml"));

        startScene = new Scene(startRoot);
        mainScene = new Scene(mainRoot);
        leaderboardScene = new Scene(leaderboardRoot, 600, 30 * leaderboard.getLeaderboard().size() + 60);

        // Display the window
        primaryStage.setScene(startScene);
        primaryStage.show();
    }

    /**
     * Switch from the starting scene to the main scene
     * @param username The username that the user selected when they started the scene
     */
    public static void switchToMainScene(String username)
    {
        hangman = new Hangman(username);
        System.out.println(hangman.getWordToGuess());

        name = username;
        MainController.kShowName.setText(name);

        MainController.kUserGuesses.setText(hangman.getUserDisplay());
        window.setScene(mainScene);
    }

    /**
     * Switch from the main scene (the hangman scene) to the leaderboard scene
     */
    public static void switchToLeaderboardScene()
    {
        leaderboard.addEntry(name, hangman.getScore());
        leaderboard.updateFile();
        window.setScene(leaderboardScene);
    }


    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void stop()
    {
        leaderboard.updateFile();
    }

    //TODO: Reset buttons
    public static void switchToMainScene()
    {
        switchToMainScene(name);
    }
}
