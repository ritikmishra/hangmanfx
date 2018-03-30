package hangman;

import hangman.controller.MainController;
import hangman.util.Hangman;
import hangman.util.Leaderboard;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.*;


public class Main extends Application
{

    public static int runTime = 0;
    /**
     * Our instance of the Hangman game. This will keep track of letters guessed and lives left for us.
     */
    public static Hangman hangman;
    /**
     * The leaderboard. Static so that {@link hangman.controller.LeaderboardController} can read off the leaderboard entries.
     */
    public static Leaderboard leaderboard = new Leaderboard();
    private static Thread thread;
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
     * <p>
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
     * Switch from the starting scene to the main scene
     *
     * @param username The username that the user selected when they started the scene
     */
    public static void switchToMainScene(String username)
    {
        thread = new Thread(() -> {
            while(true)
            {
                runTime++;
                try { Thread.sleep(1000); }
                catch(InterruptedException e) {}
            }
        });

        thread.start();

        hangman = new Hangman(username);

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
        thread.stop();
        leaderboard.addEntry(name, hangman.getScore(), hangman.getWordToGuess(), hangman.getElapsedTimeSeconds());
        leaderboard.updateFile();

        window.setScene(leaderboardScene);
    }

    public static void main(String[] args)
    {
        launch(args);
    }

    //TODO: Reset buttons
    public static void switchToMainScene()
    {
        switchToMainScene(name);
    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        // Keep a reference to the window
        window = primaryStage;

        primaryStage.setTitle("Hangman");

        // Build all 3 of our scenes
        Parent mainRoot = FXMLLoader.load(getClass().getResource("main.fxml"));
        Parent startRoot = FXMLLoader.load(getClass().getResource("startingScreen.fxml"));
        Parent leaderboardRoot = FXMLLoader.load(getClass().getResource("showLeaderboard.fxml"));

        startScene = new Scene(startRoot);
        mainScene = new Scene(mainRoot);
        leaderboardScene = new Scene(leaderboardRoot, 600, screenSize.height);

        // Display the window
        primaryStage.setScene(startScene);
        primaryStage.show();
    }

    @Override
    public void stop()
    {
        leaderboard.updateFile();
    }
}
