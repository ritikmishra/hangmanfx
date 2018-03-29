package hangman.controller;

import hangman.Main;
import hangman.util.LetterButtons;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Ellipse;
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

    LetterButtons buttons;
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
     * A public reference to {@link MainController#userGuesses}. This ensures that {@link Main} can set the number of underscores when it chooses a word
     */
    public static Text kUserGuesses;

    /**
     * A button that lets the user go to the leaderboard scene
     */
    @FXML
    public Button continueToEndScene;

    /**
     * The array of shapes making up the body
     * <p>
     * Now that they are in an array, we can easily show body parts based on the number of incorrect guesses
     */
    private Shape[] body;

    /**
     * A public reference to {@link MainController#showName}. This ensures that {@link Main} can set the text of the name when it receives the name
     */
    public static Text kShowName;

    /**
     * Button "A"
     */
    @FXML
    public Button A;

    /**
     * Button "B"
     */
    @FXML
    public Button B;

    /**
     * Button "C"
     */
    @FXML
    public Button C;

    /**
     * Button "D"
     */
    @FXML
    public Button D;

    /**
     * Button "E"
     */
    @FXML
    public Button E;

    /**
     * Button "F"
     */
    @FXML
    public Button F;

    /**
     * Button "G"
     */
    @FXML
    public Button G;

    /**
     * Button "H"
     */
    @FXML
    public Button H;

    /**
     * Button "I"
     */
    @FXML
    public Button I;

    /**
     * Button "J"
     */
    @FXML
    public Button J;

    /**
     * Button "K"
     */
    @FXML
    public Button K;

    /**
     * Button "L"
     */
    @FXML
    public Button L;

    /**
     * Button "M"
     */
    @FXML
    public Button M;

    /**
     * Button "N"
     */
    @FXML
    public Button N;

    /**
     * Button "O"
     */
    @FXML
    public Button O;

    /**
     * Button "P"
     */
    @FXML
    public Button P;

    /**
     * Button "Q"
     */
    @FXML
    public Button Q;

    /**
     * Button "R"
     */
    @FXML
    public Button R;

    /**
     * Button "S"
     */
    @FXML
    public Button S;

    /**
     * Button "T"
     */
    @FXML
    public Button T;

    /**
     * Button "U"
     */
    @FXML
    public Button U;

    /**
     * Button "V"
     */
    @FXML
    public Button V;

    /**
     * Button "W"
     */
    @FXML
    public Button W;

    /**
     * Button "X"
     */
    @FXML
    public Button X;


    /**
     * Button "Y"
     */
    @FXML
    public Button Y;

    /**
     * Button "Z"
     */
    @FXML
    public Button Z;

    /**
     * When the user clicks on a button to guess the letter, update the amount of lives they have
     *
     * @param letter The letter the user guessed
     */
    @FXML
    private void updateGuess(char letter)
    {

        boolean correctlyGuessed = Main.hangman.checkIfContainLetter(letter);

        if(!correctlyGuessed)
        {
            addBodyParts(Main.hangman.getLives());
        }

        if(Main.hangman.getLives() == 0 || Main.hangman.lettersLeft() == 0)
        {
            continueToEndScene.setVisible(true);

            // Reveal the word
            userGuesses.setText(Main.hangman.getWordToGuess());
            Main.runTime = 0;
        }
        else
        {
            updateText();
        }
    }

    /**
     * Given a certain amount of lives left, show a body part
     *
     * @param lives Amount of lives
     */
    private void addBodyParts(int lives)
    {
        System.out.println("lives = [" + lives + "]");
        body[body.length - lives - 1].setVisible(true);
    }

    /**
     * Action listener for the buttons
     */
    @FXML
    private void click(ActionEvent event)
    {
        updateGuess(buttons.buttonLetter.get(buttons.click(event)));
    }

    /**
     * Key listener for button
     */
    @FXML
    private void typed(KeyEvent key)
    {
        if(key.getCode() == KeyCode.ENTER)
        {
            for(Button button : buttons.canClickButton.keySet())
            {
                if(key.getSource() == button)
                {
                    updateGuess(buttons.buttonLetter.get(button));
                    buttons.changeColor(button);
                }
            }
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
//        updateText();
        // Ensure {@link Main} can access the text object
        kShowName = showName;

        kUserGuesses = userGuesses;

        // Build the body
        body = new Shape[] { head, torso, armLeft, armRight, legLeft, legRight };

        //Initialize buttons
        buttons = new LetterButtons();

        //Adds buttons to letter buttons
        buttons.setButtons(A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, W, X, Y, Z);

        //Sets button maps
        buttons.setButtonMaps();

        // hide the body
        for(Shape bodyPart : body)
        {
            bodyPart.setVisible(false);
        }
//        updateText();
    }

    private void updateText()
    {
        String userDisplay = Main.hangman.getUserDisplay();
        userGuesses.setText(userDisplay);
    }

    /**
     * When {@link MainController#continueToEndScene} is clicked, continue to the leaderboard scene
     *
     * @param mouseEvent mouse listener
     */
    @FXML
    private void continueToLeaderboard(MouseEvent mouseEvent)
    {
        buttons.canClickButton.forEach((key, value) -> {
            buttons.canClickButton.put(key, true);
            key.getStyleClass().remove("clicked");

        });
        for(Shape part : body)
        {
            part.setVisible(false);
        }
        continueToEndScene.setVisible(false);
        Main.switchToLeaderboardScene();
    }
}
