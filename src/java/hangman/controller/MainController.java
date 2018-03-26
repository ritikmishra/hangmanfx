package hangman.controller;

import hangman.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Controls the scene when the user is playing the Hangman game (when the user is on the main scene)
 */
public class MainController implements Initializable
{
    /**
     * Makes a map that gets to see if a button can be clicked again
     */
    private Map<Button, Boolean> canClickButton = new HashMap<>();

    /**
     * Displays the username in the corner
     */
    @FXML
    public Text showName;


    /**
     * Displays the head of the corpse on the gallows
     * Hidden by default
     */
    @FXML
    public Ellipse head;

    /**
     * Displays the body of the corpse on the gallows
     * Hidden by default
     */
    @FXML
    public Rectangle torso;

    /**
     * Displays the left arm of the corpse on the gallows
     * Hidden by default
     */
    @FXML
    public Rectangle armLeft;

    /**
     * Displays the right arm of the corpse on the gallows
     * Hidden by default
     */
    @FXML
    public Rectangle armRight;

    /**
     * Displays the left leg of the corpse on the gallows
     * Hidden by default
     */
    @FXML
    public Rectangle legLeft;

    /**
     * Displays the right leg of the corpse on the gallows
     * Hidden by default
     */
    @FXML
    public Rectangle legRight;

    /**
     * Displays the letters the user has correctly guessed, and the number of letters in the word
     */
    @FXML
    public Text userGuesses;

    /**
     * A button that lets the user go to the leaderboard scene
     */
    @FXML
    public Button continueToEndScene;

    /**
     * The array of shapes making up the body
     *
     * Now that they are in an array, we can easily show body parts based on the number of incorrect guesses
     */
    private Shape[] body;

    /**
     * A public reference to {@link MainController#showName}. This ensures that {@link Main} can set the text of the name when it receives the name
     */
    public static Text kShowName;

    /**
     * When the user clicks on a button to guess the letter, update the amount of lives they have
     * @param event
     */
    @FXML
    private void updateLives(MouseEvent event)
    {
        userGuesses.setText(Main.hangman.getUserDisplay());
        updateLives(Main.hangman.getLives());
        Main.hangman.takeLife();

        if(Main.hangman.getLives() == 0)
        {
            continueToEndScene.setVisible(true);
        }
    }

    /**
     * Given a certain amount of lives left, show a body part
     * @param lives
     */
    private void updateLives(int lives)
    {
        System.out.println("lives = [" + lives + "]");
        body[body.length - lives].setVisible(true);
    }

    /**
     * Action listener for the buttons
     */
    @FXML
    private void click(ActionEvent event)
    {
        for(Button button : canClickButton.keySet())
        {
            if(event.getSource() == button)
            {
                clickedButton(button);
            }
        }
    }

    /**
     * Checks if it can be clicked
     *  Changes the background and doesn't let it be clicked again once clicked
     * @param button which button is being changed
     */
    private void clickedButton(Button button)
    {
        if(canClickButton.get(button))
        {
            button.getStyleClass().add("clicked");
            canClickButton.replace(button, false);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        // Ensure {@link Main} can access the text object
        kShowName = showName;

        // Build the body
        body = new Shape[] {head, torso, armLeft, armRight, legLeft, legRight};

        // hide the body
        for(Shape bodyPart : body)
        {
            bodyPart.setVisible(false);
        }
    }

    /**
     * When {@link MainController#continueToEndScene} is clicked, continue to the leaderboard scene
     * @param mouseEvent
     */
    @FXML
    private void continueToLeaderboard(MouseEvent mouseEvent)
    {
        Main.switchToLeaderboardScene();
    }
}
